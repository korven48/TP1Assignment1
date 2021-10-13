package es.ucm.tp1.model;

final class CoinList extends List {
	private Coin[] coinArray;
	
	public CoinList() {
		this.coinArray = new Coin[this.CAPACITY];
		this.counter = 0;
	}
	
	public boolean add(Coin coin) {
		if (!this.isFull()) {
			this.coinArray[counter++] = coin;
			return true;
		}
		return false;
	}
	
	boolean remove(int pos) {
		if (this.counter - 1 == pos || this.counter - 1 > pos) {
			for (int i = pos; i <= this.counter - 1; i++) {
				if (coinArray[i+1] != null) {
					coinArray[i] = coinArray[i+1];
				}
			}				
			counter--;
			return true;
		}
		return false;
	}
	
	public Coin get(int index) {
		return coinArray[index];
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
