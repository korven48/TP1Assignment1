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
	public boolean doCollision() {
		return false;
	}
	

	/*
	@Override
	public boolean receiveCollision(Player player) {
		// seters, getters or package level??
		player.resistance--;
		if (player.resistance == 0) {
			player.crashed = true;			
		}
		return true; // I guess its only true, I dont understand why is it a boolean
	}
	*/


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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		Obstacle.counter--;
	}	
}
