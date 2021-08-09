package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

import hu.ulti.server.Helper;

public class Card {

	private int orderColorId;
	private int orderColorlessId;
	private int colorId;

	public Card(int orderColorlessId, int orderColorId, int colorId) {
		this.orderColorId = orderColorId;
		this.orderColorlessId = orderColorlessId;
	}

	public static List<Card> getAllCards() {
		List<Card> allCards = new ArrayList<Card>();

		allCards.add(new Card(0, 0, 1));
		allCards.add(new Card(1, 1, 1));
		allCards.add(new Card(2, 2, 1));
		allCards.add(new Card(3, 6, 1));
		allCards.add(new Card(4, 3, 1));
		allCards.add(new Card(5, 4, 1));
		allCards.add(new Card(6, 5, 1));
		allCards.add(new Card(7, 7, 1));

		allCards.add(new Card(8, 8, 2));
		allCards.add(new Card(9, 9, 2));
		allCards.add(new Card(10, 10, 2));
		allCards.add(new Card(11, 14, 2));
		allCards.add(new Card(12, 11, 2));
		allCards.add(new Card(13, 12, 2));
		allCards.add(new Card(14, 13, 2));
		allCards.add(new Card(15, 15, 2));

		allCards.add(new Card(16, 16, 3));
		allCards.add(new Card(17, 17, 3));
		allCards.add(new Card(18, 18, 3));
		allCards.add(new Card(19, 22, 3));
		allCards.add(new Card(20, 19, 3));
		allCards.add(new Card(21, 20, 3));
		allCards.add(new Card(22, 21, 3));
		allCards.add(new Card(23, 23, 3));

		allCards.add(new Card(24, 24, 4));
		allCards.add(new Card(25, 25, 4));
		allCards.add(new Card(26, 26, 4));
		allCards.add(new Card(27, 30, 4));
		allCards.add(new Card(28, 27, 4));
		allCards.add(new Card(29, 28, 4));
		allCards.add(new Card(30, 29, 4));
		allCards.add(new Card(31, 31, 4));

		return allCards;
	}

	public static List<Card> addTalon(Player player, List<Card> talon) {

		player.getHand().add(talon.get(0));
		player.getHand().add(talon.get(1));

		Helper.orderHand(player.getHand());

		return player.getHand();
	}

	public static List<Card> getTalonById(List<Integer> talonid) {

		List<Card> talon = new ArrayList<Card>();
		List<Card> allCards = getAllCards();

		for (Card card : allCards) {
			if (card.getOrderColorId() == talonid.get(0) || card.getOrderColorId() == talonid.get(1))
				talon.add(card);
		}

		return talon;
	}

	public static List<Card> removeTalon(Player player, List<Card> talon) {

		for (Card card : player.getHand()) {
			if (card.getOrderColorId() == talon.get(0).getOrderColorId()) {
				player.getHand().remove(card);
				break;
			}
		}

		for (Card card : player.getHand()) {
			if (card.getOrderColorId() == talon.get(1).getOrderColorId()) {
				player.getHand().remove(card);
				break;
			}
		}

		Helper.orderHand(player.getHand());

		return player.getHand();
	}

	public static List<Card> removeCardbyId(Player player, int cardId) {

		for (Card card : player.getHand()) {
			if (card.getOrderColorId() == cardId) {
				player.getHand().remove(card);
				break;
			}
		}

		Helper.orderHand(player.getHand());

		return player.getHand();
	}

	public int getOrderColorId() {
		return orderColorId;
	}

	public void setOrderColorId(int orderColorId) {
		this.orderColorId = orderColorId;
	}

	public int getOrderColorlessId() {
		return orderColorlessId;
	}

	public void setOrderColorlessId(int orderColorlessId) {
		this.orderColorlessId = orderColorlessId;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
}
