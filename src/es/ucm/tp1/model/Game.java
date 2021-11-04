package es.ucm.tp1.model;
//import java.util.Iterator;
import java.util.Random;

import es.ucm.tp1.control.Level;

public class Game {
	private Player player = null;

	
	// I think we should only have one unique list.
//	private GameElementContainer coinList;
//	private GameElementContainer obstacleList; 
//	private GameElementContainer list;

	private GameElementContainer elements; 

	private long ellapsedtime;
	private int cycle;

	private boolean victory;
	
	private boolean exit;
	
	private static final String FINISH_LINE = "¦";
	
	Long seed;
	Level level;
	boolean isTestMode;
	
	public Game(Long seed, Level level, boolean isTestMode) {
		this.seed = seed;
		this.level = level;
		this.isTestMode = isTestMode;
		this.cycle = 0; 
		this.ellapsedtime = 0;
		initObjects();
		boolean reset = false;
		setUniquePlayer(reset);
		victory = false;
		exit = false;
	}
	
	// ---------------------------  Begin - Have to get knowledge ---------------------------
	protected final void tryToAddObject(GameElement gameElement, double elementFrequency) {
		GameElement element = null;
		Random rand = new Random(seed);
		double createElement = rand.nextDouble();
		if (createElement < elementFrequency) {
			for (int i = 0; i < elements.counter; i++) {
				element = elements.get(i);
				if (element.getX() == gameElement.getX() && element.getY() == gameElement.getY()) {
					return;
				}
			}
			elements.add(gameElement);
		}
	}
	
	protected final int getRandomLane() {
		Random rand = new Random(seed);
		int lane = (int) (rand.nextDouble() * (this.getRoadWidth()));
		return lane;
	}
			
	// ---------------------------  End - Have to get knowledge ---------------------------
	
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
	
	//the inner code has to be replaced
	private void initObjects() {
		Coin currentCoin = null;
		Obstacle currentObstacle = null;
		elements = new GameElementContainer();
//		this.coinList = new ElementList();
//		this.obstacleList = new ElementList();
		boolean obstacleWasCreated;
		Random rand = new Random(seed);
		int obstacleLane, coinLane;
		double createObstacle, createCoin;
		for (int column = this.getVisibility() / 2; column <= this.getRoadLength() - 1; column++) {
			obstacleWasCreated = false;
			obstacleLane =  (int) (rand.nextDouble() * (this.getRoadWidth()));
			createObstacle = rand.nextDouble();
			coinLane =  (int) (rand.nextDouble() * (this.getRoadWidth()));
			createCoin = rand.nextDouble();
			if (createObstacle < level.obstacleFrequency()) {
				currentObstacle = new Obstacle(this, obstacleLane, column);
				this.elements.add(currentObstacle);
				obstacleWasCreated = true;
			}
			if (!obstacleWasCreated || obstacleLane != coinLane) {
				if (createCoin < level.coinFrequency()) {
					currentCoin = new Coin(this, coinLane, column);
					this.elements.add(currentCoin);
				}
			}
		}
	}

	protected int getRoadLength() {
		return this.level.getLength();
	}
	
	// // ---------------------------  Begins - Time methods ---------------------------
	
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


	public GameElement getObjectInPosition(int x, int y) {
		GameElement elem, out;
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
			out = elements.get(index);			
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
		if (canMove(direction)) {
			if (!(direction.equals(Direction.NONE))) {
				player.move(direction);
				shouldDisplay = true;				
			}
		} else {
			System.out.println("\n\tWARNING: Coudn't move the player in that direction\n");
		}
		return shouldDisplay;
	}
	
	public String positionToString(int x, int y) {
		String position = "";
		GameElement elem;
		if (x ==  level.getLength()) {
			position = FINISH_LINE;
		} 
		if(player.isInPos(x, y)) {
			position = player.getSymbol();
		} else {
			elem = getObjectInPosition(x, y);
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
		// Should update the rest of elements 
		player.update();
		removeDeadObjects();
	}
}


