package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.model.Game;

final class TestModeCommand extends Command {
	private static final String NAME = "test";
	private static final String DETAILS = "[t]est";
	private static final String SHORTCUT = "t";
	private static final String HELP = "enables test mode";
	
	public TestModeCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			game.setTest(true);
			result = true;
		} catch (Exception ex) {
			throw new CommandExecuteException(ex.getMessage(), ex);
		}
		return result;
	}
}