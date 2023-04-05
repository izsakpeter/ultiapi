package hu.ulti.server.handlerOld;

import java.util.ArrayList;
import java.util.List;

import hu.ulti.server.HelperOld;
import hu.ulti.server.modelOld.Game;
import hu.ulti.server.modelOld.Player;
import hu.ulti.server.modelOld.Strike;

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
		int card1ColorId = HelperOld.getColorId(card1);
		int card2ColorId = HelperOld.getColorId(card2);
		int card3ColorId = HelperOld.getColorId(card3);
		boolean isSzintelen = HelperOld.isSzintelenByList(game.getPreviousCall());

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
			int ADU = HelperOld.getAduByCall(game.getPreviousCall().get(0).getCallId());

			card1 = HelperOld.changeCardOrder(card1);
			card2 = HelperOld.changeCardOrder(card2);
			card3 = HelperOld.changeCardOrder(card3);

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
