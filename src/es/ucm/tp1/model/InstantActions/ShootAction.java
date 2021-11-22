package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Collider;
import es.ucm.tp1.model.Game;

public final class ShootAction implements InstantAction {
	
	public ShootAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game) {
		game.getVisibility();
		int lanePlayer = 1;
		Collider element = null;
		for (int x = 0; x <= game.getVisibility(); x++) {
			element = game.getObjectInPosition(x, lanePlayer);
		}
		if (element != null) {
			element.receiveShot();
		}
	}

}
