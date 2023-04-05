package hu.ulti.server.modelOld;

import java.util.ArrayList;
import java.util.List;

public class Card {

	private int id;
	private int colorId;

	public Card(int id, int colorId) {
		this.id = id;
		this.colorId = colorId;
	}

	public static List<Card> getAllCards() {
		List<Card> allCards = new ArrayList<Card>();

		allCards.add(new Card(0, 1));
		allCards.add(new Card(1, 1));
		allCards.add(new Card(2, 1));
		allCards.add(new Card(3, 1));
		allCards.add(new Card(4, 1));
		allCards.add(new Card(5, 1));
		allCards.add(new Card(6, 1));
		allCards.add(new Card(7, 1));

		allCards.add(new Card(8, 2));
		allCards.add(new Card(9, 2));
		allCards.add(new Card(10, 2));
		allCards.add(new Card(11, 2));
		allCards.add(new Card(12, 2));
		allCards.add(new Card(13, 2));
		allCards.add(new Card(14, 2));
		allCards.add(new Card(15, 2));

		allCards.add(new Card(16, 3));
		allCards.add(new Card(17, 3));
		allCards.add(new Card(18, 3));
		allCards.add(new Card(19, 3));
		allCards.add(new Card(20, 3));
		allCards.add(new Card(21, 3));
		allCards.add(new Card(22, 3));
		allCards.add(new Card(23, 3));

		allCards.add(new Card(24, 4));
		allCards.add(new Card(25, 4));
		allCards.add(new Card(26, 4));
		allCards.add(new Card(27, 4));
		allCards.add(new Card(28, 4));
		allCards.add(new Card(29, 4));
		allCards.add(new Card(30, 4));
		allCards.add(new Card(31, 4));

		return allCards;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
}
