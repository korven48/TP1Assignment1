package es.ucm.tp1.model;

public class Player {
	private static Player player = null;
	//Shall representiv the vertical postion of the car
	private final static int speed = 1; 
	//Insert the right postion depanding on the level mode
	private int postionY;
	private static final int postionX = 0; 

	private long coinsCount = 0;
	//Not sure... have to talk about it
	private boolean collision = false;
	//if the car hits an object it is destroyed 
	private int resistance = 1; 
	
	private Player(int startingPostion) {
		this.postionY = startingPostion;
	}
	
	public static Player getPlayer(final boolean reset, int startingPostion) {
		if (player == null || reset) {
			player = new Player(startingPostion); 
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
	
	public int getPostionX() {
		return postionX;
	}

	public int getPostionY() {
		return postionY;
	}
}
