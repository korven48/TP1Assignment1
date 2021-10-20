package es.ucm.tp1.model;

final class ObjectList extends List {
	private GameObject[] objectArray;
	
	public ObjectList() {
		this.objectArray = new GameObject[List.CAPACITY];
		this.counter = 0;
	}
	
	public boolean add(GameObject gameObject) {
		if (!this.isFull()) {
			this.objectArray[counter++] = gameObject;
			return true;
		}
		return false;
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
	
	public void removeDead() {
		GameObject gameObject;
		for (int i = 0; i < this.size(); i++) {
			gameObject = this.get(i);
			if (! gameObject.isAlive()) {
				remove(i);
			}			
		}
	}
	
	public GameObject get(int index) {
		if (index < counter && index >= 0 && index < List.CAPACITY) {
			return objectArray[index];
		}
		return null; 
	}
	
	public boolean isObjectInPos(int x, int y) {
		boolean out = false;
		GameObject gameObject;
		for (int i = 0; i < this.size(); i++) {
			gameObject = this.get(i);
			if (gameObject.isInPos(x, y) ) {
				out = true;
			}
		}
		return out;
	}
	
	@Override
	public boolean isFull() {
		if (this.counter == List.CAPACITY) return true;
		return false;
	}
	
	@Override
	public boolean isEmpty() {
		if (this.counter == 0) return true;
		return false; 
	}
	
	@Override
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
