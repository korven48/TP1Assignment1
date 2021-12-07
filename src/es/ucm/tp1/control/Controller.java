package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.view.GamePrinter;
import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.highlevelexceptions.GameException;
import es.ucm.tp1.control.commands.Command;

public class Controller {

	private static final String PROMPT = "Command > ";
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
			    command.execute(game);
				refreshDisplay = true;
				game.removeDeadObjects();
			} catch (GameException ex) {
				System.out.format("[ERROR]: %s%n%n", ex.getMessage());
				refreshDisplay = false;
			}
		}
		if (refreshDisplay) printGame();
		printEndMessage();
  }
}