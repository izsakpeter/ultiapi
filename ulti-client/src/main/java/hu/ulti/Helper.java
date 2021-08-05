package hu.ulti;

import java.util.List;

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

	public static List<Card> getOrderedHand(List<Card> cards, boolean isColorOrder) {

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

			if (m10Index != -1) {

				if ((m10Index + 1 < cards.size())
						&& (cards.get(m10Index + 1).getOrderColorId() == 4
						|| cards.get(m10Index + 1).getOrderColorId() == 5
						|| cards.get(m10Index + 1).getOrderColorId() == 6)) {

					Card tmp = cards.get(m10Index);
					cards.remove(m10Index);
					cards.add(m10Index + 1, tmp);
				}

				if ((m10Index + 2 < cards.size())
						&& (cards.get(m10Index + 2).getOrderColorId() == 5
						|| cards.get(m10Index + 2).getOrderColorId() == 6)) {

					Card tmp = cards.get(m10Index + 1);
					cards.remove(m10Index + 1);
					cards.add(m10Index + 2, tmp);
				}

				if ((m10Index + 3 < cards.size())
						&& cards.get(m10Index + 3).getOrderColorId() == 6) {

					Card tmp = cards.get(m10Index + 2);
					cards.remove(m10Index + 2);
					cards.add(m10Index + 3, tmp);
				}
			}

			if (z10Index != -1) {

				if ((z10Index + 1 < cards.size())
						&& (cards.get(z10Index + 1).getOrderColorId() == 12
						|| cards.get(z10Index + 1).getOrderColorId() == 13
						|| cards.get(z10Index + 1).getOrderColorId() == 14)) {

					Card tmp = cards.get(z10Index);
					cards.remove(z10Index);
					cards.add(z10Index + 1, tmp);
				}

				if ((z10Index + 2 < cards.size())
						&& (cards.get(z10Index + 2).getOrderColorId() == 13
						|| cards.get(z10Index + 2).getOrderColorId() == 14)) {

					Card tmp = cards.get(z10Index + 1);
					cards.remove(z10Index + 1);
					cards.add(z10Index + 2, tmp);
				}

				if ((z10Index + 3 < cards.size())
						&& cards.get(z10Index + 3).getOrderColorId() == 14) {

					Card tmp = cards.get(z10Index + 2);
					cards.remove(z10Index + 2);
					cards.add(z10Index + 3, tmp);
				}
			}

			if (t10Index != -1) {

				if ((t10Index + 1 < cards.size())
						&& (cards.get(t10Index + 1).getOrderColorId() == 20
						|| cards.get(t10Index + 1).getOrderColorId() == 21
						|| cards.get(t10Index + 1).getOrderColorId() == 22)) {

					Card tmp = cards.get(t10Index);
					cards.remove(t10Index);
					cards.add(t10Index + 1, tmp);
				}

				if ((t10Index + 2 < cards.size()) 
						&&(cards.get(t10Index + 2).getOrderColorId() == 21
						|| cards.get(t10Index + 2).getOrderColorId() == 22)) {

					Card tmp = cards.get(t10Index + 1);
					cards.remove(t10Index + 1);
					cards.add(t10Index + 2, tmp);
				}

				System.out.println(t10Index + 3);
				
				if ((t10Index + 3 < cards.size()
						&& cards.get(t10Index + 3).getOrderColorId() == 22)) {

					Card tmp = cards.get(t10Index + 2);
					cards.remove(t10Index + 2);
					cards.add(t10Index + 3, tmp);
				}
			}
			
			if (p10Index != -1) {

				if ((p10Index + 1 < cards.size())
						&& (cards.get(p10Index + 1).getOrderColorId() == 28 || cards.get(p10Index + 1).getOrderColorId() == 29
						|| cards.get(p10Index + 1).getOrderColorId() == 30)) {

					Card tmp = cards.get(p10Index);
					cards.remove(p10Index);
					cards.add(p10Index + 1, tmp);
				}

				if ((p10Index + 2 < cards.size())
						&& (cards.get(p10Index + 2).getOrderColorId() == 29
						|| cards.get(p10Index + 2).getOrderColorId() == 30)) {

					Card tmp = cards.get(p10Index + 1);
					cards.remove(p10Index + 1);
					cards.add(p10Index + 2, tmp);
				}

				if ((p10Index + 3 < cards.size())
						&& cards.get(p10Index + 3).getOrderColorId() == 30) {

					Card tmp = cards.get(p10Index + 2);
					cards.remove(p10Index + 2);
					cards.add(p10Index + 3, tmp);
				}
			}
		}

		return cards;
	}

}
