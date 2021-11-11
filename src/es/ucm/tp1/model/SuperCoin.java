package es.ucm.tp1.model;

public class SuperCoin extends GameElement {
	private static final String NAME = "super";
	boolean onScreen;
	
	private static SuperCoin scoin;
	
	public static SuperCoin getPlayer(int x, int y, Game game, boolean reset) {
		if (scoin == null || reset) {
			scoin = new SuperCoin(x, y, game); 
		}
		return scoin; 
	}
	
	private SuperCoin(int x, int y, Game game) {
		super(x, y, game, NAME);
		onScreen = false;
	}
	
	public SuperCoin() {
		super(NAME);
	}

	@Override
	public boolean isAdvanced() {
		return true;
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
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

}
