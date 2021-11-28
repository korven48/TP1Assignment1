package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.Collider;

public final class WaveAction implements InstantAction {
	
	public WaveAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game) {
		game.moveElementsToRight();
		game.update();
		game.removeDeadObjects();
	}

}
