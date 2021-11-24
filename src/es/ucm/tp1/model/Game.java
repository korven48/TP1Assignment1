package es.ucm.tp1.model;
import java.util.Random;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.model.Elements.Coin;
import es.ucm.tp1.model.Elements.GameElement;
import es.ucm.tp1.model.Elements.Obstacle;
import es.ucm.tp1.model.InstantActions.InstantAction;
import es.ucm.tp1.model.Elements.GameElementContainer;

public class Game {
	private Player player = null;
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
		//this is just setting the player to the middle without considering the previous postion!
		//Problem!
		this.player.setX((int)(level.getWidth() / 2));
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
	
	public void incrementCyle() {
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
	
	public final void tryToAddObject(GameElement gameElement, double elementFrequency) {
		GameElement element = null;
		// TODO: We should only create one random, right?
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
		GameElement elem;
		Collider out;
		int index = -1;
		for (int i = 0; i < elements.size(); i++) {
			elem = elements.get(i);
			if (elem.isInPos(x, y)) {
				index = i;
			}
		}
		if (index == -1) {
			out = null;
		} else {
			out = (Collider) elements.get(index);			
		}
		return out;
	}
	
	private boolean canMove(Direction direction) {
		boolean result = true;
		if (direction.equals(Direction.DOWN) && player.getY() >= level.getWidth() - 1)
			result = false;
		else if (direction.equals(Direction.UP) && player.getY() <= 0)
			result = false;
		return result;
	}
		
	
	public boolean movePlayer(boolean shouldDisplay, Direction direction) {
		//if (this.player.doCollision()) {
			if (canMove(direction)) {
				if (!(direction.equals(Direction.NONE))) {
					player.move(direction);
					shouldDisplay = true;				
				}
			} else {
				System.out.println("\n\tWARNING: Coudn't move the player in that direction\n");
			}
		//}
		this.player.doCollision();
		return shouldDisplay;
	}
	
	public String positionToString(int x, int y) {
		String position = "";
		GameElement elem = null;
		if (x ==  level.getLength()) {
			position = FINISH_LINE;
		} 
		if(player.isInPos(x, y)) {
			position = player.getSymbol();
		} else {
			GameElement elem2;
			for (int i = 0; i < elements.size(); i++) {
				elem2 = elements.get(i);
				if (elem2.isInPos(x, y)) {
					elem = elem2;
				}
			}
			if (elem != null) {
				position = elem.getSymbol();
			} 
		}
		return position;
	}
		
	public void removeDeadObjects() {
		elements.removeDead();	
	}

	public void update() {
		player.update();
		elements.update();
		removeDeadObjects();
	}
	
	public void playerReceiveCoin (int amount) {
		player.addCoins(amount);
	}
	
	public void doInstantAction(InstantAction action) {
		action.execute(this);		
	}
	
	public boolean playerPays (int amount) {
		if (player.getCoins() >= amount) {
			player.pay(amount);
			return true;
		}
		return false;
	}

	public int getPlayerLane() {
		return player.getY();
	}
	
	public int getAmountOfCoinsPlayer() {
		return player.getCoins();
	}
	
	public void execute (InstantAction action) {
		
	}
	
	public Level getLevel() {
		return this.level;
	}
}


