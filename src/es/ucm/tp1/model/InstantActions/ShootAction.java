package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Collider;
import es.ucm.tp1.model.Game;

public final class ShootAction implements InstantAction {

	@Override
	public void execute(Game game) {
		game.getVisibility();
		int lanePlayer = game.getPlayerLane();
		int camera = game.getCameraPosition();
		Collider element = null;
		for (int x = camera; x <= camera + game.getVisibility() - 1; x++) {
			element = game.getObjectInPosition(x, lanePlayer);
			if (element != null) {
				if (element.receiveShot(game.getPlayerCallback())) {
					return;					
				}
			}
		}
	}

}
