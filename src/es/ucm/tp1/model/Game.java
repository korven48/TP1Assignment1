package es.ucm.tp1.model;
import java.util.LinkedList;
import java.util.Random;

import es.ucm.tp1.control.Controller;
import es.ucm.tp1.model.Direction;
//For the constructor
import es.ucm.tp1.control.Level;

public class Game {
	private Player player = null;
	private CoinList coinList;
	private ObstacleList obstacleList; 
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
	
	//Added because of the SuperCar.java
	Long seed;
	Level level;
	boolean isTestMode;
	
	private void setUniquePlayer(boolean reset) {
		int startingline = (int) this.getRoadWidth() / 2; 
		this.player = Player.getPlayer(reset, startingline);
		//ellapsedtime = System.currentTimeMillis();
	}
	
	private void reset() {
		boolean reset = true;
		setUniquePlayer(reset);
		initObjects();
		victory = false;
	}
	
	//SuperCar.java calls this constructor
	//Do we need changes or just assignments are enough?
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
	
	//Make it beautiful if everything is working! 
	private void initObjects() {
		Coin currentCoin = null;
		Obstacle currentObstacle = null;
		this.coinList = new CoinList();
		this.obstacleList = new ObstacleList();
		boolean obstacleWasCreated;
		Random rand = new Random(seed);
		int objectLane;
		double createObstacle;
		int coinLane;
		double createCoin;
		for (int column = this.getVisibility() / 2; column <= this.level.getLength() - 1; column++) {
			obstacleWasCreated = false;
			objectLane =  (int) (rand.nextDouble() * (this.getRoadWidth()));
			createObstacle = rand.nextDouble();
			coinLane =  (int) (rand.nextDouble() * (this.getRoadWidth()));
			createCoin = rand.nextDouble();
			if (createObstacle < level.getObstacleFrequency()) {
				currentObstacle = new Obstacle(this, column, objectLane);
				this.obstacleList.add(currentObstacle);
				obstacleWasCreated = true;
			}
			if (!obstacleWasCreated || objectLane != coinLane) {
				if (createCoin < level.getCoinFrequency()) {
					currentCoin = new Coin(this, column, coinLane);
					this.coinList.add(currentCoin);
				}
			}
		}
	}
	
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
	
	public String getGameStatus() {
		StringBuilder str = new StringBuilder(); 
		int distanceToFinish = level.getLength() - player.getPostionX();
		
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
	
	public boolean getVictory() {
		return victory;
	}
	
	public boolean checkFinishLine() {
		boolean finnished;
		finnished = player.getPostionX() == level.getLength() + 1; // It actually goes through the finish line, not the best imo
		return finnished;
	}

	private boolean checkCollistion() {
		boolean result = false;
		Obstacle obstacle;
		for (int i = 0; i < this.obstacleList.size(); i++) {
			obstacle = this.obstacleList.get(i);			
			result |= obstacle.checkHit(this.player);
		}
		return result;
	}
	
	private boolean checkCoinSelected() {
		boolean result = false;
		Coin coin;
		for (int i = 0; i < this.coinList.size(); i++) {
			coin = this.coinList.get(i);
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
		if (direction.equals(Direction.DOWN) && player.getPostionY() >= level.getWidth() - 1)
			result = false;
		else if (direction.equals(Direction.UP) && player.getPostionY() <= 0)
			result = false;
		return result;
	}
	
	public int getCameraPosition() {
		return player.getPostionX();
	}
	
	public int getRoadWidth() {
		return level.getWidth();
	}
	public int getVisibility() {
		return level.getVisibility();
	}
	
	private void ShowLastCommand(final String command) {
		String debugInfo = Game.DEBUG_MSG;
		if (command != "") {
			debugInfo += command;			
		}
		System.out.println("\n" + debugInfo);
	}
	
	public boolean update(final String command) {
		boolean shouldDisplay = false;
		this.ShowLastCommand(command);

		String currentCommand = command.toLowerCase();
		Direction direction = Direction.NONE;
		switch (currentCommand) {
			case "info":
			case "i":
				// Displays information about each of the elements of the game.
				System.out.println(getInfo());
				break;
			case "q":
				//q: Moves the car upwards on the board (diagonally upwards on the road).
				direction = Direction.UP;
				break;
			case "a":
				//a: Moves the car downwards on the board (diagonally downwards on the road).
				direction = Direction.DOWN;
				break;
			case "none":
			case "n":
			case "":
				//none: Moves the car horizontally on the road. (Same as "")
				direction = Direction.FORWARD;
				break;
			case "reset":
			case "r":
				//reset: Restarts the game in the initial configuration.
				reset();
				break;
			case "test":
			case "t":
				//test: Changes the game mode to test, in which the elapsed time is not printed; this
				//mode is explained in more detail below.
				setTest(true);
				//System.out.println("Changed game mode to test!");
				break;
			case "exit":
			case "e":
				//exit: Exists the game after displaying the Game Over message
				// At the moment shows crashed player if you want to change it goto GamePrinter.endMessage()
				setExit(true);
				break;
			case "help":
			case "h":
				//help: Displays information about each of the existing commands, each on a new line
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
	
	private String getInfo() {
		// TODO Should create an informative message
		String s = String.format("Available objects:%n"
				+ "[Car] the racing car%n"
				+ "[Coin] gives 1 coin to the player%n"
				+ "[Obstacle] hits car"
				+ "%n"); 
		return s;
	}

	public String positionToString(int x, int y) {
		String obj = "";
		if (this.coinList != null) {
			Coin coin;
			for (int i = 0; i < this.coinList.size(); i++) {
				coin = this.coinList.get(i);
				if (coin.getX() == x && coin.getY() == y) {
					obj = COIN;
				}
			}
		}	
		if (this.obstacleList != null) {
			Obstacle obstacle;
			for (int i = 0; i < this.obstacleList.size(); i++) {
				obstacle = this.obstacleList.get(i);		
				if (obstacle.getX() == x && obstacle.getY() == y) {
					obj = OBSTACLE;
				}
			}
		}
		if (x == level.getLength())
			obj = FINISH_LINE;
		if (this.player != null && this.obstacleList != null) {
			if (this.player.getPostionX() == x && this.player.getPostionY() == y) {
				if (this.player.isCrashed()) {
					obj = CRASHED_PLAYER;
				} else {
					obj = ALIVE_PLAYER;
				}
			}
		}
		return obj;
	}

	public void removeDeadObjects() {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < this.coinList.size(); i++) {
			if (coinList.get(i).isCollected()) {
				coinList.remove(i);
			}
		}
		
		/* - Needed
		for (int i = 0; i < this.obstacleList.length; i++) {
			if (this.obstacleList[i].isObjectDead()) this.obstacleList[i] = null;
		}
		*/
	}
	
}
