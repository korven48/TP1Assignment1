package es.ucm.tp1.model.Elements;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.ColliderCallback;

public final class Wall extends Obstacle {
	static int counter;
	
	private static final String THREE_WALL = "█";
	private static final String TWO_WALL = "▒";
	private static final String ONE_WALL = "░";
	private static final int AMOUNT_COIN_GAINABLE = 5;
	
	private static final String NAME = "wall";
	
	public Wall(Game game, int x, int y) {
		super(game, x, y, NAME); 
		this.resistance = 3;
	}
	
	public Wall() {
		super(NAME);
	}

	@Override
	public boolean isAdvanced() {
		return true;
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
	public String toString() {
		String result = "";
		switch (resistance) {
			case 3:
				result = Wall.THREE_WALL;
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
	public void onDelete() {
		game.playerReceiveCoin(Wall.AMOUNT_COIN_GAINABLE);
		Wall.counter--;
	}	
	
	public static void reset() {
		Wall.counter = 0;
	}
	
	@Override
	public Wall create(Game game, int x, int y) {
		// TODO Auto-generated method stub
		return new Wall(game, x, y);
	}
}
