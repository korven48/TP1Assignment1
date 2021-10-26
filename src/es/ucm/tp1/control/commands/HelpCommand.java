package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;

final class HelpCommand extends Command {
	private static final String NAME = "help";
	private static final String DETAILS = "[h]elp";
	private static final String SHORTCUT = "h";
	private static final String HELP = "show this help";
	
	/* @formatter:off */
	private static final String[] HELP_OUT = new String[] {
		"Available commands:",
		"[h]elp: show this help",
		"[i]nfo: prints gameobjet info",
		"[n]one | []: update",
		"[q]: go up",
		"[a]: go down",
		"[e]xit: exit game",
		"[r]eset: reset game",
		"[t]est: enables test mode",	
	};
	/* @formatter:off */
	
	public HelpCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	//Here we have to return false because anyway the game should not be refreshed
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		//How to parralize the task? --> It is not beautiful
		try {
			System.out.println(HelpCommand.HELP_OUT);
			result = false;
		} catch (Exception ex) {
			System.out.println(ex);
		} 
		return result;			
	}
}