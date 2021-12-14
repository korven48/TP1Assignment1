package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.NotEnoughCoinsException;
import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.InstantActions.ShootAction;

final class ShootCommand extends Command implements Buyable {
	private static final String NAME = "shoot";
	private static final String DETAILS = "[s]hoot";
	private static final String SHORTCUT = "s";
	private static final String HELP = "shoots an at an Gameelement";

	private static final int COST = 1;
	
	public ShootCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean result = false;
		try {
			game.getAmountOfCoinsPlayer(cost());
		} catch (NotEnoughCoinsException ex) {
			throw new CommandExecuteException(Command.ERROR_EXECUTE, ex);
		}
		this.buy(game);
		game.doInstantAction(new ShootAction());
		result = true;
		return result;
	}

	@Override
	public int cost() {
		return COST;
	}
}
