package es.ucm.tp1.model.Elements;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.ColliderCallback;

public class Obstacle extends GameElement{
	private static final String NAME = "obstacle";
	protected int resistance;
	public static int counter;
	
	private static final String CON_SYMBOL = "░";

	public Obstacle(Game game, int x, int y) {
		super(game, x, y, NAME);
		this.resistance = 1;
		symbol = Obstacle.CON_SYMBOL;
	}
	
	public Obstacle(Game game, int x, int y, String name) {
		super(game, x, y, name);
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
	
	public static void reset() {
		Obstacle.counter = 0;
	}
}
