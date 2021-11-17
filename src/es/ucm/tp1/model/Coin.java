package es.ucm.tp1.model;

class Coin extends GameElement {
	private static final String NAME = "coin";
	private boolean collected = false; 
	private static final int addedCoins = 1;
	static int counter;
	
	public Coin(Game game, int x, int y) {
		super(x, y, game, NAME);
		symbol = "¢";
	}
	
	public Coin() {
		super(NAME);
	}

	@Override
	public void onEnter() {
		Coin.counter++;
	}

	@Override
	public boolean receiveCollision(ColliderCallback player) {
		player.addCoins(addedCoins);
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
		return ! isCollected();
	}
	
	@Override
	public Coin create(Game game, int x, int y) {
		return new Coin(game, x, y);
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
