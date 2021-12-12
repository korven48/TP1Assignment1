package es.ucm.tp1.control.commands;
import es.ucm.tp1.model.Game;

public interface Buyable {
	public int cost();
	public default void buy(Game game){
		game.playerPays(cost());
	};
}
