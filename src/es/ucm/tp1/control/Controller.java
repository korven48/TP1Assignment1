package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.view.GamePrinter;

public class Controller {

	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";

	/* @formatter:off */
	private static final String[] HELP = new String[] {
		"Available commands:",
		"[h]elp: show this help",
		"[i]nfo: prints gameobjet info",
		"[n]one | []: update",
		"[q]: go up",
		"[a]: go down",
		"[e]xit: exit game",
		"[r]eset: reset game",
		"[t]est: enables test mode",	
	};
	/* @formatter:off */

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
	
	public static void printHelp() {
		System.out.println();
		for (String line : HELP) {
			System.out.println(line);
		}
		System.out.println();
	}
	
	public static void printUnknown() {
		System.out.println("\n" + UNKNOWN_COMMAND_MSG + "\n");
	}

	
	public void printEndMessage() {
		System.out.println("\n" + printer.endMessage() + "\n");
	}
	


	public void run() {
		// TODO fill your code
		boolean shouldDisplay;
		String command;
		printGame();
		while (!game.isFinished()) {
			System.out.print(PROMPT);
			command = scanner.nextLine();
			if (!game.isTimeOn()) game.startTime();
			shouldDisplay = game.update(command);
			game.removeDeadObjects();
			//Please check if really needed!
			//if (shouldDisplay) {
				printGame();
				game.incrementCyle();
			//}		
		}
		printEndMessage();
  }
}

	
