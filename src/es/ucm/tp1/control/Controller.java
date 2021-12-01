package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.CusExceptions.GameException;
import es.ucm.tp1.view.GamePrinter;

import es.ucm.tp1.control.commands.Command;

public class Controller {

	private static final String PROMPT = "Command > ";
	private static final String UNKNOWN_COMMAND_MSG = "[ERROR]: Unknown command";
	private static final String DEBUG_MSG = "[DEBUG] Executing: ";

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.printer = new GamePrinter(game);
	}

	public void printGame() {
		System.out.println(printer);
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
			}
			refreshDisplay = false;
			System.out.println(Controller.PROMPT);
			String s = scanner.nextLine();
			String [] parameters = s.toLowerCase().trim().split (" ");
			System.out.println(DEBUG_MSG + s);
			try {
				command = Command.getCommand(parameters);
				if (command != null) {
					refreshDisplay = command.execute(game);
//					game.update(); Update should only be called when the cycle increments
					game.removeDeadObjects();
				} else {
					System.out.println(UNKNOWN_COMMAND_MSG);
				}
			} catch (GameException ex) {
				System.out.println(ex);
			}
//			}
//			command = Command.getCommand(parameters);
//			if (command != null) {
//				refreshDisplay = command.execute(game);
////				game.update(); Update should only be called when the cycle increments
//				game.removeDeadObjects();
//			} else {
//				System.out.println(UNKNOWN_COMMAND_MSG);
//			}
		}
		if (refreshDisplay) printGame();
		printEndMessage();
  }
}