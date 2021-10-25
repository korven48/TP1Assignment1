package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;

final class ExitCommand extends Command {
	private static final String NAME = "exit";
	private static final String DETAILS = "[e]xit";
	private static final String SHORTCUT = "e";
	private static final String HELP = "exit game";
	
	public ExitCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		return result;
	}
}





/*
@Override
protected Command commandParser(String[] params) {
	// TODO Auto-generated method stub
	char c = ' ';
	for (String param : params) {
		c = param.toLowerCase().charAt(1);
		if (c == SHORTCUT.charAt(1) ) {
			return (Command) this;
		}
	}
	return (Command) null;
}
*/