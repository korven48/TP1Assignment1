package es.ucm.tp1.model;

class Coin extends GameElement {
	private boolean collected = false; 
	static int counter;
	
	protected Coin(int x, int y) {
		super(x, y);
		Coin.counter++;
	}
	
	protected Coin(Game game, int x, int y) {
		super(game, x, y);
		Coin.counter++;
	}
	
	@Override
	protected boolean isAlive() {
		return ! isCollected();
	}
			

	protected void setCollected() {
		collected = true;
	}
	
	protected boolean isCollected() {
		return collected;
	}

	protected boolean canCollect(Player player) {
		return player.isInPos(this.x, this.y);
	}	
	
	//Has to be added
	protected static void reset() {
		Coin.counter = 0;
	}
}
