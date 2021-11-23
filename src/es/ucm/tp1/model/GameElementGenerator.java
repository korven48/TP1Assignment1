package es.ucm.tp1.model;

import es.ucm.tp1.control.Level;

public final class GameElementGenerator {
	public static final GameElement[] AVAILABLE_GAMEELEMENTS = {
			// Should contain all the GameElements
			new Coin(),
			new Obstacle(),
			new Grenade(),
			// Advanced Game Elements
			new Wall(),
			new Turbo(),
			new SuperCoin(),
			new Truck(),
			new Pedestrian()
	};

	public static void generateGameElements(Game game, Level level) {
		for(int x = game.getVisibility()/2; x < game.getRoadLength(); x ++) {
			game.tryToAddObject(new Obstacle(x, game.getRandomLane(), game), level.obstacleFrequency());
			game.tryToAddObject(new Coin(x, game.getRandomLane(), game), level.coinFrequency());
		}
	}
	
	public static void generateGranade(Game game, int x, int y) {
		game.addObject(new Grenade(x, y, game));
	}

	private static GameElement getGameElement(String word) {
		GameElement gameElement = null;
		for (GameElement currentElement: GameElementGenerator.AVAILABLE_GAMEELEMENTS) {
			gameElement = currentElement.parse(word);
			if (gameElement != null) {
				break;
			}
		}
		return gameElement;
	}
	
	public static boolean generateCheatObject(Game game, String name) {
		boolean generated = false;
		GameElement currentElement = GameElementGenerator.getGameElement(name);
		if (currentElement != null && currentElement.isAdvanced()) {
			int lane = game.getRandomLane();
			int column = game.getCameraPosition() + game.getVisibility() - 1; // + position of player 
			game.addObject(currentElement.create(column, lane, game));			
			generated = true;
		}
		return generated;
	}

	public static void reset(Level level) {
		Obstacle.reset();
		Coin.reset();
	}
}
