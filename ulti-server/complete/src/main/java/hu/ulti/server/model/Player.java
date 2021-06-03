package hu.ulti.server.model;

import java.util.List;

public class Player {

	private int id;
	private int order;
	private List<Card> hand;
	private int forcedCallId = 0;
	private boolean isCallOk = true;

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

	public int getForcedCallId() {
		return forcedCallId;
	}

	public void setForcedCallId(int forcedCallId) {
		this.forcedCallId = forcedCallId;
	}

	public boolean isCallOk() {
		return isCallOk;
	}

	public void setCallOk(boolean isCallOk) {
		this.isCallOk = isCallOk;
	}

}
