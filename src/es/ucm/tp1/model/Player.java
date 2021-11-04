package es.ucm.tp1.model;

public class Player extends GameElement{
	private static final String ALIVE_PLAYER = ">";
	private static final String CRASHED_PLAYER = "@";
	
	private static Player player = null;
	private final static int speed = 1; 
	
	private int coinsCount;
	private boolean crashed;
	private int resistance;
	
	private Player(int startingPostion, Game game) {
		super(0, startingPostion, game);
		this.coinsCount = 5;
		this.resistance = 1;
		crashed = false;
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
		if (player.resistance == 0) {
			player.crashed = true;			
		}
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
	
	protected String getSymbol() {
		String symbol;
		if (this.isAlive()) {
			symbol = ALIVE_PLAYER;
		} else {
			symbol = CRASHED_PLAYER;
		}
		return symbol;
	}
	
	@Override
	public String toString() {
		return getSymbol();
	}
	
	@Override
	public boolean isAlive() {
		return ! isCrashed();
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		doCollision();
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addCoin() {
		this.coinsCount++;
	}
	
	public int getCoins () {
		return coinsCount;
	}
	public boolean isCrashed() {
		return crashed;
	}
}
