package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Collider;
import es.ucm.tp1.model.Game;
import es.ucm.tp1.view.GamePrinter;

public final class ThunderAction implements InstantAction {

	@Override
	public void execute(Game game) {
		// int x = random int between playerX and playerX + visibility
		int x = game.getRandomColumn();
		int y = game.getRandomLane();
		boolean hit = false;
		
		Collider gameElement = game.getObjectInPosition(x, y);
		
		if (gameElement != null) {
			hit = gameElement.receiveThunder();
		}
		
		GamePrinter.printMessage("Thunder hit position: (" + x + ", " + y + ")" + ((hit) ? "-> Obstacle hit":""));
	}
}
