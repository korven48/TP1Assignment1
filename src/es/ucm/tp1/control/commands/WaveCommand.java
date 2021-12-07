package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.NotEnoughCoinsException;
import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.InstantActions.WaveAction;

final class WaveCommand extends Command implements Buyable{
	private static final String NAME = "wave";
	private static final String DETAILS = "[w]ave"; 
	private static final String SHORTCUT = "w";
	private static final String HELP = "do wave";
	private static final int COST = 5;

	public WaveCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
		
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean result = false;
		try {
			game.getAmountOfCoinsPlayer(cost());
		} catch (NotEnoughCoinsException ex) {
			throw new CommandExecuteException("", ex);
		}
		this.buy(game);
		game.doInstantAction(new WaveAction());
		result = true;	
		return result;
	}

	@Override
	public int cost() {
		return WaveCommand.COST;
	}
	
	@Override
	public void buy(Game game) {
		game.playerPays(cost());
	}
}
