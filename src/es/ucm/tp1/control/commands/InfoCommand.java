package es.ucm.tp1.control.commands;

import es.ucm.tp1.Exceptions.highlevelexceptions.CommandExecuteException;
import es.ucm.tp1.model.Game;
import es.ucm.tp1.model.Player;
import es.ucm.tp1.model.Elements.Coin;
import es.ucm.tp1.model.Elements.Grenade;
import es.ucm.tp1.model.Elements.Obstacle;
import es.ucm.tp1.model.Elements.Pedestrian;
import es.ucm.tp1.model.Elements.SuperCoin;
import es.ucm.tp1.model.Elements.Truck;
import es.ucm.tp1.model.Elements.Wall;
import es.ucm.tp1.model.InstantActions.ThunderAction;

final class InfoCommand extends Command {
	private static final String NAME = "info";
	private static final String DETAILS = "[i]nfo";
	private static final String SHORTCUT = "i";
	private static final String HELP = "prints gameobjet info";
	
	private static final String INFO = String.format(
			  "Available objects:%n"
			+ Player.INFO
			+ Coin.INFO
			+ Obstacle.INFO
			+ Grenade.INFO
			+ Wall.INFO
			+ ThunderAction.INFO
			+ SuperCoin.INFO
			+ Truck.INFO
			+ Pedestrian.INFO
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
			throw new CommandExecuteException(Command.ERROR_EXECUTE, ex);
		}
		return result;
	}
}