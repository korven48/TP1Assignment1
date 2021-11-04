package es.ucm.tp1.model;

public class Obstacle extends GameElement{
	protected static final String symbol = "░";
	private int resistance;
	static int counter;

	public Obstacle(int x, int y, Game game) {
		super(x, y, game);
		this.resistance = 1;
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
}
