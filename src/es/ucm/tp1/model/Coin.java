package es.ucm.tp1.model;

public class Coin {			// Should be the last thing to implement
	// Common to all Objects
	private int x, y;
	private Game game;
	static int counter;
	boolean collected = false; 
	
	// Coin specific
	
	public void setCollected() {
		this.collected = true;
	}
	
	public boolean getCollected() {
		return this.collected;
	}	

	public Coin () {
		// todo
	}
		
}
