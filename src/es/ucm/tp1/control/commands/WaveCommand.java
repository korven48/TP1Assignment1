package es.ucm.tp1.control.commands;

import es.ucm.tp1.model.Game;

public class WaveCommand extends Command {
	private static final String NAME = "wave";
	private static final String DETAILS = "[w]ave"; 
	private static final String SHORTCUT = "w";
	private static final String HELP = "pushes all the game elements within the visibility (except the car) one square to the right";


	public WaveCommand() {
		super(NAME, DETAILS, SHORTCUT, HELP);
	}
	
		
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}
