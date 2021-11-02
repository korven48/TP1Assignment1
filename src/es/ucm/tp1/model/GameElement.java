package es.ucm.tp1.model;

abstract class GameElement {
	protected int x;
	protected int y;
	protected Game game;
	
	protected GameElement(int x, int y) {
		super();
		this.x = x;
		this.y = y;		
	}
	
	protected GameElement(Game game, int x, int y) {
		super();
		this.game = game; 
		this.x = x;
		this.y = y;		
	}
	
	protected abstract boolean isAlive();

	protected int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
	}

	protected int getY() {
		return y;
	}

	void setY(int y) {
		this.y = y;
	}
	
	protected boolean isInPos(int x, int y) {
		boolean out = false;
		if (this.getX() == x && this.getY() == y) {
			return true;
		} 
		return out;
	}
}
