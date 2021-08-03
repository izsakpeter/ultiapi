package hu.ulti.server;

import hu.ulti.server.model.Game;

public class StrikeHandler {

	public static Game strikeHandler(Game game) {

		if (game.getRound().getCard3Id() != 0) {

			int card1 = game.getRound().getCard1Id();
			int card2 = game.getRound().getCard2Id();
			int card3 = game.getRound().getCard3Id();
			int card1ColorId = getColor(card1);
			int card2ColorId = getColor(card2);
			int card3ColorId = getColor(card3);
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
						game.setLastStrikeId(2);
				}

				if (card1ColorId == card2ColorId && card1ColorId != card3ColorId) {

					if (card1 > card2 && card1 > card3)
						game.setLastStrikeId(1);

					if (card2 > card1 && card2 > card3)
						game.setLastStrikeId(2);
				}

				if (card1ColorId == card3ColorId && card1ColorId != card2ColorId) {

					if (card1 > card2 && card1 > card3)
						game.setLastStrikeId(1);

					if (card3 > card1 && card3 > card2)
						game.setLastStrikeId(2);
				}
			}

			game.setLastStrike(game.getRound());

		}

		return game;
	}

	private static int getColor(int id) {

		if (id <= 7)
			return 0;
		else if (id > 7 && id <= 15)
			return 1;
		else if (id > 15 && id <= 23)
			return 2;
		else
			return 3;
	}

	private static int getAdu(int id) {

		switch (id) {
			case 0:
				return 0;
			case 10:
				return 1;
			case 20:
				return 2;
			case 30:
				return 3;
		}

		return 0;
	}
}
