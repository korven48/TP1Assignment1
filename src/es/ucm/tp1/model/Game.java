package es.ucm.tp1.model;

//For the constructor
import es.ucm.tp1.control.Level;
import java.util.LinkedList;
import java.util.Random;
import java.lang.Math;

public class Game {
	private int cycleCounter = 0;
	private Player player = null;
	private Coin[] coinList;
	private Obstacle[] obstacleList; 
	
	//Added because of the SuperCar.java
	Long seed;
	Level level;
	boolean isTestMode;
	
	private void setUniquePlayer() {
		int startingline = (int) this.getRoadWidth() / 2; 
		if (this.player == null) {
			this.player = Player.getPlayer(false, startingline);
		}
	}
	
	//SuperCar.java calls this constructor
	//Do we need changes or just assignments are enough?
	public Game(Long seed, Level level, boolean isTestMode) {
		this.seed = seed;
		this.level = level;
		this.isTestMode = isTestMode;
		initObjects();
		setUniquePlayer();
	}
	
	//Make it beautiful if everything is working! 
	private void initObjects() {
		Coin currentCoin = null;
		LinkedList <Coin> linkedListCoins = new LinkedList<Coin>();
		Obstacle currentObstacle = null;
		LinkedList<Obstacle> linkedListObstacle = new LinkedList<Obstacle>();
		Random rand = new Random();
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
		System.out.println(this.coinList.length);
		this.obstacleList = linkedListObstacle.toArray(new Obstacle[linkedListObstacle.size()]); 
		System.out.println(this.obstacleList.length);
	}
	
	public void update(final String command) {
		int index = 1;
		String currentCommand = command.toLowerCase();
		if (currentCommand.length() != 0) {
			char letter = currentCommand.charAt(index);
			currentCommand = String.valueOf(letter); 
		}
		switch (command) {
			case "i":
				break;
			case "q":
				break; 
			case "a":
				break;
			case "n":
				break;
			case "":
				break;
			case "r":
				break;
			case "t":
				break;
			case "e":
				break;
			case "h":
				break;
			default:
				break; 
		}
	}
	
	public boolean isFinished() {
		boolean result = false; 
		result = checkCollistion();
		return result; 
	}

	private boolean checkCollistion() {
		boolean result = false;
		for (Obstacle obstacle : this.obstacleList) {
			result = obstacle.checkHit(this.player);
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
	
	public int getRoadWidth() {
		return level.getWidth();
	}
	public int getVisibility() {
		return level.getVisibility();
	}
	public String positionToString(int x, int y) {
		String obj = "";
		if (this.coinList != null) {
			for (Coin coin : this.coinList) {
				if (coin.getX() == x && coin.getY() == y) {
					obj = "¢";
				}
			}		
		}	
		if (this.obstacleList != null) {
			for (Obstacle obstacle : this.obstacleList) {
				if (obstacle.getX() == x && obstacle.getY() == y) {
					obj = "░";
				}
			}
		}
		if (this.player != null && this.obstacleList != null) {
			if (this.player.getPostionX() == x && this.player.getPostionY() == y) {
				if (this.checkCollistion()) {
					obj = "@";
				} else {
					obj = ">";
				}
			}
		}
		return obj;
	}
}
