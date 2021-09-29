package es.ucm.tp1.model;

public class Player {
	private static Player player = null;
	//Shall representiv the vertical postion of the car
	private int postionY = 0;
	private long coinsCount = 0;
	//Not sure... have to talk about it
	private boolean collision = false; 
		
	
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
		//Has to be filled
	}
	
	//Need?
	public void setCollision(final boolean collision) {
		this.collision = collision;
	}
}
