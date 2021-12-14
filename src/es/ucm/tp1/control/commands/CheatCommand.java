package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.highlevelexceptions.CommandParseException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.GenerateNewGameElementException;
import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.GameElementGenerator;

public class CheatCommand extends Command {
	private static final String NAME = "cheat";
	private static final String DETAILS = "Cheat <AO-name>";
	private static final String SHORTCUT = "c";
	private static final String HELP = "Removes all elements of last visible column and adds advanced object AO";
	private String element;
	
	public CheatCommand(String element) {
		super(NAME, DETAILS, SHORTCUT, HELP);
		this.element = element;
	}
	
	public CheatCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}

	@Override
	protected Command parse(String[] words) throws CommandParseException {
		String element;
		if (this.matchCommandName(words[0])) {
			if (words.length != 2) {
				throw new CommandParseException(String.format("Command %s: %s%n", CheatCommand.NAME,
							 	   Command.INCORRECT_NUMBER_OF_ARGS_MSG));
			} else {
				element = words[1];
				return new CheatCommand(element);
			}
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean generated = false;
		try {
			GameElementGenerator.generateCheatObject(game, element);
		} catch (GenerateNewGameElementException ex) {
			throw new CommandExecuteException(String.format("Command %s: %s%n", CheatCommand.NAME, Command.ERROR_EXECUTE).trim(), ex);	
		}			
		return generated;
	}

}
