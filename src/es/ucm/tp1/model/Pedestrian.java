package es.ucm.tp1.model;

public class Pedestrian extends Obstacle {

	private static final String NAME = "pedestrian";
	private static final String CON_SYMBOL = "☺";
	
	/*You must not allow the player and a pedestrian to pass
	  through each other without colliding; as for the implementation of the Turbo command,
	  this will involve checking for collisions twice on every cycle. */ 
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y, NAME); 
		symbol = CON_SYMBOL;
	}
	
	public Pedestrian() {
		super(NAME);
	}
	
	@Override
	public boolean isAdvanced() {
		return true;
	}
	
	@Override
	public void update() {
		super.update();
		// Goes top to bottom
		y++;
	}
	
	@Override
	public boolean receiveShot() {
		// If the pedestrian is hit by a bullet or a grenade, the player loses all their coins but does not
		// die and the game continues.
		return true;
	}
	
	@Override
	public Pedestrian create(Game game, int x, int y) {
		return new Pedestrian(game, x, y);
	}
}
