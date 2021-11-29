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
	public void onEnter() {
		super.onEnter();
		Wall.counter++;
	}

	@Override
	public void onDelete() {
		super.onDelete();
		Wall.counter--;
	}	
	
	@Override
	public boolean receiveShot(ColliderCallback player) {
		boolean result = super.receiveShot(player);
		if (this.resistance == 0) {
			player.addCoins(AMOUNT_COIN_GAINABLE);
		}
		return result;
	}
	
	public static void reset() {
		Obstacle.reset();
		Wall.counter = 0;
	}
	
	@Override
	public Wall create(Game game, int x, int y) {
		return new Wall(game, x, y);
	}
}
