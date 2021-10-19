package es.ucm.tp1.view;

import es.ucm.tp1.model.Game;
import es.ucm.tp1.utils.StringUtils;


public class GamePrinter {

	private static final String SPACE = " ";
	private static final String VERTICAL_DELIMITER = "|";	// What is this?
	private static final String ROAD_BORDER_PATTERN = "═";
	private static final String LANE_DELIMITER_PATTERN = "─";

	private static final int CELL_SIZE = 7;
	private static final int MARGIN_SIZE = 2;

	private String indentedRoadBorder;
	private String indentedLlanesSeparator;
	private String margin;

	private static final String CRASH_MSG = String.format("Player crashed!%n");
	private static final String WIN_MSG = String.format("Player wins!%n");
	private static final String DO_EXIT_MSG = "Player leaves the game";
	private static final String GAME_OVER_MSG = "[GAME OVER] "; 
	
	public String newLine; 

	protected Game game;
	

	public GamePrinter(Game game) {
		this.game = game;
		

		margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		String roadBorder = ROAD_BORDER_PATTERN + StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) *  game.getVisibility());
		indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE,  game.getVisibility() - 1) + laneDelimiter + SPACE;

		indentedLlanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);
		newLine =  System.getProperty("line.separator");
	}

	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		// Game Status
		
		str.append(game.getGameStatus());
		
		// Paint game

		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;
		int camera = game.getCameraPosition();
		for (int y = 0; y < game.getRoadWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = camera; x < (camera + game.getVisibility()); x++) {
				str.append(StringUtils.centre(game.positionToString(x, y), CELL_SIZE))
						.append(verticalDelimiter);
			}
			if (y <  game.getRoadWidth() - 1) {
				str.append(this.indentedLlanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);

		return str.toString();
	}

	
	public String endMessage(){
		
		String s;
		
		// TODO your code here
		StringBuilder sb = new StringBuilder();
		
		sb.append(GAME_OVER_MSG);
		if (game.getVictory()) {
			sb.append(WIN_MSG);
			sb.append("\r\n");
			if (!game.isTest()) {
				sb.append("New record!: ");
				sb.append(game.getTime());				
			}
			//sb.append() insert here with the correct commands the time
		} else if (game.getExit()){
			sb.append(DO_EXIT_MSG);
		} else {
			sb.append(CRASH_MSG);
		}
		s = sb.toString();
		return s;
	}
}
