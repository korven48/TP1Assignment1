package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Collider;
import es.ucm.tp1.model.Game;

public final class ThunderAction implements InstantAction {

	@Override
	public void execute(Game game) {
		// int x = random int between playerX and playerX + visibility
		int x = 4;
		int y = game.getRandomLane();
		boolean hit = false;
		
		Collider gameElement = game.getObjectInPosition(x, y);
		
		if (gameElement != null) {
			hit = gameElement.receiveThunder();
		}
		
		System.out.println("Thunder hit position: (" + x + ", " + y + ")" + ((hit) ? "-> Obstacle hit":""));
	}

}
