package es.ucm.tp1.model;

public class Truck extends Obstacle {
	// Maybe its not an obstacle
	
	private static final String NAME = "truck";
	private static final String CON_SYMBOL = "←"; // \u2190
	
	public Truck(int x, int y, Game game) {
		// TODO Auto-generated constructor stub
		super(x, y, game, NAME); 
		this.resistance = 3;
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
	public Truck create(int x, int y, Game game) {
		return new Truck(x, y, game);
	}
}