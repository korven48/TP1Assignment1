package es.ucm.tp1.model;

//Convert to Singelton in the future --> https://refactoring.guru/design-patterns/singleton 
public class SuperCoin extends GameElement {
	private static final String NAME = "super";
	private static final int addedCoins = 1000;
	boolean onScreen;
	private boolean collected = false;
	
	private static final String SUPER_CON_SYMBOL = "$";
	private static SuperCoin sCoin;
	private static boolean isExisting = false; 
	
	public static SuperCoin getSuperCoin(Game game, int x, int y, boolean reset) {
		if (sCoin == null || reset) {
			sCoin = new SuperCoin(game, x, y); 
		}
		return sCoin; 
	}
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y, NAME);
		onScreen = false;
		symbol = SuperCoin.SUPER_CON_SYMBOL;
		SuperCoin.isExisting = true; 
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
	public SuperCoin create(Game game, int x, int y) {
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
		sCoin = null;
	}

	@Override
	public boolean isAlive() {
		return ! collected;
	}

	public static final boolean hasSuperCoin() {
		if (SuperCoin.isExisting) {
			return true;
		}
		return false;
	}
}
