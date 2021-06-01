package hu.ulti.server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import hu.ulti.server.model.Card;

public class Helper {
	
	public static List<List<Card>> getHands(int dealer) {

		List<Card> cards = Card.getAllCards();
		List<Card> hand1 = new ArrayList<Card>();
		List<Card> hand2 = new ArrayList<Card>();
		List<Card> hand3 = new ArrayList<Card>();

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
	
					cards.get(random).setTalon(true);
					
					hand2.add(cards.get(random));
					cards.remove(random);
				}
				break;
			case 2:
				for (int i = 0; i < 2; i++) {
	
					Random rand = new Random();
					int random = rand.nextInt(cards.size());
					
					cards.get(random).setTalon(true);
	
					hand3.add(cards.get(random));
					cards.remove(random);
				}
				break;
			case 3:
				for (int i = 0; i < 2; i++) {
	
					Random rand = new Random();
					int random = rand.nextInt(cards.size());
					
					cards.get(random).setTalon(true);
	
					hand1.add(cards.get(random));
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
		return hands;
	}
	
	public static List<Card> orderHand(List<Card> hand, int orderId) {
		if (orderId == 0)
			hand.sort(Comparator.comparing(Card::isTalon).thenComparing(Card::getColorId));
		else
			hand.sort(Comparator.comparing(Card::isTalon).thenComparing(Card::getColorlessId));
		return hand;
	}

}
