package es.ucm.tp1.model;


class Obstacle extends GameElement{
	private static final String NAME = "obstacle";
	protected int resistance;
	static int counter;
	
	private static final String CON_SYMBOL = "░";

	public Obstacle(int x, int y, Game game) {
		super(x, y, game, NAME);
		this.resistance = 1;
		symbol = Obstacle.CON_SYMBOL;
	}
	
	public Obstacle(int x, int y, Game game, String name) {
		super(x, y, game, name);
		this.resistance = 1;
		symbol = Obstacle.CON_SYMBOL;
	}
	
	public Obstacle(String name) {
		super(name);
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
	public boolean receiveExplosion() {
		return receiveShot();
	}
	
	@Override
	public boolean receiveThunder() {
		resistance = 0;
		return true;
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
	public Obstacle create(int x, int y, Game game) {
		return new Obstacle(x, y, game);
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
