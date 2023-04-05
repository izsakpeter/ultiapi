package hu.ulti.server.model;

import java.util.List;

public class Game {

	private long lastModificationTimeStamp = 0;
	private List<Player> players;

	public Game() {
	}

	public Game(long lastModificationTimeStamp, List<Player> players) {
		super();
		this.lastModificationTimeStamp = lastModificationTimeStamp;
		this.players = players;
	}

	public long getLastModificationTimeStamp() {
		return lastModificationTimeStamp;
	}

	public void setLastModificationTimeStamp(long lastModificationTimeStamp) {
		this.lastModificationTimeStamp = lastModificationTimeStamp;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public Game clone() {
		return new Game(this.lastModificationTimeStamp, this.players);
	}
}
