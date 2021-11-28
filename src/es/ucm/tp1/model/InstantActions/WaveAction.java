package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.Collider;

public final class WaveAction implements InstantAction {
	
	public WaveAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game) {
		//iterate from the end of the ElementsContainer to the front
		//Because the other way around lead to erros like doppel or more times moving
		for (int x = game.getVisibility() + game.getCameraPosition(); x >= game.getCameraPosition(); x--) {
			for (int y = 0; y <= game.getLevel().getWidth(); y++) {
				Collider gameElement = game.getObjectInPosition(x, y);
				if (gameElement != null) {
					if ((game.getObjectInPosition(x + 2, y) == null)) {
						gameElement.moveRight();
					}
				}
			}
		}
		game.update();
		game.removeDeadObjects();
	}

}
