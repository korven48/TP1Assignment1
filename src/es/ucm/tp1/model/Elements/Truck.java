package es.ucm.tp1.model.Elements;

import es.ucm.tp1.model.Game;

public class Truck extends Obstacle {
	private static final String NAME = "truck";
	private static final String CON_SYMBOL = "←"; // \u2190
	
	public Truck(Game game, int x, int y) {
		super(game, x, y, NAME); 
		// resistance not specified
		this.resistance = 1;
		this.symbol = CON_SYMBOL;
	}
	
	public Truck() {
		super(NAME);
	}

	@Override
	public boolean isAdvanced() {
		return true;
	}
	
	@Override
	public void update() {
		super.update();
		x--;
	}
	
	@Override
	public boolean receiveWave() {
		//Because the car moves also 1 to the left we have 2 to the right
		this.x += 2;
		return true;
	}
	
	@Override
	public Truck create(Game game, int x, int y) {
		return new Truck(game, x, y);
	}
}