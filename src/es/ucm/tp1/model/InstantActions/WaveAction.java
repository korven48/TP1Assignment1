package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.Collider;

public final class WaveAction implements InstantAction {

	public WaveAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game) {
		for (int x = 0; x <= game.getLevel().getLength(); x++) {
			for (int y = 0; y <= game.getLevel().getLength(); y++) {
				Collider gameElement = game.getObjectInPosition(x, y);
				if (gameElement != null) {
					gameElement.moveRight();
				}
			}
		}
		game.update();
		game.removeDeadObjects();
	}

}
