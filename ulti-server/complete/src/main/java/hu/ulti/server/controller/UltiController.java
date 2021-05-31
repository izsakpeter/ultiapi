package hu.ulti.server.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.ulti.server.Helper;
import hu.ulti.server.model.Card;
import hu.ulti.server.model.Game;

@RestController
public class UltiController {

	private boolean isPlayer1Ready = false;
	private boolean isPlayer2Ready = false;
	private boolean isPlayer3Ready = false;
	
	private List<List<Card>> hands = null;
	private List<Card> hand1 = new ArrayList<Card>();
	private List<Card> hand2 = new ArrayList<Card>();
	private List<Card> hand3 = new ArrayList<Card>();
	
	private int dealer = 3;
	/*private int starterPlayer = 1;
	private int startingValue = 0;*/
	
	@GetMapping("/ulti/{id}")
	public Game shuffle(@PathVariable int id) {

		switch (id) {
			case 1:
				isPlayer1Ready = true;
				break;
			case 2:
				isPlayer2Ready = true;
				break;
			case 3:
				isPlayer3Ready = true;
				break;
		}

		if (isPlayer1Ready && isPlayer2Ready && isPlayer3Ready) {
			
			if (hands == null)
				hands = Helper.getHands(dealer);
			
			List<Card> hand = new ArrayList<Card>();
			hand1 = hands.get(0);
			hand2 = hands.get(1);
			hand3 = hands.get(2);
			
			if (id == 1)
				hand = hand1;
			if (id == 2)
				hand = hand2;
			if (id == 3)
				hand = hand3;
			
			hand.sort(Comparator.comparing(Card::isTalon).thenComparing(Card::getColorId));
			
			return new Game(hand);
		}

		return null;
	}
	
	@GetMapping("/order")
	public Game changeOrder(@RequestParam int id, @RequestParam int orderid) {

		List<Card> hand = new ArrayList<Card>();
		
		Game game = new Game();
		
		if (id == 1)
			hand = hand1;
		if (id == 2)
			hand = hand2;
		if (id == 3)
			hand = hand3;
		
		if (orderid == 0) {
			game.setOrder(0);
			hand.sort(Comparator.comparing(Card::isTalon).thenComparing(Card::getColorId));
		} else {
			game.setOrder(1);
			hand.sort(Comparator.comparing(Card::isTalon).thenComparing(Card::getColorlessId));
		}
		
		game.setHand(hand);
		
		return game;
	}
	
	/*
	@GetMapping("/startingValue")
	public Game setStartingValue(@RequestParam int id, @RequestParam int value) {
	
		if (id == starterPlayer)
			startingValue = value;
		
		Game game = new Game();
		
		game.setStartingValue(startingValue);
		
		return game;
	}*/

}
