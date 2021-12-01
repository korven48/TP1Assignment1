package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;

public class SerializeCommand extends Command {
	private static final String NAME = "serialize";
	private static final String DETAILS = "seriali[z]e";
	private static final String SHORTCUT = "z";
	private static final String HELP = "Serializes the board.";
	
	
	public SerializeCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}
