package es.ucm.tp1.model;

import es.ucm.tp1.control.Level;

public final class GameElementGenerator {
	public static void generateGameElements(Game game, Level level) {
		for(int x = game.getVisibility()/2; x < game.getRoadLength(); x ++) {
			game.tryToAddObject(new Obstacle(x, game.getRandomLane(), game), level.obstacleFrequency());
			game.tryToAddObject(new Coin(x, game.getRandomLane(), game), level.coinFrequency());
		}
	}
		
	public static void reset(Level level) {
		Obstacle.reset();
		Coin.reset();
	}
}
