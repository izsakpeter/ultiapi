package hu.ulti.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import hu.ulti.server.model.Call;
import hu.ulti.server.model.CallWithValue;
import hu.ulti.server.model.Card;
import hu.ulti.server.model.Hand;
import hu.ulti.server.model.Player;
import hu.ulti.server.model.Score;
import hu.ulti.server.model.UuidWithCardId;

public class Helper {

	private static List<Integer> betliIds = Arrays.asList(5, 9, 17, 21, 29, 33, 41, 45);
	private static List<Integer> duriIds = Arrays.asList(7, 11, 19, 23, 31, 35, 43, 47);

	public static List<List<Card>> getHands() {

		List<Card> cards = Card.getAllCards();
		List<List<Card>> hands = Arrays.asList(new ArrayList<Card>(), new ArrayList<Card>(), new ArrayList<Card>(),
				new ArrayList<Card>());

		for (int k = 0; k < 3; k++) {
			if (k == 1) {
				for (int i = 0; i < 2; i++) {
					Random rand = new Random();
					int random = rand.nextInt(cards.size());
					hands.get(3).add(cards.get(random));
					cards.remove(random);
				}
			} else {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 5; j++) {
						Random rand = new Random();
						int random = rand.nextInt(cards.size());
						hands.get(i).add(cards.get(random));
						cards.remove(random);
					}
				}
			}
		}

		return hands;
	}

	public static List<Card> orderHand(List<Card> hand) {
		hand.sort(Comparator.comparing(Card::getId));
		return hand;
	}

	public static int setFirstDealer(int players) {
		if (players == 4)
			return 3;

		return 2;
	}

	public static int dealerHandler(int number, int players) {

		if (number == 0)
			return 1;
		else if (number == 1)
			return 2;
		else if (number == 2)
			return 3;

		if (players == 4 && number == 3)
			return 0;
		else if (number == 3)
			return 0;

		return 1;
	}

	public static boolean isTeritett(List<Call> previousCall) {
		List<Integer> teritettIds = Arrays.asList(9, 10, 11, 21, 22, 23, 33, 34, 35, 45, 46, 47);

		for (int i = 0; i < previousCall.size(); i++) {
			for (Integer teritettId : teritettIds) {
				if (previousCall.get(i).getCallId() == teritettId)
					return true;
			}
		}

		return false;
	}

	public static List<Player> getPlayerList(int players) {

		if (players > 4 || players < 3)
			players = 3;

		List<Player> playersList = new ArrayList<Player>();

		for (int i = 0; i < players; i++) {
			playersList.add(i, new Player());
		}

		return playersList;
	}

	public static Hand fillHandWithUncoveredCards(Player player) {
		Hand hand = new Hand();
		hand.setId(player.getId());
		List<UuidWithCardId> list = new ArrayList<UuidWithCardId>();

		for (int i = 0; i < player.getHand().size(); i++) {
			list.add(new UuidWithCardId(getUUid().toString(), player.getHand().get(i).getId()));
		}

		hand.setList(list);

		return hand;
	}

	public static Hand fillHandWithCoveredCards(Player player) {
		Hand hand = new Hand();
		hand.setId(player.getId());
		List<UuidWithCardId> list = new ArrayList<UuidWithCardId>();

		for (int i = 0; i < player.getHand().size(); i++) {
			list.add(new UuidWithCardId(getUUid().toString(), -1));
		}
		hand.setList(list);

		return hand;
	}

	public static Hand setEmptyHand(Player player) {
		Hand hand = new Hand();
		hand.setId(player.getId());
		List<UuidWithCardId> list = new ArrayList<UuidWithCardId>();

		for (int i = 0; i < 10; i++) {
			list.add(new UuidWithCardId(getUUid().toString(), -2));
		}
		hand.setList(list);

		return hand;
	}

	public static List<Card> fillTalonWithMinusOne() {
		List<Card> talon = new ArrayList<Card>();
		talon.add(new Card(-1, -1));
		talon.add(new Card(-1, -1));

		return talon;
	}

	public static List<Card> addTalon(Player player, List<Card> talon) {

		player.getHand().add(talon.get(0));
		player.getHand().add(talon.get(1));

		Helper.orderHand(player.getHand());

		return player.getHand();
	}

	public static List<Card> getTalonById(List<Integer> talonid) {

		List<Card> talon = new ArrayList<Card>();
		List<Card> allCards = Card.getAllCards();

		for (Card card : allCards) {
			if (card.getId() == talonid.get(0) || card.getId() == talonid.get(1))
				talon.add(card);
		}

		return talon;
	}

	public static List<Card> removeTalon(Player player, List<Card> talon) {

		for (Card card : player.getHand()) {
			if (card.getId() == talon.get(0).getId()) {
				player.getHand().remove(card);
				break;
			}
		}

		for (Card card : player.getHand()) {
			if (card.getId() == talon.get(1).getId()) {
				player.getHand().remove(card);
				break;
			}
		}

		Helper.orderHand(player.getHand());

		return player.getHand();
	}

	public static List<Card> removeCardbyId(Player player, int cardId) {

		for (Card card : player.getHand()) {
			if (card.getId() == cardId) {
				player.getHand().remove(card);
				break;
			}
		}

		Helper.orderHand(player.getHand());

		return player.getHand();
	}

	public static String getUUid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static List<Score> getDefaultScoreList(List<Player> players) {
		List<Score> scores = new ArrayList<Score>();

		for (int i = 0; i < players.size(); i++) {
			scores.add(new Score(players.get(i).getId()));
		}

		return scores;
	}

	public static int getCallValue(int callId) {

		List<CallWithValue> allCalls = CallWithValue.getAllCalls();

		for (CallWithValue callWithValue : allCalls) {
			if (callWithValue.getId() == callId)
				return callWithValue.getValue();
		}

		return 0;
	}

	public static List<Integer> getPlayers(List<Player> players) {
		List<Integer> playersList = new ArrayList<Integer>();

		for (Player player : players) {
			if (player.isPlaying()) {
				playersList.add(player.getId());
			}
		}

		return playersList;
	}

	public static boolean isSzintelenByList(List<Call> callList) {
		return (Helper.isBetlibyList(callList) || Helper.isSzintelenDuribyList(callList));
	}

	public static boolean isBetlibyList(List<Call> callList) {

		for (Integer id : betliIds) {
			for (int i = 0; i < callList.size(); i++) {
				if (id == callList.get(i).getCallId())
					return true;
			}
		}

		return false;
	}

	public static boolean isSzintelenDuribyList(List<Call> callList) {

		for (Integer id : duriIds) {
			for (int i = 0; i < callList.size(); i++) {
				if (id == callList.get(i).getCallId())
					return true;
			}
		}

		return false;
	}

	public static boolean isSzintelenbyId(int callId) {
		return (Helper.isBetliById(callId) || Helper.isSzintelenDuriById(callId));
	}

	private static boolean isBetliById(int callId) {

		for (Integer id : betliIds) {
			if (id == callId)
				return true;
		}

		return false;
	}

	private static boolean isSzintelenDuriById(int callId) {

		for (Integer id : duriIds) {
			if (id == callId)
				return true;
		}

		return false;
	}

	public static int getColorId(int cardId) {
		if (cardId <= 7)
			return Constants.MAKK_ID;
		else if (cardId <= 15)
			return Constants.ZOLD_ID;
		else if (cardId <= 23)
			return Constants.TOK_ID;
		else
			return Constants.PIROS_ID;
	}

	public static int changeCardOrder(int cardId) {

		List<Integer> list10s = Arrays.asList(3, 11, 19, 27);
		List<Integer> others = Arrays.asList(4, 5, 6, 12, 13, 14, 20, 21, 22, 28, 29, 30);

		if (list10s.contains(cardId))
			return cardId + 3;

		if (others.contains(cardId))
			return cardId - 1;

		return cardId;
	}
	
	public static List<Card> fixChangeCardOrderHand(List<Card> hand){
		
		List<Card> list = new ArrayList<Card>();
		
		for (int i = 0; i < hand.size(); i++) {
			list.add(new Card(changeCardOrder(hand.get(i).getId()), hand.get(i).getColorId()));
		}
		
		return list;
	}

	public static int getAduByCall(int id) {

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
	
	public static boolean isInList(List<Integer> list, List<Call> finalCall) {

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < finalCall.size(); j++) {
				if (finalCall.get(j).getCallId() == list.get(i))
					return true;
			}
		}

		return false;
	}
}
