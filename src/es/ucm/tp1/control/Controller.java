package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.view.*;
import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.highlevelexceptions.GameException;
import es.ucm.tp1.control.commands.Command;

public class Controller {

	private static final String PROMPT = "Command > ";
	private static final String DEBUG_MSG = "[DEBUG] Executing: ";
	private static final String UNKNOWN_COMMAND_MSG = "Unknown Command: ";

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;
	private GameSerializer serializer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.printer = new GamePrinter(game);
		this.serializer = new GameSerializer(game);
	}

	public void printGame() {
		System.out.println(printer);
	}
	
	public void printSerializer() {
		System.out.println(serializer);		
	}
	
	public static void printUnknown() {
		System.out.println(UNKNOWN_COMMAND_MSG);
	}

	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}

	public void run() {
		boolean refreshDisplay = true;
		Command command = null; 
		
		while (!game.isFinished()) {
			if (refreshDisplay) {
				printGame();
				
				// Debbuging only
//				printSerializer();
			}
			refreshDisplay = false;
			
			System.out.println(Controller.PROMPT);
			String s = scanner.nextLine();
			String [] parameters = s.toLowerCase().trim().split(" ");
			System.out.println(DEBUG_MSG + s);
			
			try {
				command = Command.getCommand(parameters);
			    refreshDisplay = command.execute(game);
				game.removeDeadObjects();
			} catch (GameException ex) {
				System.out.format("[ERROR]: %s%n%n", ex.getMessage());
			}
		}
		game.close();
		if (refreshDisplay) printGame();
		printEndMessage();
  }
}