package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.InvalidPositionException;
import es.ucm.tp1.model.Direction;
import es.ucm.tp1.model.Game;

final class NoneCommand extends Command {
	private static final String NAME = "none";
	private static final String DETAILS = "[n]one | []";
	private static final String SHORTCUT = "n";
	private static final String HELP = "update";
	
	public NoneCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	//Had to be changed, because NoneCommand can be executed also without an input
	@Override
	protected boolean matchCommandName(String name) {
		return NoneCommand.SHORTCUT.equalsIgnoreCase(name) || NoneCommand.NAME.equalsIgnoreCase(name) || "".equalsIgnoreCase(name);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// TODO Auto-generated method stub
		if (!game.isTimeOn()) game.startTime();
		Direction direction = Direction.FORWARD;
		try {
			game.incrementCyle(direction);
			return true;
		} catch (InvalidPositionException ex) {
			throw new CommandExecuteException("", ex);
		}		
	}
}