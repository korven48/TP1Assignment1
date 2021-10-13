package es.ucm.tp1.model;

final class CoinList extends List {
	private Coin[] coinArray;
	
	public CoinList() {
		this.coinArray = new Coin[List.CAPACITY];
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
		if (index < counter && index >= 0 && index < List.CAPACITY) {
			return coinArray[index];
		}
		return null; 
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
			stringOut += String.format("%s%n", coinArray[i]);
		}
		return stringOut;
	}
}
