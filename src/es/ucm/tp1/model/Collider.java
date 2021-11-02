package es.ucm.tp1.model;

public interface Collider {
	boolean doCollision();
	boolean receiveCollision(Player player);
}
