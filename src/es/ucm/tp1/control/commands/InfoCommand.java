package es.ucm.tp1.control.commands;

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
			+ "[Obstacle] hits car"
			+ "%n"); 
	
	public InfoCommand() {
		// TODO Auto-generated constructor stub
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		boolean result = false;
		//How to parallize this task?
		try {
			System.out.println(InfoCommand.INFO);
			result = false;
		} catch (Exception ex) {
			System.out.println(ex);
			//maybe here an reset to the last state of the game?
		}
		return result;
	}
}