package es.ucm.tp1.model;

import es.ucm.tp1.Exceptions.lowlevelexceptions.InvalidPositionException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.NotEnoughCoinsException;
import es.ucm.tp1.model.Elements.GameElement;

public class Player extends GameElement{
	private static final String NAME = "player";
	private static final String ALIVE_PLAYER = ">";
	private static final String CRASHED_PLAYER = "@";
	public static final String INFO = "[Car] the racing car%n";
	
	private static Player player = null;
//	private final static int speed = 1;
	
	private int coinsCount;
	private int resistance;
	
	private Player(int startingPostion, Game game) {
		super(game, 0, startingPostion, NAME);
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
		Collider gameElement = game.getObjectInPosition(x, y);
		if (gameElement != null) {
			return gameElement.receiveCollision(this);
		}
		return false;
	}
	
	@Override
	public void reciveDamage () {
		player.resistance--;
	}
	
	@Override
	public void looseCoins() {
		super.looseCoins();
		coinsCount = 0;
	}
	
	boolean canMove(Direction direction) {
		boolean result = true;
		if (direction.equals(Direction.DOWN) && getY() >= game.level.getWidth() - 1)
			result = false;
		else if (direction.equals(Direction.UP) && getY() <= 0)
			result = false;
		return result;
	}
	
	void processingMovement(boolean shouldDisplay, Direction direction) throws InvalidPositionException {
		if (this.canMove(direction)) {
			if (!(direction.equals(Direction.NONE))) {
				move(direction);
				shouldDisplay = true;				
			}
			this.doCollision();
		} else {
			this.doCollision();
			throw new InvalidPositionException("WARNING: Coudn't move the player in that direction");
		}
	}
	
	public void move(Direction direction) {
		// UP, DOWN, FOREWARD, NONE uses the Enum Direction
		if (direction.equals(Direction.UP)) {
			this.y--;
		} else if (direction.equals(Direction.DOWN)) {
			this.y++;
		}
		if (! direction.equals(Direction.NONE)) {
			this.x++;
		}
	}
	
	@Override
	public void moveForward(int steps) {
		this.x += steps;  
	}
	
	@Override
	public String toString() {
		return symbol;
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
		//doCollision();
		if (isCrashed()) {
			symbol = CRASHED_PLAYER;
		}
		this.doCollision();
	}

	@Override
	public void onDelete() {
		
	}
	
	@Override
	public void addCoins(int amount) {
		coinsCount += amount;
	}
	
	public void payAble (int amount) throws NotEnoughCoinsException {
		if (!(amount <= this.coinsCount)) throw new NotEnoughCoinsException("Not enough coins!");
	}
	
	public boolean isCrashed() {
		return player.resistance <= 0;
	}

	public void pay(int amount) {
		this.coinsCount -= amount;
	}
	
	public int getCoins() {
		return this.coinsCount;
	}

	@Override
	public GameElement create(Game game, int x, int y) {
		// player doesnt create anything, does it?
		return null;
	}
	
	//This is the only GameElement should not move to the right
	@Override 
	public boolean receiveWave() {
		return false;
	}



}
