package es.ucm.tp1.model;

public class Coin extends GameElement {
	private boolean collected = false; 
	static int counter;
	protected static final String symbol = "¢";
	
	public Coin(int x, int y, Game game) {
		super(x, y, game);
	}
	
	@Override
	public void onEnter() {
		Coin.counter++;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		Coin.counter--;
	}

	@Override
	public boolean isAlive() {
		return ! isCollected();
	}
			

	public void setCollected() {
		collected = true;
	}
	
	public boolean isCollected() {
		return collected;
	}

	public boolean canCollect(Player player) {
		return player.isInPos(this.x, this.y);
	}	
}
