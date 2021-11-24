package es.ucm.tp1.model;

import es.ucm.tp1.model.InstantActions.ExplodeAction;

public class Grenade extends GameElement {

	private static final String NAME = "granade";
	private static final int TOTALCYCLES = 3;
	
	private int initialCycle, cyclesLeft;

	public Grenade(int x, int y, Game game) {
		// ExplodeAction object should be so
		// created by the constructor of the Grenade class
		super(game, x, y, NAME);
//		initialCycle = game.getCycle();
//		cyclesLeft = TOTALCYCLES;
		cyclesLeft = 3;
		symbol = "ð";
	}
	
	public Grenade() {
		super(NAME);
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
//		cyclesLeft = TOTALCYCLES - (game.getCycle() - initialCycle);
		cyclesLeft--;
	}

	@Override
	public void onDelete() {
		explode();
	}
	
	@Override
	public String getSymbol() {
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
		return new Grenade(x, y, game);
	}

}
