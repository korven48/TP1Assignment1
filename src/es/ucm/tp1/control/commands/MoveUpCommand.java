package es.ucm.tp1.control.commands;

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
	public boolean execute(Game game) {
		boolean result = false;
		if (!game.isTimeOn()) game.startTime();
		Direction direction = Direction.UP;
		try {
			result = game.incrementCyle(direction);
			if (result) Command.printMessage(String.format("%nWARNING: Coudn't move the player in that direction"));
		} catch (Exception ex) {
			System.out.println(ex);
		}		
		return result;
	}
}