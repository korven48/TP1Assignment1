package es.ucm.tp1.model;

public class Obstacle extends GameObject{
	private int resistance;
	static int counter;

	public Obstacle (int x, int y) {
		super(x, y);
		this.resistance = 1;
		Obstacle.counter++;
	}
	
	@Override
	public boolean isAlive() {
		if (resistance > 0) {
			return true;
		}
		return false;
	}
	
		
	public boolean checkHit(Player player) {
		boolean result = false;
		if (player.getY() == this.y && player.getX() == this.x) {
			result = true; 
		}
		return result;
	}	
}
