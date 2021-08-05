package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int id;
	private boolean isColorOrder;

	private List<Card> hand;
	private int forcedColorId = 0;
	private boolean isCallOk = true;
	private List<Strike> strikes = new ArrayList<Strike>();

	public Player() {
		this.isColorOrder = true;
	}

	public Player(List<Card> hand) {
		this.isColorOrder = true;
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
