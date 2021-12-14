package es.ucm.tp1.model.Elements;

import es.ucm.tp1.model.InstantActions.ExplodeAction;
import es.ucm.tp1.Exceptions.lowlevelexceptions.InvalidPositionException;
import es.ucm.tp1.model.Game;

public class Grenade extends GameElement {

	private static final String NAME = "granade";
	private static final int TOTALCYCLES = 3;
	
	private int cyclesLeft;

	public Grenade(Game game, int x, int y) throws InvalidPositionException {
		super(game, x, y, NAME);
		// Checks if the grenade is in the visibility range and inside of the road
		if (isPositionValid(game, x, y)) {
			throw new InvalidPositionException("Invalid Position");
		}
		cyclesLeft = TOTALCYCLES;
		symbol = "ð";
	}

	private boolean isPositionValid(Game game, int x, int y) {
		return !(x >= game.getCameraPosition() && x < game.getCameraPosition() + game.getVisibility() &&
		    y >= 0 && y < game.getRoadWidth());
	}
	
	public Grenade() {
		super(NAME);
	}

	@Override
	public void update() {
		cyclesLeft--;
	}
	
	@Override
	public String getSerialized() {
		return super.getSerialized() + " " + this.cyclesLeft;
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
	public GameElement create(Game game, int x, int y) throws InvalidPositionException {
		return new Grenade(game, x, y);
	}

}
