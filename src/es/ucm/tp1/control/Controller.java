package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.view.*;
import es.ucm.tp1.Exceptions.highlevelexceptions.GameException;
import es.ucm.tp1.control.commands.Command;

public class Controller {

	private static final String PROMPT = "Command > ";
	private static final String DEBUG_MSG = "[DEBUG] Executing: ";

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;
	// private GameSerializer serializer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.printer = new GamePrinter(game);
		// this.serializer = new GameSerializer(game);
	}

	public void printGame() {
		System.out.println(printer);
	}
	
	// public void printSerializer() {
	// 	System.out.println(serializer);		
	// }
	
	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}

	public void run() {
		boolean refreshDisplay = true;
		Command command = null; 
		try {
			game.loadRecord();
		} catch (GameException ex) {
			System.out.format("[ERROR]: %s%n%n", ex.getMessage());
		}
		
		while (!game.isFinished()) {
			if (refreshDisplay) {
				printGame();
			}
			refreshDisplay = false;
			
			System.out.println(Controller.PROMPT);
			String s = scanner.nextLine();
			String [] parameters = s.toLowerCase().trim().split("\\s+");
			System.out.println(DEBUG_MSG + s);
			
			try {
				command = Command.getCommand(parameters);
			    refreshDisplay = command.execute(game);
				game.removeDeadObjects();
			} catch (GameException ex) {
				System.out.format("[ERROR]: %s%n%n", ex.getMessage());
				//For Debug
				//System.out.format("[ERROR]: %s%n%n", ex.getCause().getMessage());
				//ex.printStackTrace();
			}
		}
		
		try {
			game.close();
		} catch (GameException ex) {
			System.out.format("[ERROR]: %s%n%n", ex.getMessage());
			//For Debug
			//System.out.format("[ERROR]: %s%n%n", ex.getCause().getMessage());
			//ex.printStackTrace();
		}
		
		if (refreshDisplay) printGame();
		printEndMessage();
  }
}