package es.ucm.tp1.model;

interface ColliderCallback {
	public void reciveDamage();
	public void addCoin();
	void moveForward(int steps);
}
