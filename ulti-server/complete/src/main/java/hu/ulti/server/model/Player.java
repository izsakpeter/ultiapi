package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int id = 0;
	private boolean isReady = false;
	private boolean isColorOrder = true;
	private List<Card> hand;
	private boolean isCallOk = true;
	private boolean isColorForced = false;
	private List<Strike> strikes = new ArrayList<Strike>();

	public Player() {
	}

	public Player(int id) {
		this.id = id;
		this.isReady = true;
	}

	public Player(List<Card> hand) {
		this.hand = hand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isColorOrder() {
		return isColorOrder;
	}

	public void setColorOrder(boolean isColorOrder) {
		this.isColorOrder = isColorOrder;
	}

	public void setStrikes(List<Strike> strikes) {
		this.strikes = strikes;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public boolean isCallOk() {
		return isCallOk;
	}

	public void setCallOk(boolean isCallOk) {
		this.isCallOk = isCallOk;
	}

	public List<Strike> getStrikes() {
		return strikes;
	}

	public void addStrike(Strike strike) {
		this.strikes.add(strike);
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public boolean isColorForced() {
		return isColorForced;
	}

	public void setColorForced(boolean isColorForced) {
		this.isColorForced = isColorForced;
	}
}
