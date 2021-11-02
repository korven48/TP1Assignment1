package es.ucm.tp1.model;

class Obstacle extends GameElement{
	private int resistance;
	static int counter;

	protected Obstacle (int x, int y) {
		super(x, y);
		this.resistance = 1;
		Obstacle.counter++;
	}
	
	protected Obstacle(Game game, int x, int y) {
		super(game, x, y);
		Coin.counter++;
	}
	
	@Override
	protected boolean isAlive() {
		if (resistance > 0) {
			return true;
		}
		return false;
	}
	
		
	protected boolean checkHit(Player player) {
		boolean result = false;
		if (player.getY() == this.y && player.getX() == this.x) {
			result = true; 
		}
		return result;
	}	
	
	//Has to be changed?
	protected static void reset() {
		Obstacle.counter = 0;
	}
}
