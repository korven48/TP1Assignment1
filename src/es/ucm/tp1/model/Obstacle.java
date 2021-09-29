package es.ucm.tp1.model;

public class Obstacle {
	// Common to all Objects
	private int x, y;
	private Game game;
	static int counter;
	
	// Obstacle specific
	private int resistance;
	
	public Obstacle () {
		this.resistance = 1;
		// todo
	}
}
