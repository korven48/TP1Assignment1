package es.ucm.tp1.model;

public abstract class GameElement implements Collider, ColliderCallback {
	protected int x;
	protected int y;
	protected Game game;
	protected String symbol;
	private final String NAME;
	
	public GameElement(int x, int y, Game game, String name) {
		super();
		this.x = x;
		this.y = y;
		this.game = game;
		this.NAME = name;
	}
	
	public GameElement(String name) {
		this.NAME = name;
	}
	
	public boolean isAdvanced() {
		return false;
	}
	
	protected GameElement parse(String word) {
		if (matchElementName(word))
			return this;
		return null;
	}
	

	private boolean matchElementName(String name) {
		return NAME.equals(name);
	}

	@Override
	public boolean receiveShot() {
		return false; 
	};
	
	@Override
	public void moveForward(int steps) {
		//Do nothing
	}
	
	@Override
	public boolean doCollision() {
		return false;
	}

	@Override
	public boolean receiveCollision(ColliderCallback player) {
		return false;
	}
 
	public boolean isInPos(int x, int y) {
		boolean out = false;
		if (this.getX() == x && this.getY() == y) {
			return true;
		} 
		return out;
	}
	
	@Override
	public String toString() {
		return symbol;
	}

	public abstract void onEnter();
	public abstract void update();
	public abstract void onDelete();
	public abstract boolean isAlive();

	@Override
	public void reciveDamage() {}
	
	@Override
	public void addCoins(int coins) {}
	
	public String getSymbol() {
		return symbol;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean receiveExplosion() {
		return false;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract GameElement create(int x, int y, Game game);
}
