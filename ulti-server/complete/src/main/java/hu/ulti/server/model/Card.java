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

	private int orderColorId;
	private int orderColorlessId;
	private String color;
	private String value;
	private int colorId;

	public Card(int orderColorlessId, int orderColorId, String color, String value, int colorId) {
		this.orderColorId = orderColorId;
		this.orderColorlessId = orderColorlessId;
		this.color = color;
		this.value = value;
	}

	public static List<Card> getAllCards() {
		List<Card> allCards = new ArrayList<Card>();

		allCards.add(new Card(0, 0, MAKK, HETES, 1));
		allCards.add(new Card(1, 1, MAKK, NYOLCAS, 1));
		allCards.add(new Card(2, 2, MAKK, KILENCES, 1));
		allCards.add(new Card(3, 6, MAKK, TIZES, 1));
		allCards.add(new Card(4, 3, MAKK, ALSO, 1));
		allCards.add(new Card(5, 4, MAKK, FELSO, 1));
		allCards.add(new Card(6, 5, MAKK, KIRALY, 1));
		allCards.add(new Card(7, 7, MAKK, ASZ, 1));

		allCards.add(new Card(8, 8, ZOLD, HETES, 2));
		allCards.add(new Card(9, 9, ZOLD, NYOLCAS, 2));
		allCards.add(new Card(10, 10, ZOLD, KILENCES, 2));
		allCards.add(new Card(11, 14, ZOLD, TIZES, 2));
		allCards.add(new Card(12, 11, ZOLD, ALSO, 2));
		allCards.add(new Card(13, 12, ZOLD, FELSO, 2));
		allCards.add(new Card(14, 13, ZOLD, KIRALY, 2));
		allCards.add(new Card(15, 15, ZOLD, ASZ, 2));

		allCards.add(new Card(16, 16, TOK, HETES, 3));
		allCards.add(new Card(17, 17, TOK, NYOLCAS, 3));
		allCards.add(new Card(18, 18, TOK, KILENCES, 3));
		allCards.add(new Card(19, 22, TOK, TIZES, 3));
		allCards.add(new Card(20, 19, TOK, ALSO, 3));
		allCards.add(new Card(21, 20, TOK, FELSO, 3));
		allCards.add(new Card(22, 21, TOK, KIRALY, 3));
		allCards.add(new Card(23, 23, TOK, ASZ, 3));

		allCards.add(new Card(24, 24, PIROS, HETES, 4));
		allCards.add(new Card(25, 25, PIROS, NYOLCAS, 4));
		allCards.add(new Card(26, 26, PIROS, KILENCES, 4));
		allCards.add(new Card(27, 30, PIROS, TIZES, 4));
		allCards.add(new Card(28, 27, PIROS, ALSO, 4));
		allCards.add(new Card(29, 28, PIROS, FELSO, 4));
		allCards.add(new Card(30, 29, PIROS, KIRALY, 4));
		allCards.add(new Card(31, 31, PIROS, ASZ, 4));

		return allCards;
	}

	public static List<Card> addTalon(Player player, List<Card> talon) {

		player.getHand().add(talon.get(0));
		player.getHand().add(talon.get(1));

		Helper.orderHand(player.getHand(), player.getOrder());

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

		Helper.orderHand(player.getHand(), player.getOrder());

		return player.getHand();
	}
	
	public static List<Card> removeCardbyId(Player player, int cardId) {
		
		for (Card card : player.getHand()) {
			if (card.getOrderColorId() == cardId) {
				player.getHand().remove(card);
				break;
			}
		}
		
		Helper.orderHand(player.getHand(), player.getOrder());
		
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

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
}
