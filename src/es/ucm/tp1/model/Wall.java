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
	}
	
	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		String result = "";
		switch (resistance) {
			case 3:
				result = Wall.ONE_WALL;
				break;
			case 2:
				result = Wall.TWO_WALL;
				break;
			case 1:
				result = Wall.ONE_WALL;
				break;
			default: 
				result = "";
				
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
		Wall.counter++;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onDelete() {
		//This should give 5 coins to the player
		Wall.counter--;
	}	
	
	protected static void reset() {
		Wall.counter = 0;
	}
}
