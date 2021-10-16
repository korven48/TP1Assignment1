package es.ucm.tp1.model;

abstract class List {
	protected final static int CAPACITY = 100;
	protected int counter; 
	abstract public boolean isFull();
	abstract public boolean isEmpty();
	abstract public int size();
}
