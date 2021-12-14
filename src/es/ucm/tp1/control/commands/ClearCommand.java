package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.model.Game;

public class ClearCommand extends Command {
	private static final String NAME = "clear";
	private static final String DETAILS = "Clear [0]";
	private static final String SHORTCUT = "0";
	private static final String HELP = "Clears the road";


	public ClearCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.removeAll();
			return true;
		} catch (Exception ex) {
			throw new CommandExecuteException(Command.ERROR_EXECUTE, ex);
		}
	}
}
