package es.ucm.tp1.model;

class Turbo extends GameElement {
	private boolean collected = false; 
	static int counter;
	static final int steps = 3;
	
	public Turbo(int x, int y, Game game) {
		super(x, y, game);
		// TODO Auto-generated constructor stub
		symbol = ">>>";
	}

	@Override
	public boolean receiveCollision(ColliderCallback player) {
		player.moveForward(Turbo.steps);
		return true; // true because the player crashes 
	}
	
	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		this.counter++;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		this.counter--;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return !isCollected();
	}
	
	protected void setCollected() {
		collected = true;
	}
	
	protected boolean isCollected() {
		return collected;
	}
	
	protected static void reset() {
		Coin.counter = 0;
	}
}
