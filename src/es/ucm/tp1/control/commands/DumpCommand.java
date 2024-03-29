package es.ucm.tp1.control.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.highlevelexceptions.CommandParseException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.InputOutputRecordException;
import es.ucm.tp1.model.Game;

public class DumpCommand extends Command {
	private static final String NAME = "dump";
	private static final String DETAILS = "[d]ump <filename>";
	private static final String SHORTCUT = "d";
	private static final String HELP = "Shows the content of a saved file.";
	
	private String filename;
	
	public DumpCommand(String filename) {
		super(NAME, DETAILS, SHORTCUT, HELP);
		this.filename = filename;
	}
	
	public DumpCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	protected DumpCommand parse(String[] words) throws CommandParseException {
		if (this.matchCommandName(words[0])) {
			if (words.length == 2) {
				filename = words[1];
				return new DumpCommand(filename);
			} else {
				throw new CommandParseException(String.format("Command %s: %s%n%n", DumpCommand.NAME,
					 	   Command.INCORRECT_NUMBER_OF_ARGS_MSG));
			}
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// Dumps a file into the console
		try {
			readFile();
		} catch (InputOutputRecordException ex) {
			throw new CommandExecuteException(Command.ERROR_EXECUTE, ex);
		}
		return false;
	}

	private void readFile() throws InputOutputRecordException {
		try (FileReader file = new FileReader(filename + ".txt");
			BufferedReader bfile = new BufferedReader(file);) {
			
			String line = bfile.readLine();
			while (line != null) {
				System.out.println(line);
				line = bfile.readLine();
			}
			
		} catch (IOException ex) {
			throw new InputOutputRecordException("Read File went wronge", ex);
		}
	}

}
