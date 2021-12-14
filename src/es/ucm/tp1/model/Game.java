package es.ucm.tp1.model;

import java.util.Random;

import es.ucm.tp1.Exceptions.highlevelexceptions.GameException;
import es.ucm.tp1.Exceptions.highlevelexceptions.HighRecordException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.CreationError;
import es.ucm.tp1.Exceptions.lowlevelexceptions.InputOutputRecordException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.InvalidPositionException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.NotEnoughCoinsException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.RecordsException;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.control.Records;
import es.ucm.tp1.model.Elements.GameElement;
import es.ucm.tp1.model.InstantActions.InstantAction;
import es.ucm.tp1.model.Elements.GameElementContainer;
import es.ucm.tp1.model.Elements.IPosElement;
//We just imported that because we need to access the static fields
import es.ucm.tp1.model.Elements.Coin;
import es.ucm.tp1.model.Elements.Obstacle;

public class Game implements IGame, Serializable {
	Player player = null;
	private GameElementContainer elements; 
	private Records records;
	private boolean isOldRecordShown = false;

	private long ellapsedtime;
	private int cycle;
	private boolean victory;
	private boolean exit; 
	
	private static final String FINISH_LINE = "¦";
	private static final String ERROR_MSG_NOT_MOVEABLE = "WARNING: Coudn't move the player in that direction";
	private static final String ERROR_RECORD = "Something went wrong to record this round";
	
	Long seed;
	Level level;
	boolean isTestMode;
	Random rand;
	
	public Game(Long seed, Level level, boolean isTestMode) {
		initSetFields(seed, level, isTestMode, false, false);
		initRand(this.seed);
		initObjects();
		boolean reset = false;
		setUniquePlayer(reset);

	}
	
	public void loadRecord() throws GameException {
		try {
			this.records.load();
		} catch (InputOutputRecordException ex) {
			throw new GameException(ex.getMessage(), ex);
		} finally {
			//Create the file --> Creating a new record.
		}
	}

	private void initSetFields(Long seed, Level level, boolean isTestMode, boolean victory, boolean exit) {
		this.seed = seed;
		this.level = level;
		this.isTestMode = isTestMode;
		this.cycle = 0; 
		this.ellapsedtime = 0;
		this.elements = new GameElementContainer();
		this.victory = false;
		this.exit = false;
		this.records = new Records(this);
	}
	
	
	private void setUniquePlayer(boolean reset) {
		int startingline = (int) this.getRoadWidth() / 2; 
		this.player = Player.getPlayer(reset, startingline, this);
	}
	
	public void reset(long seed, Level level) throws InputOutputRecordException {
		boolean reset = true;
		setUniquePlayer(reset);
		initObjects();
		victory = false;
		this.records.load();
		// ---------------------------  Beginning - Addition to reset ---------------------------
		this.seed = seed;
		this.level = level;
		// ---------------------------  End - Addition to reset ---------------------------
	}
	
	// ---------------------------  Beginning of Setters and Getters ---------------------------
	@Override
	public void setExit(boolean exit) {
		this.exit = exit;
	}
	
	public boolean getExit() {
		return exit;
	}
	
	public void setTest(boolean isTestMode) {
		this.isTestMode = isTestMode;
	}
	
	public boolean isTest() {
		return isTestMode;
	}
	
	//In further iterations we could also throw an exception if it does not work
	public void incrementCyle(Direction direction) throws InvalidPositionException {
		//Later can be removed by throwing an exception which is handelt in the Command
		boolean result = false;
		if (direction == null) {
			throw new InvalidPositionException(Game.ERROR_MSG_NOT_MOVEABLE);
		} else {
			this.movePlayer(result, direction);
		}
		update();
		this.cycle++;
	}
	
	public int getCycle() {
		return cycle;
	}
	
	public int getCameraPosition() {
		return player.getX();
	}
	
	public int getRoadWidth() {
		return level.getWidth();
	}
	public int getVisibility() {
		return level.getVisibility();
	}

	public boolean getVictory() {
		return victory;
	}
	
	// ---------------------------  End of Setters and Getters  ---------------------------
	
	// ---------------------------  Begin - Creating the elements ---------------------------
	
	private void initObjects() {
		GameElementGenerator.generateGameElements(this, this.level);
	}
	
	public void removeAll() {
		elements.clear();
	}

	//This we will let in this part of the code because the decribtion does not tell diffrently 
	//And it is the logic of the game when and when not a gameElement should be added
	public final void tryToAddObject(GameElement gameElement, double elementFrequency) {
		try {
			this.elements.processAddingObject(gameElement, elementFrequency, this.rand);
		} catch (CreationError ex) {}
	}
	
	public final int getRandomLane() {
		Random rand = this.rand;
		int lane = (int) (rand.nextDouble() * (this.getRoadWidth()));
		return lane;
	}
	
	// ---------------------------  End - Creating the elements ---------------------------

	protected void initRand(long seed) {
		this.rand = new Random(seed);
	}
	
	protected int getRoadLength() {
		return this.level.getLength();
	}
	
	// ---------------------------  Begins - Time methods ---------------------------
	
	public void startTime() {
		ellapsedtime = System.currentTimeMillis();
	}
	
	public boolean isTimeOn() {
		return this.ellapsedtime != 0;
	}
	
	public long getTime() {
		return System.currentTimeMillis() - ellapsedtime;
	}
	
	public String getFormatedTime() {
		// time: The time in seconds with 2 decimals of precision
		double seconds = 0;
		if (this.ellapsedtime != 0) seconds = (System.currentTimeMillis() - ellapsedtime) / 1000.;
		String time = String.format("%.2f", seconds) + " s";
		return time;
	}
	// ---------------------------  Ends - Time methods ---------------------------
	
	private String getCurrentRecordEntry() {
		if (!this.isOldRecordShown) {
			this.isOldRecordShown = true;
			return this.records.getLevelRecords(level);
		}
		return "";
	}
	
	public String getGameStatus() {
		StringBuilder str = new StringBuilder(); 
		int distanceToFinish = level.getLength() - player.getX();
		
		String oldRecord = this.getCurrentRecordEntry();
		if (oldRecord != "") str.append(oldRecord);
		str.append(String.format("Distance: " + distanceToFinish + "%n"));
		str.append(String.format("Coins: " + player.getCoins() + "%n"));
		str.append(String.format("Cicle: " + this.cycle + "%n"));
		str.append(String.format("Total obstacles: " + Obstacle.counter + "%n"));
		str.append(String.format("Total coins: " + Coin.counter + "%n"));
		if (!isTest()) {
			str.append(String.format("Ellapsed Time: " + getFormatedTime() + "%n"));
		}
		return str.toString().trim();
	}

	public boolean isFinished() {
		// Game finishes if player crashes, wins or exits
		boolean crashed, result;
		victory = checkFinishLine();
		crashed = player.isCrashed();
		result = crashed || victory || exit;
		
		return result; 
	}

	public boolean checkFinishLine() {
		boolean finnished;
		finnished = player.getX() == level.getLength() + 1; // It actually goes through the finish line, not the best imo
		return finnished;
	}

	public Collider getObjectInPosition(int x, int y) {
		Collider col = elements.getObjectInPosition(x, y);
		return col;
	}
	
	public ColliderCallback getPlayerCallback() {
		return player;
	}
		
	void movePlayer(boolean shouldDisplay, Direction direction) throws InvalidPositionException {
		player.processingMovement(shouldDisplay, direction);
	}

	public String positionToString(int x, int y) {
		return this.elements.positionToStringLogic(x, y, level, (IPosElement) this.player, Game.FINISH_LINE);
	}
		
	public void removeDeadObjects() {
		elements.removeDead();
	}

	public void update() {
		elements.update();
		GameElementGenerator.generateRuntimeObjects(this);
		removeDeadObjects();
		player.update();
	}
	
	public void doInstantAction(InstantAction action) {
		action.execute(this);	
	}
	
	public int getPlayerLane() {
		return player.getY();
	}
	
	public void getAmountOfCoinsPlayer(int amount) throws NotEnoughCoinsException {
		player.payAble(amount);
	}
	
	public Level getLevel() {
		return this.level;
	}

	public void playerPays(int cost) {
		player.pay(cost);
	}

	public int getRandomColumn() {
		int column = rand.nextInt(getVisibility()) + getCameraPosition();
		return column;
	}
	
	public IPosElement[] getAllPos() {
		return elements.getAllPosElements();
	}
		
	public String getRecords() {
		return records.toString();
	}
	
	@Override
	public String getSerialized() {
		StringBuilder str = new StringBuilder();
		str.append("Game Objects: " + String.format("%n"));
		str.append(elements.getSerialized());
		return str.toString();
	}
	
	public String getGeneralState() {
		StringBuilder str = new StringBuilder();
		
		str.append("Level: " + level.name() + String.format("%n"));
		str.append("Cycles: " + getCycle() + String.format("%n"));
		str.append("Coins: " + player.getCoins() + String.format("%n"));
		str.append("Ellapsed Time: " + ellapsedtime + String.format("%n"));
		
		return str.toString();
	}
	
	public boolean isRecord() {
		return this.records.getIsRecord();
	}

	public void close() throws HighRecordException {
		try {
			if (isFinished()) {
				records.trySetNewRecord(level.name(), getTime());
				try {
					records.save();	
				} catch (InputOutputRecordException ex){
					throw new HighRecordException(Game.ERROR_RECORD, ex);
				}
			}
		} catch (RecordsException ex) {
			throw new HighRecordException(Game.ERROR_RECORD, ex);
		}
	}
}