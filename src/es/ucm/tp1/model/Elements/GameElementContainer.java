package es.ucm.tp1.model.Elements;

import java.util.List;
import java.util.Random;

import es.ucm.tp1.Exceptions.lowlevelexceptions.CreationError;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.model.Collider;
import es.ucm.tp1.model.Serializable;

import java.util.ArrayList;

public final class GameElementContainer implements Serializable {
	protected static final int CAPACITY = 100;
	private List<GameElement> gameElements;
	private static final String ERROR_Creation = "Could not generat this Element";
	
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
	
	@Override
	public String getSerialized() {
		StringBuilder str = new StringBuilder();
		GameElement elem;
		for (int i = 0; i < this.size(); i++){
			elem = this.get(i);
			str.append(elem.getSerialized() + "\n");
		}
		return str.toString();
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
	
	private GameElement get(int index) {
		int counter = this.gameElements.size();
		if (index < counter && index >= 0 && index < CAPACITY) {
			return gameElements.get(index);
		}
		return null; 
	}
	
	//Thats not a list its an array from the type IPosElement. Due to this fact everything is fine
	//If you have questions ask in class. 
	public IPosElement[] getAllPosElements() {
		IPosElement[] gameElements = new IPosElement[this.size()];
		for(int i = 0; i < this.size(); i++) gameElements[i] = (IPosElement) this.get(i);		
		return gameElements;
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
	
	//That when we call this method from inside this class it is undependend of the datatyp still. 
	//Otherwise we would have to change diffrent methods as well.
	//We changed it but we still use it inside this class because some methods need it.
	//Because the internal need we let it like this and if the datastructure changes we just change this method
	private int size() {
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
	
	public String positionToStringLogic(int x, int y, Level level, IPosElement player, String FINISH_LINE) {
		String position = "";
		GameElement elem = null;
		if (x ==  level.getLength()) {
			position = FINISH_LINE;
		} 
		if(player.isInPos(x, y)) {
			position = player.toString();
		} else {
			GameElement elem2;
			for (int i = 0; i < this.gameElements.size(); i++) {
				elem2 = gameElements.get(i);
				if (elem2.isInPos(x, y)) {
					elem = elem2;
				}
			}
			if (elem != null) {
				position = elem.toString();
			} 
		}
		return position;
	}
	
	
	public void processAddingObject(GameElement gameElement, double elementFrequency, Random _rand) throws CreationError {
		GameElement element = null;
		Random rand = _rand;
		double createElement = rand.nextDouble();
		if (createElement < elementFrequency) {
			if (this.gameElements != null) {
				for (int i = 0; i < this.size(); i++) {
					element = this.gameElements.get(i);
					if (element.isInPos(gameElement.getX(), gameElement.getY())) {
						throw new CreationError(GameElementContainer.ERROR_Creation);
					}
				}
			}
			addObject(gameElement);
			gameElement.onEnter();
		}
	}
	
	private void addObject(GameElement gameElement) {
		int x = gameElement.getX();
		int y = gameElement.getY();
		if (this.isObjectInPos(x, y)) {
			this.remove(x, y);
		}
		this.add(gameElement);
	}
}
