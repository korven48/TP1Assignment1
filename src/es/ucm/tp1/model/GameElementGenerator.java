package es.ucm.tp1.model;

import es.ucm.tp1.Exceptions.lowlevelexceptions.GenerateNewGameElementException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.InvalidPositionException;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.model.Elements.Coin;
import es.ucm.tp1.model.Elements.GameElement;
import es.ucm.tp1.model.Elements.Grenade;
import es.ucm.tp1.model.Elements.Obstacle;
import es.ucm.tp1.model.Elements.Pedestrian;
import es.ucm.tp1.model.Elements.SuperCoin;
import es.ucm.tp1.model.Elements.Truck;
import es.ucm.tp1.model.Elements.Turbo;
import es.ucm.tp1.model.Elements.Wall;
import es.ucm.tp1.model.InstantActions.*;

public final class GameElementGenerator {
	private static final String NOT_AN_ADVANCED_GAME_ELEMENT = "Not an advanced game element";
	private static final String ERROR_IS_FULL = "The Container is full";
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
			game.doInstantAction(new ThunderAction()); 
		}
	}
	
	public static void generateGranade(Game game, int x, int y) throws InvalidPositionException {
		game.addObject(new Grenade(game, x, y));
	}

	private static GameElement getGameElement(String word) {
		GameElement gameElement = null;
		for (GameElement currentElement : GameElementGenerator.AVAILABLE_GAMEELEMENTS) {
			gameElement = currentElement.parse(word);
			if (gameElement != null) {
				break;
			}
		}
		return gameElement;
	}
	
	public static void generateCheatObject(Game game, String name) throws GenerateNewGameElementException {
		GameElement currentElement = GameElementGenerator.getGameElement(name);
		if (currentElement != null && currentElement.isAdvanced()) {
			int lane = game.getRandomLane();
			int column = game.getCameraPosition() + game.getVisibility() - 1; // + position of player 
			try {
				game.addObject(currentElement.create(game, column, lane));	
			} catch (Exception ex) {
				throw new GenerateNewGameElementException(GameElementGenerator.ERROR_IS_FULL, ex);
			}
		} else {
			throw new GenerateNewGameElementException(GameElementGenerator.NOT_AN_ADVANCED_GAME_ELEMENT);
		}
	}

	public static void reset(Level level) {
		Obstacle.reset();
		Coin.reset();
	}
}
