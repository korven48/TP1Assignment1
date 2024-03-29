package es.ucm.tp1.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.highlevelexceptions.CommandParseException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.StandardInputOutputException;
import es.ucm.tp1.model.Game;
import es.ucm.tp1.view.GameSerializer;

public class SaveCommand extends Command {
	private static final String NAME = "save";
	private static final String DETAILS = "sa[v]e <filename>";
	private static final String SHORTCUT = "v";
	private static final String HELP = "Save the state of the game to a file.";
	
	private static final String FILE_EXTENTION = ".txt";
	
	private String filename;
	
	public SaveCommand(String filename) {
		super(NAME, DETAILS, SHORTCUT, HELP);
		this.filename = filename;
	}
	
	public SaveCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	protected SaveCommand parse(String[] words) throws CommandParseException{
		if (this.matchCommandName(words[0])) {
			if (words.length == 2) {
				filename = words[1];
				return new SaveCommand(filename);
			} else {
				throw new CommandParseException(String.format("Command %s: %s%n%n", SaveCommand.NAME,
					 	   Command.INCORRECT_NUMBER_OF_ARGS_MSG));
			}
		}
		return null;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// Try to dump GameSerializer in <filename>.txt
		try {
			writeMethod(game);
		} catch (StandardInputOutputException ex) {
			throw new CommandExecuteException(Command.ERROR_EXECUTE, ex);
		}
		return false;
	}

	private void writeMethod(Game game) throws StandardInputOutputException {
		try (FileWriter file     = new FileWriter(filename + SaveCommand.FILE_EXTENTION);
			BufferedWriter bfile = new BufferedWriter(file)) {
			GameSerializer serializer = new GameSerializer(game);
//			bfile.write(game.getSerializedElems());
			bfile.write(serializer.toString());
			System.out.println("Game successfully saved in file " + filename + ".txt");
		} catch (IOException ex) {
			throw new StandardInputOutputException("Writing the file went wronge", ex);
		}
	}
}
