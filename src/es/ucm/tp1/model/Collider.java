package es.ucm.tp1.model;

public interface Collider {
	boolean doCollision();
	boolean receiveCollision(ColliderCallback player);
	boolean receiveShot();
	boolean receiveExplosion();
	boolean receiveThunder();
}
