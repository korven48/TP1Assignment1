package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.InvalidPositionException;
import es.ucm.tp1.model.Direction;
import es.ucm.tp1.model.Game;

final class MoveUpCommand extends Command {
	private static final String NAME = "moveUp";
	private static final String DETAILS = "[q]";
	private static final String SHORTCUT = "q";
	private static final String HELP = "go up";
	
	public MoveUpCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}

	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		if (!game.isTimeOn()) game.startTime();
		Direction direction = Direction.UP;
		try {
			game.incrementCyle(direction);
			return true;
		} catch (InvalidPositionException ex) {
			throw new CommandExecuteException(ex.getMessage(), ex);
		}	
	}
}