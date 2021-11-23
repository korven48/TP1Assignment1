package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.GameElementGenerator;
import es.ucm.tp1.model.InstantActions.ShootAction;

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
	protected GrenadeCommand parse(String[] words) {
		if (this.matchCommandName(words[0])) {
			if (words.length == 3) {
				x = Integer.valueOf(words[1]);
				y = Integer.valueOf(words[2]);
				return new GrenadeCommand(x, y);
			} else {
				System.out.format("[ERROR]: Command %s: %s%n%n", GrenadeCommand.NAME,
					 	   Command.INCORRECT_NUMBER_OF_ARGS_MSG);
			}
		}
		return null;
	}


	@Override
	public boolean execute(Game game) {
		// should create a granade in position (playerX + x, y), with x < visibility
		boolean result = false;
		if (game.playerPays(cost())) {
			this.buy(game);
			GameElementGenerator.generateGranade(game, x + game.getCameraPosition(), y);
			result = true;
		}
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
