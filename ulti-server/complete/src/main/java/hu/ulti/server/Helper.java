package hu.ulti.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import hu.ulti.server.model.Card;
import hu.ulti.server.model.Game;

public class Helper {

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

	public static boolean isBetli(Game game) {
		List<Integer> betliIds = Arrays.asList(4, 9, 16, 21, 28, 33, 40, 45);

		for (Integer id : betliIds) {
			for (Integer call : game.getPreviousCall()) {
				if (id == call)
					return true;
			}
		}

		return false;
	}

	public static boolean isSzintelenDuri(Game game) {
		List<Integer> duriIds = Arrays.asList(7, 11, 19, 23, 31, 35, 43, 47);

		for (Integer id : duriIds) {
			for (Integer call : game.getPreviousCall()) {
				if (id == call)
					return true;
			}
		}

		return false;
	}

	public static boolean isTeritett(List<Integer> previousCall) {
		List<Integer> teritettIds = Arrays.asList(9, 10, 11, 21, 22, 23, 33, 34, 35, 45, 46, 47);

		for (Integer call : previousCall) {
			for (Integer teritettId : teritettIds) {
				if (call == teritettId)
					return true;
			}
		}
		return false;
	}
}
