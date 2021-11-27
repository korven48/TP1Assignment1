package es.ucm.tp1.model.Elements;

import java.util.List;

import es.ucm.tp1.model.Collider;

import java.util.ArrayList;

public final class GameElementContainer {
	protected final static int CAPACITY = 100;
	private List<GameElement> gameElements;
	
	public GameElementContainer() {
		this.gameElements = new ArrayList<>();
	}

	public boolean add(GameElement gameObject) {
		if (!this.isFull()) {
			this.gameElements.add(gameObject);
			gameObject.onEnter();
			return true;
		}
		return false;
	}
	

	public void update() {
		GameElement gameObject;
		for (int i = 0; i < this.size(); i++) {
			gameObject = this.get(i);
			gameObject.update();
		}
	}
	
	public boolean remove(int pos) {
		int size = this.gameElements.size();
		if ((size - 1 == pos || size - 1 > pos) && size > 0) {
			gameElements.remove(pos);
			return true;
		}
		return false;
	}
	
	public void remove(int x, int y) {
		GameElement gameObject;
		for (int i = 0; i < this.size(); i++) {
			gameObject = get(i);
			if (gameObject.isInPos(x, y) ) {
				remove(i);
				break;
			}
		}
	}
	
	public void removeDead() {
		GameElement gameElement;
		for (int i = 0; i < this.size(); i++) {
			gameElement = this.get(i);
			if (!gameElement.isAlive()) {
				if (remove(i)) {
					gameElement.onDelete();
				}
			}			
		}
	}
	
	// This could be breaking encapsulation
	public GameElement get(int index) {
		int counter = this.gameElements.size();
		if (index < counter && index >= 0 && index < CAPACITY) {
			return gameElements.get(index);
		}
		return null; 
	}
	
	public boolean isObjectInPos(int x, int y) {
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
	
	public Collider getObjectInPosition(int x, int y) {
		GameElement elem;
		Collider out;
		int index = -1;
		for (int i = 0; i < gameElements.size(); i++) {
			elem = gameElements.get(i);
			if (elem.isInPos(x, y)) {
				index = i;
			}
		}
		if (index == -1) {
			out = null;
		} else {
			out = (Collider) gameElements.get(index);			
		}
		return out;
	}
	
	public boolean isFull() {
		if ((this.gameElements.size()) == CAPACITY) return true;
		return false;
	}
	
	public boolean isEmpty() {
		if ((this.gameElements.size()) == 0) return true;
		return false; 
	}
	
	public int size() {
		return this.gameElements.size();
	}
	
	@Override
	public String toString() {
		int counter = this.gameElements.size();
		String stringOut = String.format("Elements of the list:%n%n");
		for(int i = 0; i < counter; i++) {
			stringOut += String.format("%s%n", gameElements.get(i));
		}
		return stringOut;
	}

	public void clear() {
		gameElements.clear();		
	}
}
