package hu.ulti.server.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {

	private long lastModificationTimeStamp = 0;
	private List<Player> players;
	private boolean isRoundStarted = false;
	private Player player;
	private int activePlayerId;
	private List<Hand> hands;
	private List<Card> talon;
	private List<Score> scores;;

	public Game() {
	}

	public Game(long lastModificationTimeStamp, List<Player> players, boolean isRoundStarted, Player player,
			int activePlayerId, List<Hand> hands, List<Card> talon, List<Score> scores) {
		super();
		this.lastModificationTimeStamp = lastModificationTimeStamp;
		this.players = players;
		this.isRoundStarted = isRoundStarted;
		this.player = player;
		this.activePlayerId = activePlayerId;
		this.hands = hands;
		this.talon = talon;
		this.scores = scores;
	}

	public int getActivePlayerId() {
		return activePlayerId;
	}

	public void setActivePlayerId(int activePlayerId) {
		this.activePlayerId = activePlayerId;
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

	@JsonProperty(value = "isRoundStarted")
	public boolean isRoundStarted() {
		return isRoundStarted;
	}

	public void setRoundStarted(boolean isRoundStarted) {
		this.isRoundStarted = isRoundStarted;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Hand> getHands() {
		return hands;
	}

	public void setHands(List<Hand> hands) {
		this.hands = hands;
	}

	public List<Card> getTalon() {
		return talon;
	}

	public void setTalon(List<Card> talon) {
		this.talon = talon;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public Game clone() {
		return new Game(this.lastModificationTimeStamp, this.players, this.isRoundStarted, this.player,
				this.activePlayerId, this.hands, this.talon, this.scores);
	}
}
