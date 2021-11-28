package es.ucm.tp1.model;

public interface ColliderCallback {
	public void reciveDamage();
	void moveForward(int steps);
	public void addCoins(int coins);
	public void looseCoins();
}
