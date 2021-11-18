package es.ucm.tp1.model;


class Obstacle extends GameElement{
	private int resistance;
	static int counter;
	
	private static final String CON_SYMBOL = "░";

	public Obstacle(int x, int y, Game game) {
		super(x, y, game);
		this.resistance = 1;
		symbol = Obstacle.CON_SYMBOL;
	}
	
	@Override
	public boolean receiveShot() {
		boolean result = false; 
		if (resistance > 0) {
			resistance--;
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean receiveCollision(ColliderCallback player) {
		player.reciveDamage();
		return true; // true because the player crashes 
	}


	@Override
	public boolean isAlive() {
		if (resistance > 0) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public void onEnter() {
		Obstacle.counter++;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onDelete() {
		Obstacle.counter--;
	}	
	
	protected static void reset() {
		Obstacle.counter = 0;
	}
}
