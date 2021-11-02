package es.ucm.tp1.model;

final class ObjectList extends List {
	private GameElement[] objectArray;
	
	protected ObjectList() {
		this.objectArray = new GameElement[List.CAPACITY];
		this.counter = 0;
	}
	
	protected boolean add(GameElement gameObject) {
		if (!this.isFull()) {
			this.objectArray[counter++] = gameObject;
			return true;
		}
		return false;
	}
	
	protected boolean remove(int pos) {
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
			}			
		}
	}
	
	protected GameElement get(int index) {
		if (index < counter && index >= 0 && index < List.CAPACITY) {
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
	
	@Override
	protected boolean isFull() {
		if (this.counter == List.CAPACITY) return true;
		return false;
	}
	
	@Override
	protected boolean isEmpty() {
		if (this.counter == 0) return true;
		return false; 
	}
	
	@Override
	protected int size() {
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
