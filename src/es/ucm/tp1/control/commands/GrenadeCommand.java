package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.Exceptions.highlevelexceptions.CommandParseException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.NotEnoughCoinsException;
import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.GameElementGenerator;

public class GrenadeCommand extends Command implements Buyable {
	private static final String NAME = "granade";
	private static final String DETAILS = "[g]renade <x> <y>"; 
	private static final String SHORTCUT = "g";
	private static final String HELP = "add a grenade in position x, y";
	
	private static final int COST = 3;
	
	private int x, y;
	
	public GrenadeCommand(int x, int y) {
		super(NAME, DETAILS, SHORTCUT, HELP);
		this.x = x;
		this.y = y;
	}
	
	public GrenadeCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
	@Override
	protected GrenadeCommand parse(String[] words) throws CommandParseException {
		if (this.matchCommandName(words[0])) {
			if (words.length == 3) {
				try {
					x = Integer.valueOf(words[1]);
					y = Integer.valueOf(words[2]);
					return new GrenadeCommand(x, y);
				} catch (NumberFormatException ex) {
					throw new CommandParseException(String.format("[ERROR]: Command %s: %s%n%n", GrenadeCommand.NAME,
						 	   Command.INCORRECT_NUMBER_OF_ARGS_MSG), ex);
				}
			} else {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s%n%n", GrenadeCommand.NAME,
					 	   Command.INCORRECT_NUMBER_OF_ARGS_MSG));
			}
		}
		return null;
	}


	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// should create a granade in position (playerX + x, y), with x < visibility
		boolean result = false;
		try {
			game.getAmountOfCoinsPlayer(cost());
		} catch (NotEnoughCoinsException ex) {
			throw new CommandExecuteException("", ex);
		}
		this.buy(game);
		GameElementGenerator.generateGranade(game, x + game.getCameraPosition(), y);
		result = true;
		return result;
	}
	
	@Override
	public int cost() {
		return COST;
	}
	
	@Override
	public void buy(Game game) {
		game.playerPays(cost());
	}
}
