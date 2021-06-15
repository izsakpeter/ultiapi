package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private int startingValue;
	private Player player;
	private List<Integer> previousCall = new ArrayList<Integer>();
	private boolean isGameReadyToStart = false;
	private int lastCallerId;
	private int activePlayer;
	private Strike kor = new Strike();

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

	public List<Integer> getPreviousCall() {
		return previousCall;
	}

	public void setPreviousCall(List<Integer> previousCall) {
		this.previousCall = previousCall;
	}

	public boolean isGameReadyToStart() {
		return isGameReadyToStart;
	}

	public void setGameReadyToStart(boolean isGameReadyToStart) {
		this.isGameReadyToStart = isGameReadyToStart;
	}

	public int getLastCallerId() {
		return lastCallerId;
	}

	public void setLastCallerId(int lastCallerId) {
		this.lastCallerId = lastCallerId;
	}

	public int getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Strike getKor() {
		return kor;
	}

	public void setKor(Strike kor) {
		this.kor = kor;
	}

}
