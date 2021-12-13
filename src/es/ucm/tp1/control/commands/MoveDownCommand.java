package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.InvalidPositionException;
import es.ucm.tp1.model.Direction;
import es.ucm.tp1.model.Game;

final class MoveDownCommand extends Command {
	private static final String NAME = "moveDown";
	private static final String DETAILS = "[a]";
	private static final String SHORTCUT = "a";
	private static final String HELP = "go down";
	
	public MoveDownCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// TODO Auto-generated method stub
		if (!game.isTimeOn()) game.startTime();
		Direction direction = Direction.DOWN;
		try {
			game.incrementCyle(direction);
			return true;
		} catch (InvalidPositionException ex) {
			throw new CommandExecuteException(Command.ERROR_EXECUTE, ex);
		}	
	}
}