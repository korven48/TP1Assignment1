package es.ucm.tp1.model;

final class GameElementContainer {
	protected final static int CAPACITY = 100;
	protected int counter;
	private GameElement[] objectArray;
	
	public GameElementContainer() {
		this.objectArray = new GameElement[CAPACITY];
		this.counter = 0;
	}

	protected boolean add(GameElement gameObject) {
		if (!this.isFull()) {
			this.objectArray[counter++] = gameObject;
			gameObject.onEnter();
			return true;
		}
		return false;
	}
	

	void update() {
		GameElement gameObject;
		for (int i = 0; i < this.size(); i++) {
			gameObject = this.get(i);
			gameObject.update();
		}
	}
	
	boolean remove(int pos) {
		if (this.counter - 1 == pos || this.counter - 1 > pos) {
			for (int i = pos; i <= this.counter - 1; i++) {
				if (objectArray[i+1] != null) {
					objectArray[i] = objectArray[i+1];
				}
			}				
			counter--;
			return true;
		}
		return false;
	}
	
	protected void removeDead() {
		GameElement gameObject;
		for (int i = 0; i < this.size(); i++) {
			gameObject = this.get(i);
			if (! gameObject.isAlive()) {
				remove(i);
				gameObject.onDelete();
			}			
		}
	}
	
	public GameElement get(int index) {
		if (index < counter && index >= 0 && index < CAPACITY) {
			return objectArray[index];
		}
		return null; 
	}
	
	protected boolean isObjectInPos(int x, int y) {
		boolean out = false;
		GameElement gameObject;
		for (int i = 0; i < this.size(); i++) {
			gameObject = this.get(i);
			if (gameObject.isInPos(x, y) ) {
				out = true;
			}
		}
		return out;
	}
	

	public boolean isFull() {
		if (this.counter == CAPACITY) return true;
		return false;
	}
	
	public boolean isEmpty() {
		if (this.counter == 0) return true;
		return false; 
	}
	
	public int size() {
		return this.counter;
	}
	
	@Override
	public String toString() {
		String stringOut = String.format("Elements of the list:%n%n");
		for(int i = 0; i < this.counter; i++) {
			stringOut += String.format("%s%n", objectArray[i]);
		}
		return stringOut;
	}
}
