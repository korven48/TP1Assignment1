package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.model.Game;

final class HelpCommand extends Command {
	private static final String NAME = "help";
	private static final String DETAILS = "[h]elp";
	private static final String SHORTCUT = "h";
	private static final String HELP = "show this help";
		
	//"Clear [0]: Clears the road",
	//"Cheat <AO-name>: Removes all elements of last visible column and adds advanced object AO"
			
	public HelpCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP.toString());
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			System.out.println(Command.getCommandDetailsAndHelpToString());
			result = false;
		} catch (Exception ex) {
			throw new CommandExecuteException();
		} 
		return result;			
	}
}