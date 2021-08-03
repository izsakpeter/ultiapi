package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int id;
	private int order;
	private List<Card> hand;
	private int forcedColorId = 0;
	private boolean isCallOk = true;
	private List<Strike> strikes = new ArrayList<Strike>();

	public Player() {
		this.order = 0;
	}

	public Player(List<Card> hand) {
		this.order = 0;
		this.hand = hand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public int getForcedColorId() {
		return forcedColorId;
	}

	public void setForcedColorId(int forcedColorId) {
		this.forcedColorId = forcedColorId;
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

	public void setStrikes(Strike strike) {
		this.strikes.add(strike);
	}
}
