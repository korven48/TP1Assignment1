package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;

public abstract class Command {
	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of Arguments";
	protected static final Command[] AVAILABLE_COMMANDS = {
			new HelpCommand(),
			new InfoCommand(),
			new MoveDownCommand(),
			new MoveUpCommand(),
			new ExitCommand(),
			new NoneCommand(),
			new ResetCommand(),
			new TestModeCommand()
	};
	
	private final String name;
	private final String shortcut;
	private final String details;
	private final String help;
	
	String commandString;
	
	public Command(String name, String details, String shortcut, String help) {
		super();
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}
	
	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	protected Command parse(String[] words) {
		if (matchCommandName(words[0]))
			if (words.length != 1) {
				System.out.format("[ERROR]: Command %s: %s%n%n", name,
								INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				return this;
			}
		return null;
	}
	
	public abstract boolean execute(Game game);
	
	public final static Command getCommand(final String[] commandWords) {
		Command currentCommand = null;
		for (Command com : Command.AVAILABLE_COMMANDS) {
			//Is their an equal to ??= operator in C#
			currentCommand = com.parse(commandWords);
			//Maybe remove in future versions
			if (currentCommand != null) {
				break;
			}
		}
		return currentCommand;
	}
}
