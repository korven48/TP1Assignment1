package es.ucm.tp1.model;

public class Player {
	private static Player player = null;
	//Shall representiv the vertical postion of the car
	private final static int speed = 1; 
	private int postionY = 0;
	private long coinsCount = 0;
	//Not sure... have to talk about it
	private boolean collision = false;
	//if the car hits an object it is destroyed 
	private int resistance = 1; 
	
	private void Player() {
		player = new Player();
	}
	
	public static Player getPlayer(final boolean reset) {
		if (player == null || reset) {
			player = new Player(); 
		}			
		return player; 
	}
	
	public void setCoinCounterUp() {
		this.coinsCount++;
	}	
	
	public void setPostion(final byte direction) {
		if (direction == 1 && !(this.postionY == 1)) {
			this.postionY++;
		} else if (direction == -1 && !(this.postionY == -1)) {
			this.postionY--;
		}
	}
	
	//Need?
	public void setCollision(final boolean collision) {
		if (collision) {
			this.resistance--;
			this.collision = collision;
		}
	}
}
