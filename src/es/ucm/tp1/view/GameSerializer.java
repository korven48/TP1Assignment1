package es.ucm.tp1.view;

import es.ucm.tp1.model.Game;

public class GameSerializer {

	protected Game game;
	
	public GameSerializer(Game game) {
		this.game = game;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(game.getGeneralState());
		
		return str.toString();
	}
}
