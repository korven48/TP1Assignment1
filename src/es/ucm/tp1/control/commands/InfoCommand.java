package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.model.Game;

final class InfoCommand extends Command {
	private static final String NAME = "info";
	private static final String DETAILS = "[i]nfo";
	private static final String SHORTCUT = "i";
	private static final String HELP = "prints gameobjet info";
	
	private static final String INFO = String.format(
			  "Available objects:%n"
			+ "[Car] the racing car%n"
			+ "[Coin] gives 1 coin to the player%n"
			+ "[Obstacle] hits car%n"
			+ "[GRENADE] Explodes in 3 cycles, harming everyone around%n"
			+ "[WALL] hard obstacle%n"
			+ "[TURBO] pushes the car: 3 columns%n"
			+ "[SUPERCOIN] gives 1000 coins%n"
			+ "[TRUCK] moves towards the player%n"
			+ "[PEDESTRIAN] person crossing the road up and down%n"
			+ "%n"); 
	
	public InfoCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// TODO Auto-generated method stub
		boolean result = false;
		//How to parallize this task?
		try {
			System.out.println(InfoCommand.INFO);
			result = false;
		} catch (Exception ex) {
			throw new CommandExecuteException(ex.getMessage());
			//maybe here an reset to the last state of the game?
		}
		return result;
	}
}