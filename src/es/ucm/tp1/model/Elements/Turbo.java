package es.ucm.tp1.model.Elements;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.ColliderCallback;
import es.ucm.tp1.model.Direction;

public class Turbo extends GameElement {
	private boolean collected = false; 
	static int counter;
	
	private static final int steps = 3;
	private static final String CON_SYMBOL = ">>>";
	private static final String NAME = "turbo";
	
	public Turbo(Game game, int x, int y) {
		super(game, x, y, NAME);
		// TODO Auto-generated constructor stub
		symbol = Turbo.CON_SYMBOL;
	}
	
	public Turbo() {
		super(NAME);
	}
	
	@Override
	public boolean isAdvanced() {
		return true;
	}

	@Override
	public boolean receiveCollision(ColliderCallback player) {
		for (int i = 0; i < Turbo.steps; i++) {
			this.game.movePlayer(false, Direction.FORWARD);
		}
		return true; // true because the player crashes 
	}
	
	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		Turbo.counter++;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		Turbo.counter--;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
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

	@Override
	public Turbo create(Game game, int x, int y) {
		return new Turbo(game, x, y);
	}
}
