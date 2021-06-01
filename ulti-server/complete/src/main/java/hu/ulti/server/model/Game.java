package hu.ulti.server.model;

public class Game {

	private int startingValue;
	private Player player;

	public Game() {
		this.startingValue = 0;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getStartingValue() {
		return startingValue;
	}

	public void setStartingValue(int startingValue) {
		this.startingValue = startingValue;
	}

}
