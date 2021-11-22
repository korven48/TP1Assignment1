package es.ucm.tp1.model;

public class SuperCoin extends GameElement {
	private static final String NAME = "super";
	private static final int addedCoins = 1000;
	boolean onScreen;
	private boolean collected = false;
	
	private static SuperCoin scoin;
	
	public static SuperCoin getSuperCoin(Game game, int x, int y, boolean reset) {
		if (scoin == null || reset) {
			scoin = new SuperCoin(game, x, y); 
		}
		return scoin; 
	}
	
	private SuperCoin(Game game, int x, int y) {
		super(x, y, game, NAME);
		onScreen = false;
		symbol = "8";
	}
	
	public SuperCoin() {
		super(NAME);
	}
	
	@Override
	public boolean receiveCollision(ColliderCallback player) {
		setCollected();
		player.addCoins(addedCoins);
		return false;
	}

	private void setCollected() {
		collected = true;
	}

	@Override
	public boolean isAdvanced() {
		return true;
	}
	
	@Override
	public SuperCoin create(int x, int y, Game game) {
		return getSuperCoin(game, x, y, false);
	}
	
	@Override
	public void onEnter() {
		onScreen = true;
	}

	@Override
	public void update() {
		if (onScreen) {
			System.out.println("Supercoin is present");
		}
	}

	@Override
	public void onDelete() {
		onScreen = false;
		scoin = null;
	}

	@Override
	public boolean isAlive() {
		return ! collected;
	}

}
