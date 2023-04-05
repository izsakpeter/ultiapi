package hu.ulti.server.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import hu.ulti.server.model.Card;
import hu.ulti.server.model.Hand;
import hu.ulti.server.model.Player;
import hu.ulti.server.model.Score;
import hu.ulti.server.model.UuidWithCardId;

public class Helper {

	public static List<Player> getEmptyPlayers(int players) {

		if (players > 4 || players < 3)
			players = 3;

		List<Player> playersList = new ArrayList<Player>();

		for (int i = 0; i < players; i++) {
			playersList.add(i, new Player());
		}

		return playersList;
	}

	public static List<Player> setPlayers(List<Player> players) {
		List<Player> result = new ArrayList<Player>();

		for (Player player : players) {
			if (player.getPlayerId() != -1)
				result.add(player);
		}

		return result;
	}

	public static Player getPlayerById(List<Player> players, int id) {

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getPlayerId() == id)
				return players.get(i);
		}

		return null;
	}

	public static int setFirstDealer(int players) {
		if (players == 4)
			return 3;

		return 2;
	}

	public static List<List<Card>> getPacks() {

		List<Card> cards = Card.getAllCards();
		List<List<Card>> packs = Arrays.asList(new ArrayList<Card>(), new ArrayList<Card>(), new ArrayList<Card>(),
				new ArrayList<Card>());

		for (int k = 0; k < 3; k++) {
			if (k == 1) {
				for (int i = 0; i < 2; i++) {
					Random rand = new Random();
					int random = rand.nextInt(cards.size());
					packs.get(3).add(cards.get(random));
					cards.remove(random);
				}
			} else {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 5; j++) {
						Random rand = new Random();
						int random = rand.nextInt(cards.size());
						packs.get(i).add(cards.get(random));
						cards.remove(random);
					}
				}
			}
		}

		return packs;
	}

	public static Hand fillHandWithCoveredCards(Player player) {
		Hand hand = new Hand();
		hand.setId(player.getPlayerId());
		List<UuidWithCardId> list = new ArrayList<UuidWithCardId>();

		for (int i = 0; i < player.getHand().size(); i++) {
			list.add(new UuidWithCardId(getUUid().toString(), -1));
		}
		hand.setList(list);

		return hand;
	}

	public static Hand setEmptyHand(Player player) {
		Hand hand = new Hand();
		hand.setId(player.getPlayerId());
		List<UuidWithCardId> list = new ArrayList<UuidWithCardId>();

		for (int i = 0; i < 10; i++) {
			list.add(new UuidWithCardId(getUUid().toString(), -2));
		}
		hand.setList(list);

		return hand;
	}

	private static String getUUid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static List<Card> getCoveredTalon() {
		List<Card> talon = new ArrayList<Card>();
		talon.add(new Card(-1, -1));
		talon.add(new Card(-1, -1));

		return talon;
	}

	public static List<Score> getDefaultScoreList(List<Player> players) {
		List<Score> scores = new ArrayList<Score>();

		for (int i = 0; i < players.size(); i++) {
			scores.add(new Score(players.get(i).getPlayerId()));
		}

		return scores;
	}

}
