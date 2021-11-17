package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.InstantActions.ShootAction;

final class ShootCommand extends Command {
	private static final String NAME = "shoot";
	private static final String DETAILS = "[s]hoot";
	private static final String SHORTCUT = "s";
	private static final String HELP = "shoots an at an Gameelement";
	
	public ShootCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		boolean result = false;
		game.doInstantAction(new ShootAction());
		result = true; 
		return result;
	}
}
