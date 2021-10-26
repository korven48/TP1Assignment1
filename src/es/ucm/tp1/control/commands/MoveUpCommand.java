package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Direction;
import es.ucm.tp1.model.Game;

final class MoveUpCommand extends Command {
	private static final String NAME = "moveUp";
	private static final String DETAILS = "[q]";
	private static final String SHORTCUT = "q";
	private static final String HELP = "go up";
	
	public MoveUpCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}

	
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		boolean result = false;
		Direction direction = Direction.UP;
		try {
			result = game.movePlayer(result, direction);
		} catch (Exception ex) {
			System.out.println(ex);
		}		
		return result;
	}
}