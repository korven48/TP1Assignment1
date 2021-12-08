package es.ucm.tp1.control.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	protected DumpCommand parse(String[] words) {
		if (this.matchCommandName(words[0])) {
			if (words.length == 2) {
				filename = words[1];
				return new DumpCommand(filename);
			} else {
				Command.printMessage(String.format("[ERROR]: Command %s: %s%n%n", DumpCommand.NAME,
					 	   Command.INCORRECT_NUMBER_OF_ARGS_MSG));
			}
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) {
		// Dumps a file into the console
		try (
			FileReader file = new FileReader(filename + ".txt");
			BufferedReader bfile = new BufferedReader(file);){
			
			String line = bfile.readLine();
			while (line != null) {
				System.out.println(line);
				line = bfile.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
