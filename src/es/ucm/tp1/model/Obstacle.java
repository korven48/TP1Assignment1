package es.ucm.tp1.model;


class Obstacle extends GameElement{
	private static final String NAME = "obstacle";
	private int resistance;
	static int counter;
	
	private static final String CON_SYMBOL = "░";

	public Obstacle(Game game, int x, int y) {
		super(x, y, game, NAME);
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
	
	public Obstacle() {
		super(NAME);
	}
	
	@Override
	public boolean receiveCollision(ColliderCallback player) {
		player.reciveDamage();
		return true; // true because the player crashes 
	}

	@Override
	public Obstacle create(Game game, int x, int y) {
		return new Obstacle(game, x, y);
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
