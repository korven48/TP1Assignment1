package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.Elements.IPosElement;

public final class WaveAction implements InstantAction {
	
	public WaveAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game) {
		IPosElement[] elememts = game.getAllPos();
		int cameraPosition = game.getCameraPosition();
		int visibility = game.getVisibility();
		if (elememts != null) {
			for (IPosElement elem : elememts) {
				int cameraPos = cameraPosition;
				int maxRang = cameraPosition + visibility - 1;
				if (cameraPos <= elem.getX() && maxRang >= elem.getX()) {
					if (game.getObjectInPosition(elem.getX() + 1, elem.getY()) == null) {
						elem.receiveWave();
					}				
				}
			}
		}		
		game.update();
		game.removeDeadObjects();
	}
}
