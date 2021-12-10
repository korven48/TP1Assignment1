package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Game;

public final class WaveAction implements InstantAction {
	
	public WaveAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game) {
		game.invokeReceiveWaveOnElements();
		game.update();
		game.removeDeadObjects();
	}

}
