package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Collider;
import es.ucm.tp1.model.Game;

public class ExplodeAction implements InstantAction {
	private int x, y;
	
	public ExplodeAction(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}



	@Override
	public void execute(Game game) {
		game.getVisibility();
		Collider element = null;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				// Loops through vicinity objects.
				if (i != 0 || j != 0) { // if the position is not that of the granade
					element = game.getObjectInPosition(x + i, y + j);									
					if (element != null) {
						element.receiveShot();
					}
				}
			}
		}
		game.update();
	}

}
