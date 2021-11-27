package es.ucm.tp1.control.commands;

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
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (!game.isTimeOn()) game.startTime();
		Direction direction = Direction.FORWARD;
		try {
			result = game.incrementCyle(direction);
			if (result) Command.printMessage(String.format("%nWARNING: Coudn't move the player in that direction"));
		} catch (Exception ex) {
			System.out.println(ex);
		}		
		return result;
	}
}