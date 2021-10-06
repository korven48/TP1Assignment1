package es.ucm.tp1.model;

//For the constructor
import es.ucm.tp1.control.Level;

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
		if (this.player == null) {
			this.player = Player.getPlayer(false);
		}
	}
	
	//SuperCar.java calls this constructor
	//Do we need changes or just assignments are enough?
	public Game(Long seed, Level level, boolean isTestMode) {
		this.seed = seed;
		this.level = level;
		this.isTestMode = isTestMode;
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
		//ToDo
		return true; 
	}
	
	public String positionToString(int x, int y) {
		//ToDo
		return "";
	}
	
	public int getRoadWidth() {
		//ToDo
		return 1;
	}
	
	public int getVisibility() {
		//ToDo
		return 1;
	}
}
