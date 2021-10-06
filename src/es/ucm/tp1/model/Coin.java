package es.ucm.tp1.model;

public class Coin {			// Should be the last thing to implement
	// Common to all Objects
	private int x, y;
	private Game game;
	static int counter;
	boolean collected = false; 
	
	// Coin specific
	
	public Coin (Game game, int x, int y) {
		// todo
		this.game = game; 
		this.x = x;
		this.y = y;
	}
			
	public void setCollected() {
		this.collected = true;
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

	public boolean getCollected(Player player) {
		if (player.getPostionY() == this.y && x == 1) {
			collected = true; 
		}
		return this.collected;
	}	

}
