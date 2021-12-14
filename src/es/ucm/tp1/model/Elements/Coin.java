package es.ucm.tp1.model.Elements;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.ColliderCallback;

public class Coin extends GameElement {
	private static final String NAME = "coin";
	private static final String CON_SYMBOL = "¢";
	private static final int addedCoins = 1;
	public static final String INFO = "[Coin] gives 1 coin to the player%n";
	
	private boolean collected = false; 
	public static int counter;
	
	public Coin(int x, int y, Game game) {
		super(game, x, y, NAME);
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
		//Element wird eins nach hinten versetzt
		//this.x--;		
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
	public Coin create(Game game, int x, int y) {
		return new Coin(x, y, game);
	}
			
	protected void setCollected() {
		collected = true;
	}
	
	protected boolean isCollected() {
		return collected;
	}
	
	public static void reset() {
		Coin.counter = 0;
	}
}
