package hu.ulti.server;

import hu.ulti.server.model.Call;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;
import hu.ulti.server.model.Strike;

public class StrikeHandler {

	private int roundCounter;
	private Game game;
	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player player3 = new Player();

	public StrikeHandler(int roundCounter, Game game, Player player1, Player player2, Player player3) {
		this.roundCounter = roundCounter;
		this.game = game;
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;

		int card1 = game.getRound().getCard1Id();
		int card2 = game.getRound().getCard2Id();
		int card3 = game.getRound().getCard3Id();
		int card1ColorId = getColor(card1);
		int card2ColorId = getColor(card2);
		int card3ColorId = getColor(card3);
		boolean isBetli = Helper.isBetli(game);
		boolean isSzintelenDuri = Helper.isSzintelenDuri(game);
		boolean isSzintelen = isBetli || isSzintelenDuri;

		if (isSzintelen) {
			if (card1ColorId != card2ColorId && card1ColorId != card3ColorId) {
				strikeHandler(game.getRound().getCard1PlayerId());

			} else if (card1ColorId == card2ColorId && card1ColorId == card3ColorId) {
				if (card1 > card2 && card1 > card3)
					strikeHandler(game.getRound().getCard1PlayerId());
				else if (card2 > card1 && card2 > card3)
					strikeHandler(game.getRound().getCard2PlayerId());
				else if (card3 > card1 && card3 > card2)
					strikeHandler(game.getRound().getCard3PlayerId());

			} else if (card1ColorId == card2ColorId && card1ColorId != card3ColorId) {
				if (card1 > card2)
					strikeHandler(game.getRound().getCard1PlayerId());
				else if (card2 > card1)
					strikeHandler(game.getRound().getCard2PlayerId());

			} else if (card1ColorId == card3ColorId && card1ColorId != card2ColorId) {
				if (card1 > card3)
					strikeHandler(game.getRound().getCard1PlayerId());
				else if (card3 > card1)
					strikeHandler(game.getRound().getCard3PlayerId());
			}
		} else {

			int ADU = getAdu(game.getPreviousCall().get(0));

			card1 = fixCardOrder(card1);
			card2 = fixCardOrder(card2);
			card3 = fixCardOrder(card3);

			if (ADU == card1ColorId && ADU == card2ColorId && ADU == card3ColorId) {
				if (card1 > card2 && card1 > card3)
					strikeHandler(game.getRound().getCard1PlayerId());
				else if (card2 > card1 && card2 > card3)
					strikeHandler(game.getRound().getCard2PlayerId());
				else if (card3 > card1 && card3 > card2)
					strikeHandler(game.getRound().getCard3PlayerId());

			} else if (ADU == card1ColorId && ADU != card2ColorId && ADU != card3ColorId)
				strikeHandler(game.getRound().getCard1PlayerId());

			else if (ADU != card1ColorId && ADU == card2ColorId && ADU != card3ColorId)
				strikeHandler(game.getRound().getCard2PlayerId());

			else if (ADU != card1ColorId && ADU != card2ColorId && ADU == card3ColorId)
				strikeHandler(game.getRound().getCard3PlayerId());

			else if (ADU == card1ColorId && ADU == card2ColorId && ADU != card3ColorId) {
				if (card1 > card2)
					strikeHandler(game.getRound().getCard1PlayerId());
				else if (card2 > card1)
					strikeHandler(game.getRound().getCard2PlayerId());

			} else if (ADU == card1ColorId && ADU != card2ColorId && ADU == card3ColorId) {
				if (card1 > card3)
					strikeHandler(game.getRound().getCard1PlayerId());
				else if (card3 > card1)
					strikeHandler(game.getRound().getCard3PlayerId());

			} else if (ADU != card1ColorId && ADU == card2ColorId && ADU == card3ColorId) {
				if (card2 > card3)
					strikeHandler(game.getRound().getCard2PlayerId());
				else if (card3 > card2)
					strikeHandler(game.getRound().getCard3PlayerId());

			} else if (ADU != card1ColorId && ADU != card2ColorId && ADU != card3ColorId) {
				if (card1ColorId != card2ColorId && card1ColorId != card3ColorId)
					strikeHandler(game.getRound().getCard1PlayerId());

				else if (card1ColorId == card2ColorId && card1ColorId == card3ColorId) {
					if (card1 > card2 && card1 > card3)
						strikeHandler(game.getRound().getCard1PlayerId());
					else if (card2 > card1 && card2 > card3)
						strikeHandler(game.getRound().getCard2PlayerId());
					else if (card3 > card1 && card3 > card2)
						strikeHandler(game.getRound().getCard3PlayerId());

				} else if (card1ColorId == card2ColorId && card1ColorId != card3ColorId) {
					if (card1 > card2)
						strikeHandler(game.getRound().getCard1PlayerId());
					else if (card2 > card1)
						strikeHandler(game.getRound().getCard2PlayerId());

				} else if (card1ColorId == card3ColorId && card1ColorId != card2ColorId) {
					if (card1 > card3)
						strikeHandler(game.getRound().getCard1PlayerId());
					else if (card3 > card1)
						strikeHandler(game.getRound().getCard3PlayerId());
				}
			}
		}

		game.getRound().clearStrike();
	}

	public static int getColor(int id) {
		if (id <= 7)
			return Call.MAKK_COLOR_ID;
		else if (id > 7 && id <= 15)
			return Call.ZOLD_COLOR_ID;
		else if (id > 15 && id <= 23)
			return Call.TOK_COLOR_ID;
		else
			return Call.PIROS_COLOR_ID;
	}

	private void strikeHandler(int id) {
		if (id == player1.getId()) {
			player1.addStrike(new Strike(roundCounter, game.getRound().getCard1Id(), game.getRound().getCard2Id(),
					game.getRound().getCard3Id(), game.getRound().getCard1PlayerId(), game.getRound().getCard2PlayerId(),
					game.getRound().getCard3PlayerId()));
			game.setActivePlayer(player1.getId());
		} else if (id == player2.getId()) {
			player2.addStrike(new Strike(roundCounter, game.getRound().getCard1Id(), game.getRound().getCard2Id(),
					game.getRound().getCard3Id(), game.getRound().getCard1PlayerId(), game.getRound().getCard2PlayerId(),
					game.getRound().getCard3PlayerId()));
			game.setActivePlayer(player2.getId());
		} else if (id == player3.getId()) {
			player3.addStrike(new Strike(roundCounter, game.getRound().getCard1Id(), game.getRound().getCard2Id(),
					game.getRound().getCard3Id(), game.getRound().getCard1PlayerId(), game.getRound().getCard2PlayerId(),
					game.getRound().getCard3PlayerId()));
			game.setActivePlayer(player3.getId());
		}
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

	private int fixCardOrder(int cardId) {

		switch (cardId) {
		case 3:
			return 6;
		case 4:
			return 3;
		case 5:
			return 4;
		case 6:
			return 5;
		case 11:
			return 14;
		case 12:
			return 11;
		case 13:
			return 12;
		case 14:
			return 13;
		case 19:
			return 22;
		case 20:
			return 19;
		case 21:
			return 20;
		case 22:
			return 21;
		case 27:
			return 30;
		case 28:
			return 27;
		case 29:
			return 28;
		case 30:
			return 29;
		default:
			break;
		}

		return cardId;
	}

	public Game getGame() {
		return game;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Player getPlayer3() {
		return player3;
	}
}
