package es.ucm.tp1.model;

public interface Collider {
	boolean doCollision();
	boolean receiveCollision(ColliderCallback player);
	default boolean receiveShot() {
		//Deafult: Do nothing
		return false; 
	};
}
