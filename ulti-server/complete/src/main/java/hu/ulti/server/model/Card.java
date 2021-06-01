package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

import hu.ulti.server.Helper;

public class Card {
	private static final String MAKK = "makk";
	private static final String ZOLD = "zold";
	private static final String TOK = "tok";
	private static final String PIROS = "piros";

	private static final String HETES = "7";
	private static final String NYOLCAS = "8";
	private static final String KILENCES = "9";
	private static final String TIZES = "10";
	private static final String ALSO = "a";
	private static final String FELSO = "f";
	private static final String KIRALY = "k";
	private static final String ASZ = "asz";

	private int colorId;
	private int colorlessId;
	private String color;
	private String value;
	private boolean isTalon;

	public Card(int colorlessId, int colorId, String color, String value) {
		this.colorId = colorId;
		this.colorlessId = colorlessId;
		this.color = color;
		this.value = value;
		this.isTalon = false;
	}

	public static List<Card> getAllCards() {
		List<Card> allCards = new ArrayList<Card>();

		allCards.add(new Card(0, 0, MAKK, HETES));
		allCards.add(new Card(1, 1, MAKK, NYOLCAS));
		allCards.add(new Card(2, 2, MAKK, KILENCES));
		allCards.add(new Card(3, 6, MAKK, TIZES));
		allCards.add(new Card(4, 3, MAKK, ALSO));
		allCards.add(new Card(5, 4, MAKK, FELSO));
		allCards.add(new Card(6, 5, MAKK, KIRALY));
		allCards.add(new Card(7, 7, MAKK, ASZ));

		allCards.add(new Card(8, 8, ZOLD, HETES));
		allCards.add(new Card(9, 9, ZOLD, NYOLCAS));
		allCards.add(new Card(10, 10, ZOLD, KILENCES));
		allCards.add(new Card(11, 14, ZOLD, TIZES));
		allCards.add(new Card(12, 11, ZOLD, ALSO));
		allCards.add(new Card(13, 12, ZOLD, FELSO));
		allCards.add(new Card(14, 13, ZOLD, KIRALY));
		allCards.add(new Card(15, 15, ZOLD, ASZ));

		allCards.add(new Card(16, 16, TOK, HETES));
		allCards.add(new Card(17, 17, TOK, NYOLCAS));
		allCards.add(new Card(18, 18, TOK, KILENCES));
		allCards.add(new Card(19, 22, TOK, TIZES));
		allCards.add(new Card(20, 19, TOK, ALSO));
		allCards.add(new Card(21, 20, TOK, FELSO));
		allCards.add(new Card(22, 21, TOK, KIRALY));
		allCards.add(new Card(23, 23, TOK, ASZ));

		allCards.add(new Card(24, 24, PIROS, HETES));
		allCards.add(new Card(25, 25, PIROS, NYOLCAS));
		allCards.add(new Card(26, 26, PIROS, KILENCES));
		allCards.add(new Card(27, 30, PIROS, TIZES));
		allCards.add(new Card(28, 27, PIROS, ALSO));
		allCards.add(new Card(29, 28, PIROS, FELSO));
		allCards.add(new Card(30, 29, PIROS, KIRALY));
		allCards.add(new Card(31, 31, PIROS, ASZ));

		return allCards;
	}

	public static List<Card> showTalon(List<Card> hand, int orderId) {

		for (Card card : hand) {
			card.setTalon(false);
		}

		Helper.orderHand(hand, orderId);

		return hand;
	}

	public static List<Card> removeTalon(List<Card> hand, int orderId, int talon1, int talon2) {

		for (Card card : hand) {
			if (card.getColorId() == talon1) {
				hand.remove(card);
				break;
			}
		}

		for (Card card : hand) {
			if (card.getColorId() == talon2) {
				hand.remove(card);
				break;
			}
		}

		Helper.orderHand(hand, orderId);

		return hand;
	}

	public static List<Card> addTalon(List<Card> hand, int orderId, int talon1, int talon2) {
		List<Card> cards = getAllCards();

		for (Card card : cards) {
			if (card.getColorId() == talon1 || card.getColorId() == talon2) {
				card.setTalon(true);
				hand.add(card);
			}
		}

		Helper.orderHand(hand, orderId);

		return hand;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public int getColorlessId() {
		return colorlessId;
	}

	public void setColorlessId(int colorlessId) {
		this.colorlessId = colorlessId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isTalon() {
		return isTalon;
	}

	public void setTalon(boolean isTalon) {
		this.isTalon = isTalon;
	}
}
