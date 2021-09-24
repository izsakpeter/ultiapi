package hu.ulti.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import hu.ulti.server.model.Call;
import hu.ulti.server.model.Card;
import hu.ulti.server.model.Hand;
import hu.ulti.server.model.Player;
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

	public static int dealerHandler(int number) {

		if (number == 1)
			return 2;
		else if (number == 2)
			return 3;
		else if (number == 3)
			return 1;

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

	public static List<Player> getPlayerList() {
		List<Player> players = new ArrayList<Player>();
		Player player1 = new Player();
		players.add(0, player1);
		Player player2 = new Player();
		players.add(1, player2);
		Player player3 = new Player();
		players.add(2, player3);
		return players;
	}

	public static Hand fillHandWithMinusOne(Player player) {
		Hand hand = new Hand();
		hand.setId(player.getId());
		List<UuidWithCardId> list = new ArrayList<UuidWithCardId>();

		for (int i = 0; i < player.getHand().size(); i++) {
			UUID uuid = UUID.randomUUID();
			list.add(new UuidWithCardId(uuid.toString(), -1));
		}
		hand.setList(list);

		return hand;
	}

	public static Hand setHandWithCards(Player player) {
		Hand hand = new Hand();
		hand.setId(player.getId());
		List<UuidWithCardId> list = new ArrayList<UuidWithCardId>();

		for (int i = 0; i < player.getHand().size(); i++) {
			UUID uuid = UUID.randomUUID();
			list.add(new UuidWithCardId(uuid.toString(), player.getHand().get(i).getId()));
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
	
	public static boolean isBetli(List<Call> callList) {

		for (Integer id : betliIds) {
			for (int i = 0; i < callList.size(); i++) {
				if (id == callList.get(i).getCallId())
					return true;
			}
		}

		return false;
	}

	public static boolean isSzintelenDuri(List<Call> callList) {

		for (Integer id : duriIds) {
			for (int i = 0; i < callList.size(); i++) {
				if (id == callList.get(i).getCallId())
					return true;
			}
		}

		return false;
	}
}
