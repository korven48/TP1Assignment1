package es.ucm.tp1.model;
import java.util.Random;

import es.ucm.tp1.control.Controller;
//For the constructor
import es.ucm.tp1.control.Level;

public class Game {
	private Player player = null;
	private ObjectList coinList;
	private ObjectList obstacleList; 
	private long ellapsedtime;
	private int cycle;

	private boolean victory;
	
	private boolean exit;
	
	private static final String COIN = "¢";
	private static final String ALIVE_PLAYER = ">";
	private static final String CRASHED_PLAYER = "@";
	private static final String OBSTACLE = "░";
	private static final String FINISH_LINE = "¦";
	private static final String DEBUG_MSG = "[DEBUG] Executing: ";
	
	//Maybe should be removed
	private static final String INFO = String.format(
			"Available objects:%n"
			+ "[Car] the racing car%n"
			+ "[Coin] gives 1 coin to the player%n"
			+ "[Obstacle] hits car"
			+ "%n"); 

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
	
	private void setUniquePlayer(boolean reset) {
		int startingline = (int) this.getRoadWidth() / 2; 
		this.player = Player.getPlayer(reset, startingline);
	}
	
	public void reset() {
		boolean reset = true;
		setUniquePlayer(reset);
		initObjects();
		victory = false;
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
	
	//Maybe should be removed
	private String getInfo() {
		return INFO;
	}
	
	// ---------------------------  End of Setters and Getters  ---------------------------
	
	private void initObjects() {
		Coin currentCoin = null;
		Obstacle currentObstacle = null;
		this.coinList = new ObjectList();
		this.obstacleList = new ObjectList();
		boolean obstacleWasCreated;
		Random rand = new Random(seed);
		int obstacleLane, coinLane;
		double createObstacle, createCoin;
		for (int column = this.getVisibility() / 2; column <= this.level.getLength() - 1; column++) {
			obstacleWasCreated = false;
			obstacleLane =  (int) (rand.nextDouble() * (this.getRoadWidth()));
			createObstacle = rand.nextDouble();
			coinLane =  (int) (rand.nextDouble() * (this.getRoadWidth()));
			createCoin = rand.nextDouble();
			if (createObstacle < level.getObstacleFrequency()) {
				currentObstacle = new Obstacle(column, obstacleLane);
				this.obstacleList.add(currentObstacle);
				obstacleWasCreated = true;
			}
			if (!obstacleWasCreated || obstacleLane != coinLane) {
				if (createCoin < level.getCoinFrequency()) {
					currentCoin = new Coin(column, coinLane);
					this.coinList.add(currentCoin);
				}
			}
		}
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
		str.append(String.format("Total obstacles: " + obstacleList.size() + "%n"));
		str.append(String.format("Total coins: " + coinList.size() + "%n")); 
		if (!isTest()) {
			str.append(String.format("Ellapsed Time: " + getTime() + "%n"));
		}
		return str.toString().stripTrailing();
	}

	public boolean isFinished() {
		// Game finishes if player crashes, wins or exits
		boolean crashed, result;
		victory = checkFinishLine();
		crashed = checkCollistion();
		
		result = crashed || victory || exit;
		
		return result; 
	}

	public boolean checkFinishLine() {
		boolean finnished;
		finnished = player.getX() == level.getLength() + 1; // It actually goes through the finish line, not the best imo
		return finnished;
	}

	private boolean checkCollistion() {
		boolean result = false;
		Obstacle obstacle;
		for (int i = 0; i < this.obstacleList.size(); i++) {
			obstacle = (Obstacle) obstacleList.get(i);			
			result |= obstacle.checkHit(this.player);
		}
		return result;
	}
	
	private boolean checkCoinSelected() {
		boolean result = false;
		Coin coin;
		for (int i = 0; i < coinList.size(); i++) {
			coin = (Coin) coinList.get(i);
			if (coin.canCollect(player)) {
				coin.setCollected();
				this.player.setCoinCounterUp();
				result = true;
			}
		}		
		return result;
	}
	
	private boolean canMove(Direction direction) {
		boolean result = true;
		if (direction.equals(Direction.DOWN) && player.getY() >= level.getWidth() - 1)
			result = false;
		else if (direction.equals(Direction.UP) && player.getY() <= 0)
			result = false;
		return result;
	}
	
	private void ShowLastCommand(final String command) {
		String debugInfo = Game.DEBUG_MSG;
		if (command != "") {
			debugInfo += command;			
		}
		System.out.println("\n" + debugInfo);
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
		if (checkCollistion()) {
			player.setCollision();
		}
		checkCoinSelected();
		return shouldDisplay;
	}

	public String positionToString(int x, int y) {
		String obj = "";
		if (coinList.isObjectInPos(x, y)) {
			obj = COIN;
		} else if (obstacleList.isObjectInPos(x, y)) {
			obj = OBSTACLE;
		}
		if (x == level.getLength())
			obj = FINISH_LINE;
		if (player.isInPos(x, y)) {
			if (player.isCrashed()) {
				obj = CRASHED_PLAYER;
			} else {
				obj = ALIVE_PLAYER;
			}
		}
		return obj;
	}

	public void removeDeadObjects() {
		coinList.removeDead();
		obstacleList.removeDead();
	}
}


/*
 	public boolean update(final String command) {
		boolean shouldDisplay = false;
		this.ShowLastCommand(command);

		String currentCommand = command.toLowerCase();
		Direction direction = Direction.NONE;
		switch (currentCommand) {
			case "info":
			case "i":
				System.out.println(getInfo());
				break;
			case "q":
				// Move upwards
				direction = Direction.UP;
				break;
			case "a":
				// Move downwards
				direction = Direction.DOWN;
				break;
			case "none":
			case "n":
			case "":
				// Move foreward
				direction = Direction.FORWARD;
				break;
			case "reset":
			case "r":
				// Restarts the game in the initial configuration.
				reset();
				break;
			case "test":
			case "t":
				// Changes the game mode to test, in which the elapsed time is not printed
				setTest(true);
				break;
			case "exit":
			case "e":
				// Exists the game after displaying the Game Over message
				setExit(true);
				break;
			case "help":
			case "h":
				// Displays information about each of the existing commands, each on a new line
				Controller.printHelp();
				break;
			default:
				Controller.printUnknown();
				break; 
		}
		if (canMove(direction)) {
			if (! (direction.equals(Direction.NONE)) || currentCommand.equals("test") || currentCommand.equals("t")) {
				player.move(direction);
				shouldDisplay = true;				
			}
		} else {
			System.out.println("\n\tWARNING: Coudn't move the player in that direction\n");
		}
		if (checkCollistion()) {
			player.setCollision();
		}
		checkCoinSelected();
		return shouldDisplay;
	}
*/
