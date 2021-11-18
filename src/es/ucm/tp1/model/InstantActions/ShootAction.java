package es.ucm.tp1.model.InstantActions;

import es.ucm.tp1.model.Collider;
import es.ucm.tp1.model.Game;

public final class ShootAction implements InstantAction {
	private static final int AMOUNT_TO_PAY = 1;
	
	public ShootAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game) {
		// TODO Auto-generated method stub
		game.getVisibility();
		int lanePlayer = 1;
		Collider element = null;
		if (game.playerPays(ShootAction.AMOUNT_TO_PAY)) {
			for (int y = 0; y <= game.getVisibility(); y++) {
				element = game.getObjectInPosition(lanePlayer, y);
			}
			if (element != null) {
				element.receiveShot();
			}
		}
	}

}
