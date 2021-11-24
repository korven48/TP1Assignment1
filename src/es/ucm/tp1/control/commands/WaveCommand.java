package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.InstantActions.WaveAction;

final class WaveCommand extends Command implements Buyable{
	private static final String NAME = "wave";
	private static final String DETAILS = "[w]ave"; 
	private static final String SHORTCUT = "w";
	private static final String HELP = "pushes all the game elements within the visibility (except the car) one square to the right";

	private static final int COST = 5;

	public WaveCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
		
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (game.getAmountOfCoinsPlayer() >= this.cost()) {
			this.buy(game);
			game.doInstantAction(new WaveAction());
			result = true;
		}		
		return result;
	}

	@Override
	public int cost() {
		return COST;
	}
	
	@Override
	public void buy(Game game) {
		game.playerPays(cost());
	}
}
