package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.GameElement;
import es.ucm.tp1.model.GameElementGenerator;

public class CheatCommand extends Command {
	private static final String NAME = "cheat";
	private static final String DETAILS = "[c]heat";
	private static final String SHORTCUT = "c";
	private static final String HELP = "add an advanced game element";
	private static final String NOT_AN_ADVANCED_GAME_ELEMENT = "Not an advanced game element";
	private String element;
	
	public CheatCommand(String element) {
		super(NAME, DETAILS, SHORTCUT, HELP);
		this.element = element;
	}
	
	public CheatCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}

	@Override
	protected Command parse(String[] words) {
		String element;
		if (this.matchCommandName(words[0])) {
			if (words.length != 2) {
				System.out.format("[ERROR]: Command %s: %s%n%n", CheatCommand.NAME,
							 	   Command.INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				element = words[1];
				return new CheatCommand(element);
			}
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) {
		boolean generated = GameElementGenerator.generateCheatObject(game, element);
		if (! generated) {
			System.out.format("[ERROR]: Command %s: %s%n%n", CheatCommand.NAME,
					CheatCommand.NOT_AN_ADVANCED_GAME_ELEMENT);					
		}
		return generated;
	}

}
