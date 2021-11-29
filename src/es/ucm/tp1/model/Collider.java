package es.ucm.tp1.model;

public interface Collider {
	boolean doCollision();
	boolean receiveCollision(ColliderCallback player);
	boolean receiveShot(ColliderCallback player);
	boolean receiveExplosion(ColliderCallback player);
	boolean receiveThunder();
	boolean receiveWave();
}
