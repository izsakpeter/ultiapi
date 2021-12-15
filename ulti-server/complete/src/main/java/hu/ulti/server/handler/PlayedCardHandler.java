package hu.ulti.server.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.ulti.server.Constants;
import hu.ulti.server.Helper;
import hu.ulti.server.controller.UltiController;
import hu.ulti.server.model.Call;
import hu.ulti.server.model.Card;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;
import hu.ulti.server.model.Request;

public class PlayedCardHandler {

	public static final Logger log = LoggerFactory.getLogger(UltiController.class);

	public static boolean isPlayedCardCorrect(Game game, Request request, List<Player> players) {
		boolean isSzintelen = Helper.isSzintelenByList(game.getPreviousCall());
		int playerIndex = getPlayerIndex(players, request.getId());

		if (game.getRound().getCard1Id() == -1) {

			if (request.getId() == game.getLastCallerId() && players.get(playerIndex).getHand().size() > 1
					&& isUltiAndAdu7(request.getCardid(), game.getPreviousCall()))
				return false;

			return true;
		} else if (game.getRound().getCard2Id() == -1) {

			if (isSzintelen)
				return playedPlayableCard(game.getRound().getCard1Id(), players.get(playerIndex).getHand(),
						request.getCardid());

			int card1 = Helper.changeCardOrder(game.getRound().getCard1Id());
			int card2 = Helper.changeCardOrder(request.getCardid());
			List<Card> hand = Helper.fixChangeCardOrderHand(players.get(playerIndex).getHand());
			boolean isCard1Adu = isCardAdu(game.getRound().getCard1Id(), game.getPreviousCall().get(0).getCallId());

			if (isCard1Adu)
				return playedPlayableCard(card1, hand, card2);

			List<Integer> correctCardSameColor = getCorrectCardSameColor(card1, hand);
			List<Integer> allCardSameColor = getAllCardSameColor(card1, hand);

			if (correctCardSameColor.size() > 0)
				return correctCardSameColor.contains(card2);

			if (allCardSameColor.size() > 0)
				return allCardSameColor.contains(card2);

			return playedPlayableAduOrAnyCard(game, hand, request.getId(), card2);

		} else if (game.getRound().getCard3Id() == -1) {

			if (isSzintelen) {

				int card1 = game.getRound().getCard1Id();
				int card2 = game.getRound().getCard2Id();
				int card1Color = Helper.getColorId(card1);
				int card2Color = Helper.getColorId(card2);

				if (card1Color == card2Color) {

					int higherId = card1 > card2 ? card1 : card2;
					return playedPlayableCard(higherId, players.get(playerIndex).getHand(), request.getCardid());

				} else {
					return (playedPlayableCard(game.getRound().getCard1Id(), players.get(playerIndex).getHand(),
							request.getCardid()));
				}
			}

			int card1 = Helper.changeCardOrder(game.getRound().getCard1Id());
			int card2 = Helper.changeCardOrder(game.getRound().getCard2Id());
			int card3 = Helper.changeCardOrder(request.getCardid());
			List<Card> hand = Helper.fixChangeCardOrderHand(players.get(playerIndex).getHand());
			int card1Color = Helper.getColorId(card1);
			int card2Color = Helper.getColorId(card2);
			boolean isCard1Adu = isCardAdu(game.getRound().getCard1Id(), game.getPreviousCall().get(0).getCallId());
			boolean isCard2Adu = isCardAdu(game.getRound().getCard2Id(), game.getPreviousCall().get(0).getCallId());

			if (isCard1Adu && isCard2Adu) {
				int higherId = card1 > card2 ? card1 : card2;
				return playedPlayableCard(higherId, hand, card3);
			}

			if (!isCard1Adu && !isCard2Adu) {
				int higherId = card1 > card2 ? card1 : card2;
				List<Integer> correctCardSameColor = getCorrectCardSameColor(higherId, hand);
				List<Integer> allCardSameColor = getAllCardSameColor(higherId, hand);

				if (card1Color != card2Color) {
					correctCardSameColor = getCorrectCardSameColor(card1, hand);
					allCardSameColor = getAllCardSameColor(card1, hand);
				}

				if (correctCardSameColor.size() > 0)
					return correctCardSameColor.contains(card3);

				if (allCardSameColor.size() > 0)
					return allCardSameColor.contains(card3);

				return playedPlayableAduOrAnyCard(game, hand, request.getId(), card3);
			}

			if (isCard1Adu && !isCard2Adu)
				return playedPlayableCard(card1, players.get(playerIndex).getHand(), card3);

			if (!isCard1Adu && isCard2Adu) {

				List<Integer> allCardSameColor = getAllCardSameColor(card1, players.get(playerIndex).getHand());

				if (allCardSameColor.size() > 0)
					return allCardSameColor.contains(card3);

				return playedPlayableAduOrAnyCard(game, hand, request.getId(), card3);
			}
		}

		return false;
	}

	private static boolean playedPlayableCard(int storedCardId, List<Card> hand, int newCardId) {

		List<Integer> correctCardSameColor = getCorrectCardSameColor(storedCardId, hand);
		List<Integer> allCardSameColor = getAllCardSameColor(storedCardId, hand);

		if (correctCardSameColor.size() > 0)
			return correctCardSameColor.contains(newCardId);

		if (allCardSameColor.size() > 0)
			return allCardSameColor.contains(newCardId);

		return true;
	}

	private static boolean playedPlayableAduOrAnyCard(Game game, List<Card> hand, int requestId, int requestCardId) {

		List<Integer> allAdu = getAdu(game.getPreviousCall().get(0).getCallId(), hand);

		if (allAdu.size() > 0) {
			if (hand.size() > 1 && requestId == game.getLastCallerId()
					&& isUltiAndAdu7(requestCardId, game.getPreviousCall()) && allAdu.size() > 1)
				return false;

			return allAdu.contains(requestCardId);
		}

		return true;
	}

	private static List<Integer> getCorrectCardSameColor(int storedCardId, List<Card> playerHand) {

		int colorId = Helper.getColorId(storedCardId);
		List<Integer> allCardsByColor = getCardListByColor(colorId);
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < allCardsByColor.size(); i++) {
			for (int j = 0; j < playerHand.size(); j++) {
				if (playerHand.get(j).getId() == allCardsByColor.get(i) && playerHand.get(j).getId() > storedCardId)
					list.add(allCardsByColor.get(i));
			}
		}

		return list;
	}

	private static List<Integer> getAllCardSameColor(int card1Id, List<Card> hand) {

		int colorId = Helper.getColorId(card1Id);
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getColorId() == colorId)
				list.add(hand.get(i).getId());
		}

		return list;
	}

	private static List<Integer> getAdu(int callId, List<Card> hand) {

		int aduId = Helper.getAduByCall(callId);
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getColorId() == aduId)
				list.add(hand.get(i).getId());
		}

		return list;
	}

	private static List<Integer> getCardListByColor(int colorId) {

		List<Card> allCards = Card.getAllCards();
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < allCards.size(); i++) {
			if (allCards.get(i).getColorId() == colorId)
				list.add(allCards.get(i).getId());
		}

		return list;
	}

	private static int getPlayerIndex(List<Player> players, int id) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == id)
				return i;
		}

		return 0;
	}

	private static boolean isCardAdu(int cardId, int callId) {
		int cardColor = Helper.getColorId(cardId);
		return Helper.getAduByCall(callId) == cardColor;
	}

	private static boolean isUltiAndAdu7(int cardid, List<Call> previousCall) {

		int colorId = Helper.getColorId(cardid);
		int ultiId = getUltiId(colorId);

		for (int i = 0; i < previousCall.size(); i++) {
			if (previousCall.get(i).getCallId() == ultiId && Constants.CARD_7LIST.contains(cardid))
				return true;
		}

		return false;
	}

	private static int getUltiId(int colorId) {
		if (colorId == Constants.MAKK_ID)
			return 4;
		else if (colorId == Constants.ZOLD_ID)
			return 16;
		else if (colorId == Constants.TOK_ID)
			return 28;
		else if (colorId == Constants.PIROS_ID)
			return 40;

		return 0;
	}
}
