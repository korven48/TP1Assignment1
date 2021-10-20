package es.ucm.tp1.model;

public class Player extends GameElement{
	private static Player player = null;
	private final static int speed = 1; 
	
	private int coinsCount;
	private boolean crashed;
	private int resistance; 

	
	private Player(int startingPostion) {
		super(0, startingPostion);
		this.coinsCount = 5;
		this.resistance = 1;
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
		// UP, DOWN, FOREWARD, NONE uses the Enum-Direction
		if (direction.equals(Direction.UP)) 
			this.y--;
		else if (direction.equals(Direction.DOWN))
			this.y++;
		
		if (! direction.equals(Direction.NONE))
			this.x++;
	}
	
	public void setCollision() {
		this.resistance--;
		if (resistance == 0) {
			this.crashed = true;			
		}
	}
	
	public boolean isCrashed() {
		return crashed;
	}

	@Override
	public boolean isAlive() {
		return ! isCrashed();
	}
}
