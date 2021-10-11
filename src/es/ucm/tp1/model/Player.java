package es.ucm.tp1.model;

public class Player {
	private static Player player = null;
	//Shall represent the vertical position of the car
	private final static int speed = 1; 
	//Insert the right position depending on the level mode
	private int positionY;
//	private static final int positionX = 0; 
	private int positionX; 
	
	private int coinsCount = 0;
	//Not sure... have to talk about it
	private boolean crashed;
	//if the car hits an object it is destroyed 
	private int resistance = 1; 
	private static boolean isTimeStarted; 
	
	public static final byte UP = 1;
	public static final byte DOWN = -1;
	
	private Player(int startingPostion) {
		positionX = 0;
		this.positionY = startingPostion;
		isTimeStarted = false;
		crashed = false;
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
	
	public int getCoins () {
		return coinsCount;
	}
	
	public void move(Direction direction) {
		// UP, DOWN, FOREWARD, NONE
		if (direction.equals(Direction.UP)) 
			this.positionY--;
		else if (direction.equals(Direction.DOWN))
			this.positionY++;
		
		if (! direction.equals(Direction.NONE))
			this.positionX++;
	}
	
	//Need?
	public void setCollision() {
		this.resistance--;
		if (resistance == 0) {
			this.crashed = true;			
		}
	}
	
	public int getPostionX() {
		return positionX;
	}

	public int getPostionY() {
		return positionY;
	}

	public boolean isCrashed() {
		// TODO Auto-generated method stub
		return crashed;
	}
}
