package es.ucm.tp1.model;

public class Coin extends GameObject {
	private boolean collected = false; 
	static int counter;
	
	public Coin(int x, int y) {
		super(x, y);
		Coin.counter++;
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
