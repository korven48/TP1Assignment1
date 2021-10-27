package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Level;
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
	
	
	//Has to be modifyed
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		boolean result = false;
		long seed = 1;
		Level level = Level.HARD;
		
		try {
			//Modify this to reset the seed and the random generator
			game.reset(seed, level);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return result;
	}

}