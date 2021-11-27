package es.ucm.tp1.model.Elements;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.view.GamePrinter;
import es.ucm.tp1.model.ColliderCallback;

//Convert to Singelton in the future --> https://refactoring.guru/design-patterns/singleton 
public class SuperCoin extends GameElement {
	private static final String NAME = "super";
	private static final int addedCoins = 1000;
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
		symbol = SuperCoin.SUPER_CON_SYMBOL;
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
		isExisting = true;
	}

	@Override
	public void update() {
		if (isExisting) {
			GamePrinter.printMessage("Super coin is present!");
		}
	}

	@Override
	public void onDelete() {
		isExisting = false;
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
