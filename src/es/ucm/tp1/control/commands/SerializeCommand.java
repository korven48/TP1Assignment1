package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.model.Game;
import es.ucm.tp1.view.GameSerializer;

public class SerializeCommand extends Command {
	private static final String NAME = "serialize";
	private static final String DETAILS = "seriali[z]e";
	private static final String SHORTCUT = "z";
	private static final String HELP = "Serializes the board.";
	
	
	public SerializeCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// Should print the GameSerializer
		try {
			GameSerializer serializer = new GameSerializer(game);
			System.out.println(serializer);
			return false;
		} catch (Exception ex) {
			throw new CommandExecuteException(Command.ERROR_EXECUTE, ex);
		}
	}
}
