package es.ucm.tp1.model;

class Coin extends GameElement {
	private boolean collected = false; 
	static int counter;
	
	public Coin(int x, int y, Game game) {
		super(x, y, game);
		symbol = "¢";
	}

	@Override
	public void onEnter() {
		Coin.counter++;
	}

	@Override
	public boolean receiveCollision(ColliderCallback player) {
		player.addCoin();
		setCollected();
		return false;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onDelete() {
		Coin.counter--;
	}

	@Override
	public boolean isAlive() {
		return !isCollected();
	}
			
	protected void setCollected() {
		collected = true;
	}
	
	protected boolean isCollected() {
		return collected;
	}
	
	protected static void reset() {
		Coin.counter = 0;
	}
}
