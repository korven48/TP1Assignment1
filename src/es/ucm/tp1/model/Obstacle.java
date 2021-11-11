package es.ucm.tp1.model;


class Obstacle extends GameElement{
	private static final String NAME = "obstacle";
	private int resistance;
	static int counter;

	public Obstacle(Game game, int x, int y) {
		super(x, y, game, NAME);
		this.resistance = 1;
		symbol = "░";
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
