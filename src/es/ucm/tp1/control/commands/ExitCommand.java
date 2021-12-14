package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.model.Game;

final class ExitCommand extends Command {
	private static final String NAME = "exit";
	private static final String DETAILS = "[e]xit";
	private static final String SHORTCUT = "e";
	private static final String HELP = "exit game";
	
	public ExitCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			game.setExit(true);
			result = false;
		} catch (Exception ex) {
			throw new CommandExecuteException(Command.ERROR_EXECUTE, ex);
		}
		return result;
	}
}