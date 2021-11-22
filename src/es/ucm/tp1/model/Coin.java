package es.ucm.tp1.model;

class Coin extends GameElement {
	private static final String NAME = "coin";
	private boolean collected = false; 
	private static final int addedCoins = 1;
	static int counter;
	
	private static final String CON_SYMBOL = "¢";
	
	public Coin(int x, int y, Game game) {
		super(x, y, game, NAME);
		symbol = Coin.CON_SYMBOL;
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
		return !isCollected();
	}
	
	@Override
	public Coin create(int x, int y, Game game) {
		return new Coin(x, y, game);
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
