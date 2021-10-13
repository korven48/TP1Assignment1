package es.ucm.tp1.model;

class ObstacleList extends List {
	private Obstacle[] obstacle;
	
	public ObstacleList() {
		this.obstacle = new Obstacle[this.CAPACITY];
		this.counter = 0;
	}
	
	public boolean add(Obstacle obs) {
		if (!this.isFull()) {
			this.obstacle[counter++] = obs;
			return true;
		}
		return false;
	}
	
	boolean remove(int pos) {
		if (this.counter - 1 == pos || this.counter - 1 > pos) {
			for (int i = pos; i <= this.counter - 1; i++) {
				if (obstacle[i+1] != null) {
					obstacle[i] = obstacle[i+1];
				}
			}				
			counter--;
			return true;
		}
		return false;
	}
	
	public Obstacle get(int index) {
		return obstacle[index];
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
		StringBuilder s = new StringBuilder();
		
		return s.toString();
	}
}
