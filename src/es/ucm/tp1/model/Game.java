package es.ucm.tp1.model;

public class Game {
	private int cycleCounter = 0;
	private Player player = null;
	private Coin[] coinList;
	private Obstacle[] obstacleList; 
	
	private void setUniquePlayer() {
		if (this.player == null) {
			this.player = Player.getPlayer();
		}
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
		return true; 
	}
}
