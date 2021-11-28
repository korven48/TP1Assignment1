package es.ucm.tp1.model.Elements;

import es.ucm.tp1.model.InstantActions.ExplodeAction;
import es.ucm.tp1.model.Game;

public class Grenade extends GameElement {

	private static final String NAME = "granade";
	private static final int TOTALCYCLES = 3;
	
	private int cyclesLeft;

	public Grenade(Game game, int x, int y) {
		super(game, x, y, NAME);
		cyclesLeft = TOTALCYCLES;
		symbol = "ð";
	}
	
	public Grenade() {
		super(NAME);
	}

	@Override
	public void update() {
		cyclesLeft--;
	}

	@Override
	public void onDelete() {
		explode();
	}
		
	@Override
	public String toString() {
		// ð[3]
		return symbol + "[" + cyclesLeft + "]";
	}

	private void explode() {
		game.doInstantAction(new ExplodeAction(x, y));
	}

	@Override
	public boolean isAlive() {
		return cyclesLeft != 0;
	}

	@Override
	public GameElement create(Game game, int x, int y) {
		return new Grenade(game, x, y);
	}

}
