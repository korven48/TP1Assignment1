package es.ucm.tp1.model;

interface ColliderCallback {
	public void reciveDamage();

	void moveForward(int steps);
	public void addCoins(int coins);
}
