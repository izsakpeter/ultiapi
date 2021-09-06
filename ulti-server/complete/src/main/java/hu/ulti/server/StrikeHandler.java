package hu.ulti.server;

import hu.ulti.server.model.Call;
import hu.ulti.server.model.Game;

public class StrikeHandler {

	public static Game strikeHandler(Game game) {

		int card1 = game.getRound().getCard1Id();
		int card2 = game.getRound().getCard2Id();
		int card3 = game.getRound().getCard3Id();
		int card1ColorId = getColor(card1);
		int card2ColorId = getColor(card2);
		int card3ColorId = getColor(card3);

		boolean isSzintelen = true;

		if (isSzintelen) {

			if (card1ColorId != card2ColorId && card1ColorId != card3ColorId)
				game.setLastStrikeId(1);

			if (card1ColorId == card2ColorId && card1ColorId == card3ColorId) {

				if (card1 > card2 && card1 > card3)
					game.setLastStrikeId(1);

				if (card2 > card1 && card2 > card3)
					game.setLastStrikeId(2);

				if (card3 > card1 && card3 > card2)
					game.setLastStrikeId(3);
			}

			if (card1ColorId == card2ColorId && card1ColorId != card3ColorId) {

				if (card1 > card2)
					game.setLastStrikeId(1);

				if (card2 > card1)
					game.setLastStrikeId(2);
			}

			if (card1ColorId == card3ColorId && card1ColorId != card2ColorId) {
				if (card1 > card3)
					game.setLastStrikeId(1);

				if (card3 > card1)
					game.setLastStrikeId(3);
			}
		} else {

			final int ADU = getAdu(game.getPreviousCall().get(0));

			if (ADU == card1ColorId && ADU != card2ColorId && ADU != card3ColorId)
				game.setLastStrikeId(1);

			if (ADU != card1ColorId && ADU == card2ColorId && ADU != card3ColorId)
				game.setLastStrikeId(2);

			if (ADU != card1ColorId && ADU != card2ColorId && ADU == card3ColorId)
				game.setLastStrikeId(3);

			if (ADU == card1ColorId && ADU == card2ColorId && ADU == card3ColorId) {

				if (card1 > card2 && card1 > card3)
					game.setLastStrikeId(1);

				if (card2 > card1 && card2 > card3)
					game.setLastStrikeId(2);

				if (card3 > card1 && card3 > card2)
					game.setLastStrikeId(2);
			}

			if (ADU != card1ColorId && ADU != card2ColorId && ADU != card3ColorId) {

				if (card1ColorId != card2ColorId && card1ColorId != card3ColorId)
					game.setLastStrikeId(1);

				if (card1ColorId == card2ColorId && card1ColorId == card3ColorId) {

					if (card1 > card2 && card1 > card3)
						game.setLastStrikeId(1);

					if (card2 > card1 && card2 > card3)
						game.setLastStrikeId(2);

					if (card3 > card1 && card3 > card2)
						game.setLastStrikeId(3);
				}

				if (card1ColorId == card2ColorId && card1ColorId != card3ColorId) {

					if (card1 > card2)
						game.setLastStrikeId(1);

					if (card2 > card1)
						game.setLastStrikeId(2);
				}

				if (card1ColorId == card3ColorId && card1ColorId != card2ColorId) {
					if (card1 > card3)
						game.setLastStrikeId(1);

					if (card3 > card1)
						game.setLastStrikeId(3);
				}
			}
		}

		return game;
	}

	private static int getColor(int id) {
		if (id <= 7)
			return Call.MAKK_COLOR_ID;
		else if (id > 7 && id <= 15)
			return Call.ZOLD_COLOR_ID;
		else if (id > 15 && id <= 23)
			return Call.TOK_COLOR_ID;
		else
			return Call.PIROS_COLOR_ID;
	}

	private static int getAdu(int id) {

		switch (id) {
		case 0:
			return Call.MAKK_COLOR_ID;
		case 10:
			return Call.ZOLD_COLOR_ID;
		case 20:
			return Call.TOK_COLOR_ID;
		case 30:
			return Call.PIROS_COLOR_ID;
		}
		return 0;
	}
}
