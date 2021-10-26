package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;

final class ResetCommand extends Command {
	private static final String NAME = "reset";
	private static final String DETAILS = "[r]eset";
	private static final String SHORTCUT = "r";
	private static final String HELP = "reset game";
	
	public ResetCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			//Modify this to reset the seed and the random generator
			game.reset();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return result;
	}

}