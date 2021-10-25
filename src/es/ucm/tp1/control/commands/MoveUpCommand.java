package es.ucm.tp1.control.commands;

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