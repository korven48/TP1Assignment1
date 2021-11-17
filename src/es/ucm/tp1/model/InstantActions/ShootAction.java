package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Game;

public final class ShootAction implements InstantAction {

	public ShootAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game) {
		// TODO Auto-generated method stub
		game.getVisibility();
		game.getObjectInPosition(0, 0);
	}

}
