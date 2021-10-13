package es.ucm.tp1.model;

class ObstacleList extends List {
	private Obstacle[] obstacleArray;
	
	public ObstacleList() {
		this.obstacleArray = new Obstacle[this.CAPACITY];
		this.counter = 0;
	}
	
	public boolean add(Obstacle obs) {
		if (!this.isFull()) {
			this.obstacleArray[counter++] = obs;
			return true;
		}
		return false;
	}
	
	boolean remove(int pos) {
		if (this.counter - 1 == pos || this.counter - 1 > pos) {
			for (int i = pos; i <= this.counter - 1; i++) {
				if (obstacleArray[i+1] != null) {
					obstacleArray[i] = obstacleArray[i+1];
				}
			}				
			counter--;
			return true;
		}
		return false;
	}
	
	public Obstacle get(int index) {
		return obstacleArray[index];
	}
	
	@Override
	public boolean isFull() {
		if (this.counter == this.CAPACITY) return true;
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
			stringOut += String.format("%s%n", obstacleArray[i]);
		}
		return stringOut;
	}
}
