package es.ucm.tp1.model;

public class Player extends GameElement {
	private static final String ALIVE_PLAYER = ">";
	private static final String CRASHED_PLAYER = "@";
	
	private static Player player = null;
//	private final static int speed = 1;
	
	private int coinsCount;
	private int resistance;
	
	private Player(int startingPostion, Game game) {
		super(0, startingPostion, game);
		this.coinsCount = 5;
		this.resistance = 1;
		symbol = ALIVE_PLAYER;
	}
	
	public static Player getPlayer(final boolean reset, int startingPostion, Game game) {
		if (player == null || reset) {
			player = new Player(startingPostion, game); 
		}
		return player; 
	}
	
	@Override
	public boolean doCollision() {
		GameElement gameElement = game.getObjectInPosition(x, y);
		if (gameElement != null) {
			return gameElement.receiveCollision(this);
		}
		return false;
	}
	
	@Override
	public void reciveDamage () {
		player.resistance--;
	}
	
	public void move(Direction direction) {
		// UP, DOWN, FOREWARD, NONE uses the Enum Direction
		if (direction.equals(Direction.UP)) 
			this.y--;
		else if (direction.equals(Direction.DOWN))
			this.y++;
		
		if (! direction.equals(Direction.NONE))
			this.x++;
	}
	
	@Override
	public void moveForward(int steps) {
		this.x = this.x + steps;  
	}
	
	@Override
	public String toString() {
		return getSymbol();
	}
	
	@Override
	public boolean isAlive() {
		return !isCrashed();
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void update() {
		doCollision();
		if (isCrashed()) {
			symbol = CRASHED_PLAYER;
		}
	}

	@Override
	public void onDelete() {
		
	}
	
	@Override
	public void addCoin() {
		this.coinsCount++;
	}
	
	public int getCoins () {
		return coinsCount;
	}
	public boolean isCrashed() {
		return player.resistance == 0;
	}
}
