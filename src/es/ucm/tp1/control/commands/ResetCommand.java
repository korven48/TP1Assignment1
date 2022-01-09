package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.highlevelexceptions.CommandParseException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.InputOutputRecordException;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.model.Game;

final class ResetCommand extends Command {
	private static final String NAME = "reset";
	private static final String DETAILS = "[r]eset [<level> <seed>]";
	private static final String SHORTCUT = "r";
	private static final String HELP = "reset game";
	
	private static final long DEFAULT_SEED = 1;
	private static final Level DEFAULT_LEVEL = Level.EASY; 
	
	private long seed; 
	private Level level;
	
	private ResetCommand(long seed, Level level) {
		this();
		this.seed = seed;
		this.level = level;
	}
	
	public ResetCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException{
		//Able to store 
		String seed;
		String level;
		try {
			if (matchCommandName(words[0])) {
				if (words.length != 1 && words.length != 3) {
					throw new CommandParseException(String.format("Command %s: %s%n%n", ResetCommand.NAME,
						 	   Command.INCORRECT_NUMBER_OF_ARGS_MSG));
				} else if (words.length != 1) {
					level = words[1];
					seed = words[2];
					return new ResetCommand(Integer.parseInt(seed), Level.parse(level));
				} else if (words.length != 3) {
					return new ResetCommand(ResetCommand.DEFAULT_SEED, ResetCommand.DEFAULT_LEVEL);
				}
			}
		} catch (NumberFormatException ex) {
			throw new CommandParseException(Command.ERROR_EXECUTE, ex);
		} 
		return null;	
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// TODO Auto-generated method stub
		boolean result = false;		
		try {
			game.reset(this.seed, this.level);
			result = true;
		} catch (InputOutputRecordException ex) {
			throw new CommandExecuteException(Command.ERROR_EXECUTE, ex);
		}
		return result;
	}

}