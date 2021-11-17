package es.ucm.tp1.model;

final class Wall extends Obstacle {
	private int resistance;
	static int counter;
	
	private static final String THREE_WALL = "█";
	private static final String TWO_WALL = "▒";
	private static final String ONE_WALL = "░";
	
	public Wall(int x, int y, Game game) {
		// TODO Auto-generated constructor stub
		super(x, y, game); 
		this.resistance = 3;
		symbol = Wall.THREE_WALL;
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
		Wall.counter++;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onDelete() {
		Wall.counter--;
	}	
	
	protected static void reset() {
		Wall.counter = 0;
	}
}
