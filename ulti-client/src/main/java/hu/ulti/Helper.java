package hu.ulti;

import java.util.List;
import java.util.Set;

import com.vaadin.flow.component.html.Image;

import hu.ulti.model.Card;

public class Helper {

	public static Image getCardImg(int cardId) {

		switch (cardId) {
			case 0:
				return new Image("/cards/m7.png", "makk7");
			case 1:
				return new Image("/cards/m8.png", "makk8");
			case 2:
				return new Image("/cards/m9.png", "makk9");
			case 3:
				return new Image("/cards/m10.png", "makk10");
			case 4:
				return new Image("/cards/mal.png", "makkalso");
			case 5:
				return new Image("/cards/mfel.png", "makkfelso");
			case 6:
				return new Image("/cards/mk.png", "makkkiraly");
			case 7:
				return new Image("/cards/masz.png", "makkasz");
			case 8:
				return new Image("/cards/z7.png", "zold7");
			case 9:
				return new Image("/cards/z8.png", "zold8");
			case 10:
				return new Image("/cards/z9.png", "zold9");
			case 11:
				return new Image("/cards/z10.png", "zold10");
			case 12:
				return new Image("/cards/zal.png", "zoldalso");
			case 13:
				return new Image("/cards/zfel.png", "zoldfelso");
			case 14:
				return new Image("/cards/zk.png", "zoldkiraly");
			case 15:
				return new Image("/cards/zasz.png", "zoldasz");
			case 16:
				return new Image("/cards/t7.png", "tok7");
			case 17:
				return new Image("/cards/t8.png", "tok8");
			case 18:
				return new Image("/cards/t9.png", "tok9");
			case 19:
				return new Image("/cards/t10.png", "tok10");
			case 20:
				return new Image("/cards/tal.png", "tokalso");
			case 21:
				return new Image("/cards/tfel.png", "tokfelso");
			case 22:
				return new Image("/cards/tk.png", "tokkiraly");
			case 23:
				return new Image("/cards/tasz.png", "tokasz");
			case 24:
				return new Image("/cards/p7.png", "piros7");
			case 25:
				return new Image("/cards/p8.png", "piros8");
			case 26:
				return new Image("/cards/p9.png", "piros9");
			case 27:
				return new Image("/cards/p10.png", "piros10");
			case 28:
				return new Image("/cards/pal.png", "pirosalso");
			case 29:
				return new Image("/cards/pfel.png", "pirosfelso");
			case 30:
				return new Image("/cards/pk.png", "piroskiraly");
			case 31:
				return new Image("/cards/pasz.png", "pirosasz");
		}

		return null;
	}

	private static List<Card> orderedCards;

	public static List<Card> getOrderedHand(List<Card> cards, boolean isColorOrder) {

		orderedCards = cards;

		if (!isColorOrder) {
			int m10Index = -1;
			int z10Index = -1;
			int t10Index = -1;
			int p10Index = -1;

			for (int i = 0; i < cards.size(); i++) {

				if (cards.get(i).getOrderColorId() == 3) {
					m10Index = i;
				}

				if (cards.get(i).getOrderColorId() == 11) {
					z10Index = i;
				}

				if (cards.get(i).getOrderColorId() == 19) {
					t10Index = i;
				}

				if (cards.get(i).getOrderColorId() == 27) {
					p10Index = i;
				}
			}

			if (m10Index != -1)
				orderCards(m10Index, 4, 5, 6);

			if (z10Index != -1)
				orderCards(z10Index, 12, 13, 14);

			if (t10Index != -1)
				orderCards(t10Index, 20, 21, 22);

			if (p10Index != -1)
				orderCards(p10Index, 28, 29, 30);
		}

		return orderedCards;
	}

	private static void orderCards(int index, int poz1, int poz2, int poz3) {
		if ((index + 1 < orderedCards.size()) && (orderedCards.get(index + 1).getOrderColorId() == poz1
				|| orderedCards.get(index + 1).getOrderColorId() == poz2
				|| orderedCards.get(index + 1).getOrderColorId() == poz3)) {

			Card tmp = orderedCards.get(index);
			orderedCards.remove(index);
			orderedCards.add(index + 1, tmp);
		}

		if ((index + 2 < orderedCards.size()) && (orderedCards.get(index + 2).getOrderColorId() == poz2
				|| orderedCards.get(index + 2).getOrderColorId() == poz3)) {

			Card tmp = orderedCards.get(index + 1);
			orderedCards.remove(index + 1);
			orderedCards.add(index + 2, tmp);
		}

		if ((index + 3 < orderedCards.size()) && orderedCards.get(index + 3).getOrderColorId() == poz3) {

			Card tmp = orderedCards.get(index + 2);
			orderedCards.remove(index + 2);
			orderedCards.add(index + 3, tmp);
		}
	}

	public static int getSelectedId(String color) {
		switch (color) {
			case "makk":
				return 1;
			case "zold":
				return 2;
			case "tok":
				return 3;
			case "piros":
				return 4;
		}
		return 1;
	}

	public static List<Integer> getCallList(String value, Set<String> value2) {
		// TODO Auto-generated method stub
		return null;
	}
}
