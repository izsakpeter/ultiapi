package hu.ulti.server.model;

import java.util.List;

public class Player {

	private int playerId = -1;
	private String playerName;
	private List<Card> hand;
	private boolean isColorOrder = true;
	private boolean isColorForced = false;
	private boolean isPlaying;

	public Player() {
	}

	public Player(int playerId, String playerName) {
		this.playerId = playerId;
		this.playerName = playerName;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public boolean isColorOrder() {
		return isColorOrder;
	}

	public void setColorOrder(boolean isColorOrder) {
		this.isColorOrder = isColorOrder;
	}

	public boolean isColorForced() {
		return isColorForced;
	}

	public void setColorForced(boolean isColorForced) {
		this.isColorForced = isColorForced;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
}
