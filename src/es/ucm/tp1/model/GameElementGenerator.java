package es.ucm.tp1.model;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.model.InstantActions.*;

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
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), level.obstacleFrequency());
			game.tryToAddObject(new Coin(x, game.getRandomLane(), game), level.coinFrequency());
		
		if (level .hasAdvancedObjects()) {
			game.tryToAddObject(new Wall(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
			game.tryToAddObject(new Turbo(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
			if (!SuperCoin.hasSuperCoin()) {
				game.tryToAddObject(new SuperCoin(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
			}
			game.tryToAddObject(new Truck(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
			game.tryToAddObject(new Pedestrian(game, x, 0), level.advancedObjectsFrequency());
		    }
		}
	}
	
	public static void generateRuntimeObjects(Game game) {
		// Note we use this method to create and inject new objects or actions on runtime.
		if (game.getLevel().hasAdvancedObjects()) {
			game.execute(new ThunderAction()); 
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
			game.addObject(currentElement.create(game, column, lane));			
			generated = true;
		}
		return generated;
	}

	public static void reset(Level level) {
		Obstacle.reset();
		Coin.reset();
	}
}
