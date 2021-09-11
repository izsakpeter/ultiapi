package hu.ulti.server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import hu.ulti.server.model.Card;
import hu.ulti.server.model.Game;

public class Helper {

	public static List<List<Card>> getHands(int dealer) {

		List<Card> cards = Card.getAllCards();
		List<Card> hand1 = new ArrayList<Card>();
		List<Card> hand2 = new ArrayList<Card>();
		List<Card> hand3 = new ArrayList<Card>();
		List<Card> talon = new ArrayList<Card>();

		for (int i = 0; i < 5; i++) {

			Random rand = new Random();
			int random = rand.nextInt(cards.size());

			hand1.add(cards.get(random));
			cards.remove(random);
		}

		for (int i = 0; i < 5; i++) {

			Random rand = new Random();
			int random = rand.nextInt(cards.size());

			hand2.add(cards.get(random));
			cards.remove(random);
		}

		for (int i = 0; i < 5; i++) {

			Random rand = new Random();
			int random = rand.nextInt(cards.size());

			hand3.add(cards.get(random));
			cards.remove(random);
		}

		switch (dealer) {
		case 1:
			for (int i = 0; i < 2; i++) {

				Random rand = new Random();
				int random = rand.nextInt(cards.size());
				talon.add(cards.get(random));
				cards.remove(random);
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) {

				Random rand = new Random();
				int random = rand.nextInt(cards.size());
				talon.add(cards.get(random));
				cards.remove(random);
			}
			break;
		case 3:
			for (int i = 0; i < 2; i++) {

				Random rand = new Random();
				int random = rand.nextInt(cards.size());
				talon.add(cards.get(random));
				cards.remove(random);
			}
			break;
		}

		for (int i = 0; i < 5; i++) {

			Random rand = new Random();
			int random = rand.nextInt(cards.size());

			hand1.add(cards.get(random));
			cards.remove(random);
		}

		for (int i = 0; i < 5; i++) {

			Random rand = new Random();
			int random = rand.nextInt(cards.size());

			hand2.add(cards.get(random));
			cards.remove(random);
		}

		for (int i = 0; i < 5; i++) {

			Random rand = new Random();
			int random = rand.nextInt(cards.size());

			hand3.add(cards.get(random));
			cards.remove(random);
		}

		List<List<Card>> hands = new ArrayList<List<Card>>();
		hands.add(0, hand1);
		hands.add(1, hand2);
		hands.add(2, hand3);
		hands.add(3, talon);
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
		List<Integer> betliIds = new ArrayList<Integer>();
		betliIds.add(3);
		betliIds.add(7);
		betliIds.add(13);
		betliIds.add(17);
		betliIds.add(23);
		betliIds.add(27);
		betliIds.add(33);
		betliIds.add(37);

		for (Integer id : betliIds) {
			for (Integer call : game.getPreviousCall()) {
				if (id == call)
					return true;
			}
		}

		return false;
	}

	public static boolean isSzintelenDuri(Game game) {
		List<Integer> duriIds = new ArrayList<Integer>();
		duriIds.add(5);
		duriIds.add(9);
		duriIds.add(15);
		duriIds.add(19);
		duriIds.add(25);
		duriIds.add(29);
		duriIds.add(35);
		duriIds.add(39);

		for (Integer id : duriIds) {
			for (Integer call : game.getPreviousCall()) {
				if (id == call)
					return true;
			}
		}

		return false;
	}
	
	
}
