package es.ucm.tp1.model;

public class Obstacle {
	// Common to all Objects
	private int x, y;
	private Game game;
	static int counter;
	
	// Obstacle specific
	private int resistance;
	

	public Obstacle (Game game, int x, int y) {
		// todo
		this.game = game; 
		this.resistance = 1;
		this.x = x;
		this.y = y; 
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
		
	public boolean checkHit(Player player) {
		boolean result = false;
		if (player.getPostionY() == this.y && x == 1) {
			result = true; 
		}
		return result;
	}	
}
