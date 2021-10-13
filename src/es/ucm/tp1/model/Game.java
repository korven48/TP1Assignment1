package es.ucm.tp1.model;
import java.util.LinkedList;
import java.util.Random;

import es.ucm.tp1.control.Controller;
import es.ucm.tp1.model.Direction;
//For the constructor
import es.ucm.tp1.control.Level;

public class Game {
	private int cycleCounter = 0;
	private Player player = null;
	private Coin[] coinList;
	private Obstacle[] obstacleList; 
	private long ellapsedtime;
	private boolean victory;
	
	private boolean exit;

	
	
	private static final String COIN = "¢";
	private static final String ALIVE_PLAYER = ">";
	private static final String CRASHED_PLAYER = "@";
	private static final String OBSTACLE = "░";
	private static final String FINISH_LINE = "¦";
	
	//Added because of the SuperCar.java
	Long seed;
	Level level;
	boolean isTestMode;
	
	private void setUniquePlayer(boolean reset) {
		int startingline = (int) this.getRoadWidth() / 2; 
		this.player = Player.getPlayer(reset, startingline);
		ellapsedtime = System.currentTimeMillis();
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
		initObjects();
		boolean reset = false;
		setUniquePlayer(reset);
		victory = false;
		exit = false;
	}
	
	//Make it beautiful if everything is working! 
	private void initObjects() {
		Coin currentCoin = null;
		LinkedList <Coin> linkedListCoins = new LinkedList<Coin>();
		Obstacle currentObstacle = null;
		LinkedList<Obstacle> linkedListObstacle = new LinkedList<Obstacle>();
		Random rand = new Random(seed);
		for (int column = this.getVisibility() / 2; column <= this.level.getLength() - 1; column++) {
			double random = Math.random();
			int randRow = rand.nextInt(this.getRoadWidth());
			if (random < level.getCoinFrequency()) {
				currentCoin = new Coin(this, column, randRow);
				linkedListCoins.add(currentCoin);
				if (random < level.getObstacleFrequency()) {
					int randRow2; 
					do {
						randRow2 = rand.nextInt(this.getRoadWidth() - 1);
					} while(randRow2 == randRow);
					currentObstacle = new Obstacle(this, column, randRow2);
					linkedListObstacle.add(currentObstacle);	
				}
			} else if (random < level.getObstacleFrequency()) {
				currentObstacle = new Obstacle(this, column, randRow);
				linkedListObstacle.add(currentObstacle);
			}
		}
		this.coinList = linkedListCoins.toArray(new Coin[linkedListCoins.size()]);
//		System.out.println(this.coinList.length);
		this.obstacleList = linkedListObstacle.toArray(new Obstacle[linkedListObstacle.size()]); 
//		System.out.println(this.obstacleList.length);
	}
	
	public void startTime() {
		ellapsedtime = System.currentTimeMillis();
	}
	
	public String getTime() {
		// time: The time in seconds with 2 decimals of precision
		double seconds = (System.currentTimeMillis() - ellapsedtime) / 1000.;
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
	
	public void getGameStatus() {
		int distanceToFinish = level.getLength() - player.getPostionX();
		System.out.println("Distance: " + distanceToFinish);
		System.out.println("Coins: " + player.getCoins());
		System.out.println("Cicle: " + cycleCounter);
		System.out.println("Total obstacles: " + obstacleList.length);
		System.out.println("Total coins: " + coinList.length);
		if (! isTest())
			System.out.println("Ellapsed time: " + getTime());
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
		finnished = player.getPostionX() == level.getLength();
		return finnished;
	}

	private boolean checkCollistion() {
		boolean result = false;
		for (Obstacle obstacle : this.obstacleList) {
			result |= obstacle.checkHit(this.player);
		}
		return result;
	}
	
	private boolean checkCoinSelected() {
		boolean result = false;
		for (Coin coin : this.coinList) {
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
	
	public boolean update(final String command) {
		boolean shouldDisplay = false;
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
				System.out.println("Changed game mode to test!");
				break;
			case "exit":
			case "e":
				//exit: Exists the game after displaying the Game Over message
				// At the moment shows crashed player if you want to change it goto GamePrinter.endMessage()
				setExit(true);
				break;
			case "h":
				//help: Displays information about each of the existing commands, each on a new line
				Controller.printHelp();
				break;
			default:
				Controller.printUnknown();
				break; 
		}
		if (canMove(direction)) {
			if (! direction.equals(Direction.NONE)) {
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
		String s = "Informative message..."; 
		return s;
	}

	public String positionToString(int x, int y) {
		String obj = "";
		if (this.coinList != null) {
			for (Coin coin : this.coinList) {
				if (coin.getX() == x && coin.getY() == y) {
					obj = COIN;
				}
			}		
		}	
		if (this.obstacleList != null) {
			for (Obstacle obstacle : this.obstacleList) {
				if (obstacle.getX() == x && obstacle.getY() == y) {
					obj = OBSTACLE;
				}
			}
		}
		if (this.player != null && this.obstacleList != null) {
			if (this.player.getPostionX() == x && this.player.getPostionY() == y) {
				if (this.player.isCrashed()) {
					obj = CRASHED_PLAYER;
				} else {
					obj = ALIVE_PLAYER;
				}
			}
		}
		if (x == level.getLength())
			obj = FINISH_LINE;
		return obj;
	}

	public void removeDeadObjects() {
		// TODO Auto-generated method stub
	}
	
}
