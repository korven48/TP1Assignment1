package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;

public class DumpCommand extends Command {
	private static final String NAME = "dump";
	private static final String DETAILS = "[d]ump <filename>";
	private static final String SHORTCUT = "d";
	private static final String HELP = "Shows the content of a saved file.";
	
	private String filename;
	
	public DumpCommand(String filename) {
		super(NAME, DETAILS, SHORTCUT, HELP);
		this.filename = filename;
	}
	
	public DumpCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	protected DumpCommand parse(String[] words) {
		if (this.matchCommandName(words[0])) {
			if (words.length == 2) {
				filename = words[1];
				return new DumpCommand(filename);
			} else {
				Command.printMessage(String.format("[ERROR]: Command %s: %s%n%n", DumpCommand.NAME,
					 	   Command.INCORRECT_NUMBER_OF_ARGS_MSG));
			}
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}