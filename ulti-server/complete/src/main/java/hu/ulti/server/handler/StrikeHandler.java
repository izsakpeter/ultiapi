package hu.ulti.server.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hu.ulti.server.Constants;
import hu.ulti.server.Helper;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;
import hu.ulti.server.model.Strike;

public class StrikeHandler {

	private int roundCounter;
	private Game game;
	private List<Player> players = new ArrayList<Player>();

	public StrikeHandler(int roundCounter, Game game, List<Player> players) {
		this.roundCounter = roundCounter;
		this.game = game;
		this.players = players;

		int card1 = game.getRound().getCard1Id();
		int card2 = game.getRound().getCard2Id();
		int card3 = game.getRound().getCard3Id();
		int card1ColorId = getColor(card1);
		int card2ColorId = getColor(card2);
		int card3ColorId = getColor(card3);
		boolean isSzintelen = Helper.isSzintelenByList(game.getPreviousCall());

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
			int ADU = getAdu(game.getPreviousCall().get(0).getCallId());

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

			} else if (ADU == card1ColorId && ADU != card2ColorId && ADU != card3ColorId) {
				strikeHandler(game.getRound().getCard1PlayerId());
			} else if (ADU != card1ColorId && ADU == card2ColorId && ADU != card3ColorId) {
				strikeHandler(game.getRound().getCard2PlayerId());
			} else if (ADU != card1ColorId && ADU != card2ColorId && ADU == card3ColorId) {
				strikeHandler(game.getRound().getCard3PlayerId());
			} else if (ADU == card1ColorId && ADU == card2ColorId && ADU != card3ColorId) {
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
	}

	public static int getColor(int id) {
		if (id <= 7)
			return Constants.MAKK_ID;
		else if (id <= 15)
			return Constants.ZOLD_ID;
		else if (id <= 23)
			return Constants.TOK_ID;
		else
			return Constants.PIROS_ID;
	}

	private void strikeHandler(int id) {
		for (int i = 0; i < players.size(); i++) {
			if (id == players.get(i).getId()) {
				players.get(i)
						.addStrike(new Strike(roundCounter, game.getRound().getCard1Id(), game.getRound().getCard2Id(),
								game.getRound().getCard3Id(), game.getRound().getCard1PlayerId(),
								game.getRound().getCard2PlayerId(), game.getRound().getCard3PlayerId()));
				game.setActivePlayer(players.get(i).getId());
			}
		}
	}

	public static int getAdu(int id) {

		if (id < 12)
			return Constants.MAKK_ID;
		else if (id < 24)
			return Constants.ZOLD_ID;
		else if (id < 36)
			return Constants.TOK_ID;
		else if (id < 48)
			return Constants.PIROS_ID;

		return 0;
	}

	private int fixCardOrder(int cardId) {

		List<Integer> list10s = Arrays.asList(3, 11, 19, 27);
		List<Integer> others = Arrays.asList(4, 5, 6, 12, 13, 14, 20, 21, 22, 28, 29, 30);

		if (list10s.contains(cardId))
			return cardId + 3;

		if (others.contains(cardId))
			return cardId - 1;

		return cardId;
	}

	public Game getGame() {
		return game;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
