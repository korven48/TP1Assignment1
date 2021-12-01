package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;

public class SaveCommand extends Command {
	private static final String NAME = "save";
	private static final String DETAILS = "sa[v]e <filename>";
	private static final String SHORTCUT = "s";
	private static final String HELP = "Save the state of the game to a file.";
	
	private String filename;
	
	public SaveCommand(String filename) {
		super(NAME, DETAILS, SHORTCUT, HELP);
		this.filename = filename;
	}
	
	public SaveCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	protected SaveCommand parse(String[] words) {
		if (this.matchCommandName(words[0])) {
			if (words.length == 2) {
				filename = words[1];
				return new SaveCommand(filename);
			} else {
				Command.printMessage(String.format("[ERROR]: Command %s: %s%n%n", SaveCommand.NAME,
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
