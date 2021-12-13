package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.model.Game;

public class ShowRecordCommand extends Command {
	private static final String NAME = "record";
	private static final String DETAILS = "rec[o]rd";
	private static final String SHORTCUT = "o";
	private static final String HELP = "Show level record.";
		
	public ShowRecordCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			System.out.println(game.getRecords());
			return false;
		} catch (Exception ex) {
			throw new CommandExecuteException(Command.ERROR_EXECUTE, ex);
		}
	}

}
