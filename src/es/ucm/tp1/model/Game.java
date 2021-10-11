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
			int randRow = rand.nextInt(this.getRoadWidth() - 1);
			if (random < level.getCoinFrequency()) {
				currentCoin = new Coin(this, column, randRow);
				linkedListCoins.add(currentCoin);
				if (random < level.getObstacleFrequency()) {
					int randRow2; 
					do {
						randRow2 = rand.nextInt(this.getRoadWidth() - 1);
					} while(randRow2 == random);
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
	
	public void draw() {
		int distanceToFinish = level.getLength() - player.getPostionX();
		System.out.println("Distance: " + distanceToFinish);
		System.out.println("Coins: " + player.getCoins());
		System.out.println("Cicle: " + cycleCounter);
		System.out.println("Total obstacles: " + obstacleList.length);
		System.out.println("Total coins: " + coinList.length);
		System.out.println("Ellapsed time: " + getTime());
	}

	
	public boolean isFinished() {
		// Game finishes if player crashes or wins
		boolean result;
		victory = checkFinishLine();
		result = checkCollistion() || victory;
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
			if (coin.getCollected(this.player)) {
				this.player.setCoinCounterUp();
				result = true; 
			}
		}		
		return result;
	}
	
	private boolean canMove(Direction direction) {
		boolean result = true;
		if (direction.equals(Direction.DOWN) && player.getPostionY() >= level.getWidth() - 1) {
			result = false;
		} else if (direction.equals(Direction.UP) && player.getPostionY() <= 0) {
			result = false;
		}
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
	
	public void update(final String command) {
		int index = 0;
		String currentCommand = command.toLowerCase();
		if (currentCommand.length() != 0) {
			char letter = currentCommand.charAt(index);
			currentCommand = String.valueOf(letter); 
		}
		Direction direction;
		switch (currentCommand) {
			case "i":
				// Displays information about each of the elements of the game.
				direction = Direction.NONE;
				break;
			case "q":
				//q: Moves the car upwards on the board (diagonally upwards on the road).
				direction = Direction.UP;
				break; 
			case "a":
				//a: Moves the car downwards on the board (diagonally downwards on the road).
				direction = Direction.DOWN;
				break;
			case "n":
			case "":
				//none: Moves the car horizontally on the road. (Same as "")
				direction = Direction.FORWARD;
				break;
			case "r":
				//reset: Restarts the game in the initial configuration.
				reset();
				direction = Direction.NONE;
				break;
			case "t":
				//test: Changes the game mode to test, in which the elapsed time is not printed; this
				//mode is explained in more detail below.
				direction = Direction.NONE;
				break;
			case "e":
				//exit: Exists the game after displaying the message “Game Over”.
				direction = Direction.NONE;
				break;
			case "h":
				//help: Displays information about each of the existing commands, each on a new line
				//using the following format: <command_name> <command_parameters>: <command_description>new Player 
				Controller.printHelp();
				direction = Direction.NONE;
				break;
			default:
				Controller.printUnknown();
				direction = Direction.NONE;
				break; 
		}
		if (canMove(direction)) {
			player.move(direction);
		} else {
			System.out.println("\n\tWARNING: Coudn't move the player in that direction\n");
		}
		if (checkCollistion()) {
			player.setCollision();
		}
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
