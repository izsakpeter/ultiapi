package hu.ulti.server.model;

import java.util.List;

public class Game {

	private int order;
	private List<Card> hand;

	public Game() {
		this.order = 0;
	}

	public Game(List<Card> hand) {
		this.order = 0;
		this.hand = hand;
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
}
