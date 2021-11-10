package es.ucm.tp1.model;

interface ColliderCallback {
	public void reciveDamage();
	public void addCoin();
	default void moveForward(int steps) {
		//Do noting
		}
}
