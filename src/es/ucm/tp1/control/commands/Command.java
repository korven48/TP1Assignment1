package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Direction;
import es.ucm.tp1.model.Game;

public abstract class Command {
	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	protected static final Command[] AVAILABLE_COMMANDS = {
			new HelpCommand(),
			new InfoCommand(),
			//...
	};
	
	
	String commandString;
	
	public Command() {
		super();
	}
	
	protected abstract void commandParser(String[] params);
	
	public abstract boolean execute(Game game);
	
	public final static Command getCommand(final String[] commandWords) {
		String currentCommandString = "";
		Command currentCommand = null;
		
		return currentCommand;
	}
	
	/*
	switch (currentCommandString) {
	case "i":
		currentCommand = new InfoCommand(); 
		break;
	case "q":
		currentCommand = new MoveUpCommand();
		break;
	case "a":
		currentCommand = new MoveDownCommand();
		break;
	case "n":
		currentCommand = new NoneCommand();
		break;
	case "r":
		currentCommand = new ResetCommand();
		break;
	case "t":
		currentCommand = new TestModeCommand();
		break;
	case "e":
		currentCommand = new ExitCommand();
		break;
	case "h":
		currentCommand = new HelpCommand();
		break;
	default:
		currentCommand = null;
		break; 
	}
	*/
	
}
