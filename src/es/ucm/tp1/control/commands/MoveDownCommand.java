package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Direction;
import es.ucm.tp1.model.Game;

final class MoveDownCommand extends Command {
	private static final String NAME = "moveDown";
	private static final String DETAILS = "[a]";
	private static final String SHORTCUT = "a";
	private static final String HELP = "go down";
	
	public MoveDownCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		boolean result = false;
		Direction direction = Direction.DOWN;
		try {
			result = game.movePlayer(result, direction);
		} catch (Exception ex) {
			System.out.println(ex);
		}		
		return result;
	}
}