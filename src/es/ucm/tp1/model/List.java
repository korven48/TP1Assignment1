package es.ucm.tp1.model;

abstract class List {
	protected final static int CAPACITY = 100;
	protected int counter; 
	protected abstract boolean isFull();
	protected abstract boolean isEmpty();
	protected abstract int size();
}
