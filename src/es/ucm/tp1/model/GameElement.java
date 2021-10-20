package es.ucm.tp1.model;

public abstract class GameElement {
	protected int x;
	protected int y;
	
	public GameElement(int x, int y) {
		super();
		this.x = x;
		this.y = y;		
	}
	
	public abstract boolean isAlive();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isInPos(int x, int y) {
		boolean out = false;
		if (this.getX() == x && this.getY() == y) {
			return true;
		} 
		return out;
	}
}
