package es.ucm.tp1.model;

import java.util.Random;
import es.ucm.tp1.control.Level;

import es.ucm.tp1.model.Elements.GameElement;
import es.ucm.tp1.model.InstantActions.InstantAction;
import es.ucm.tp1.view.GamePrinter;
import es.ucm.tp1.model.Elements.GameElementContainer;
//import es.ucm.tp1.model.GameElementGenerator;

//We just imported that because we need to access the static fields
import es.ucm.tp1.model.Elements.Coin;
import es.ucm.tp1.model.Elements.Obstacle;

public class Game {
	Player player = null;
	private GameElementContainer elements; 

	private long ellapsedtime;
	private int cycle;
	private boolean victory;
	private boolean exit;
	
	private static final String FINISH_LINE = "¦";
	
	Long seed;
	Level level;
	boolean isTestMode;
	Random rand;
	
	public Game(Long seed, Level level, boolean isTestMode) {
		this.seed = seed;
		this.level = level;
		this.isTestMode = isTestMode;
		this.cycle = 0; 
		this.ellapsedtime = 0;
		this.elements = new GameElementContainer();
		initRand(this.seed);
		initObjects();
		boolean reset = false;
		setUniquePlayer(reset);
		victory = false;
		exit = false;
	}
	
	
	private void setUniquePlayer(boolean reset) {
		int startingline = (int) this.getRoadWidth() / 2; 
		this.player = Player.getPlayer(reset, startingline, this);
	}
	
	public void reset(long seed, Level level) {
		boolean reset = true;
		setUniquePlayer(reset);
		initObjects();
		victory = false;
		// ---------------------------  Beginning - Addition to reset ---------------------------
		this.seed = seed;
		this.level = level;
		// ---------------------------  End - Addition to reset ---------------------------
	}
	
	// ---------------------------  Beginning of Setters and Getters ---------------------------
	
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
	public boolean incrementCyle(Direction direction) {
		boolean result = false;
		//Later can be removed by throwing an exception which is handelt in the Command
		if (direction != null) {
			result = this.movePlayer(result, direction);
		}
		update();
		this.cycle++;
		return result; 
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
		GameElement element = null;
		Random rand = this.rand;
		double createElement = rand.nextDouble();
		if (createElement < elementFrequency) {
			if (elements != null) {
				for (int i = 0; i < elements.size(); i++) {
					element = elements.get(i);
					if (element.isInPos(gameElement.getX(), gameElement.getY())) {
						return;
					}
				}
			}
			addObject(gameElement);
			gameElement.onEnter();
		}
	}
	
	public void addObject(GameElement gameElement) {
		int x = gameElement.getX();
		int y = gameElement.getY();
		if (elements.isObjectInPos(x, y)) {
			elements.remove(x, y);
		}
		elements.add(gameElement);
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
	
	public String getTime() {
		// time: The time in seconds with 2 decimals of precision
		double seconds = 0;
		if (this.ellapsedtime != 0) seconds = (System.currentTimeMillis() - ellapsedtime) / 1000.;
		String time = String.format("%.2f", seconds) + " s";
		return time;
	}
	// ---------------------------  Ends - Time methods ---------------------------
	
	public String getGameStatus() {
		StringBuilder str = new StringBuilder(); 
		int distanceToFinish = level.getLength() - player.getX();
		
		str.append(String.format("Distance: " + distanceToFinish + "%n"));
		str.append(String.format("Coins: " + player.getCoins() + "%n"));
		str.append(String.format("Cicle: " + this.cycle + "%n"));
		str.append(String.format("Total obstacles: " + Obstacle.counter + "%n"));
		str.append(String.format("Total coins: " + Coin.counter + "%n"));
		if (!isTest()) {
			str.append(String.format("Ellapsed Time: " + getTime() + "%n"));
		}
		return str.toString().stripTrailing();
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
		
	boolean movePlayer(boolean shouldDisplay, Direction direction) {
		shouldDisplay = player.processingMovement(shouldDisplay, direction);
		player.doCollision();
		return shouldDisplay;
	}

	public String positionToString(int x, int y) {
		return positionToStringLogic(x, y);
	}


	//Maybe moved to an other class? But be aware not to destroy encapsulation
	//As you see we refactored it to out of positionToString but we did not have more time
	//In the next iteration we could move this method to a better place 
	protected String positionToStringLogic(int x, int y) {
		String position = "";
		GameElement elem = null;
		if (x ==  level.getLength()) {
			position = FINISH_LINE;
		} 
		if(player.isInPos(x, y)) {
			position = player.toString();
		} else {
			GameElement elem2;
			for (int i = 0; i < elements.size(); i++) {
				elem2 = elements.get(i);
				if (elem2.isInPos(x, y)) {
					elem = elem2;
				}
			}
			if (elem != null) {
				position = elem.toString();
			} 
		}
		return position;
	}
		
	public void removeDeadObjects() {
		elements.removeDead();
	}

	public void update() {
		elements.update();
		GameElementGenerator.generateRuntimeObjects(this);
		removeDeadObjects();
		player.doCollision();
		player.update();
	}
	
	public void doInstantAction(InstantAction action) {
		action.execute(this);		
	}
	
	public int getPlayerLane() {
		return player.getY();
	}
	
	public int getAmountOfCoinsPlayer() {
		return player.getCoins();
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
	
	
	//@Simon you told us to move it to the Game in your eMail from the 29.11.
	//Some elements like the truck has to be moved 2xtwice because they have their own moving logic
	//We are moving the first element not if it has a second behind of it but we move the n element behint if it has no element followed
	public void invokeReceiveWaveOnElements () {
		GameElement elem = null;
		for (int x = 0; x < elements.size(); x++) {
			elem = elements.get(x);
			int cameraPos = this.getCameraPosition();
			int maxRang = this.getCameraPosition() + this.getVisibility() - 1;
			if (cameraPos <= elem.getX() && maxRang >= elem.getX()) {
				if (elements.getObjectInPosition(elem.getX() + 1, elem.getY()) == null) {
					elem.receiveWave();
				}				
			}
		}	
	}


	public String getGeneralState() {
		StringBuilder str = new StringBuilder();
		
		str.append("Level: " + level.ge + "\n");
		
		return str.toString();
	}
}