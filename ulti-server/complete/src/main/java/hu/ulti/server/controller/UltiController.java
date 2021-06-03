package hu.ulti.server.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.ulti.server.Helper;
import hu.ulti.server.model.Card;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;

@RestController
public class UltiController {

	private boolean isPlayer1Ready = false;
	private boolean isPlayer2Ready = false;
	private boolean isPlayer3Ready = false;

	private List<List<Card>> hands = null;
	private List<Card> talon = new ArrayList<Card>();

	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player player3 = new Player();

	Game game = new Game();

	private int dealer = 3;
	private int activePlayer = 1;
	private int startingValue = 0;
	private int lastCaller = 1;

	@GetMapping("/ulti")
	public Game shuffle(@RequestParam int id) {

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

			player1.setHand(hands.get(0));
			player2.setHand(hands.get(1));
			player3.setHand(hands.get(2));
			talon = hands.get(3);

			Player player = new Player();

			if (id == 1)
				player = player1;
			if (id == 2)
				player = player2;
			if (id == 3)
				player = player3;

			player.getHand().sort(Comparator.comparing(Card::getColorId));

			game.setPlayer(player);

			return game;
		}

		return null;
	}

	@GetMapping("/order")
	public Game changeOrder(@RequestParam int id, @RequestParam int orderid) {

		Player player = new Player();

		if (id == 1)
			player = player1;
		if (id == 2)
			player = player2;
		if (id == 3)
			player = player3;

		if (orderid == 0)
			player.setOrder(0);
		else
			player.setOrder(1);

		Helper.orderHand(player.getHand(), orderid);

		game.setPlayer(player);

		return game;
	}

	@GetMapping("/startingValue")
	public Game setStartingValue(@RequestParam int id, @RequestParam int value) {
		
		// 1 makk
		// 2 zold
		// 3 tok
		// 4 piros

		if (id == activePlayer) {
			startingValue = value;

			Player player = new Player();

			if (id == 1) {
				player1.setHand(Card.addTalon(player1, talon));
				player = player1;
			}

			if (id == 2) {
				player2.setHand(Card.addTalon(player2, talon));
				player = player2;
			}

			if (id == 3) {
				player3.setHand(Card.addTalon(player3, talon));
				player = player3;
			}

			game.setStartingValue(startingValue);
			game.setPlayer(player);

			return game;
		}

		return null;
	}

	@GetMapping("/call")
	public Game call(@RequestParam int id, @RequestParam List<Integer> value, @RequestParam List<Integer> talonid) {

		talon = Card.getTalonById(talonid);

		if (id == activePlayer) {
			Player player = new Player();

			if (id == 1) {
				player1.setHand(Card.removeTalon(player1, talon));
				player = player1;
				activePlayer = 2;
				lastCaller = 1;
			}

			if (id == 2) {
				player1.setHand(Card.removeTalon(player2, talon));
				player = player1;
				activePlayer = 3;
				lastCaller = 2;
			}

			if (id == 3) {
				player1.setHand(Card.removeTalon(player3, talon));
				player = player1;
				activePlayer = 1;
				lastCaller = 3;
			}

			game.setPlayer(player);

			return game;
		}

		return null;
	}

	@GetMapping("/join")
	public Game call(@RequestParam int id, @RequestParam int action) {
		// action 0 passz
		// action 1 beszállás

		if (id == activePlayer) {

			Player player = new Player();

			if (action == 0) {
				
				if (lastCaller == activePlayer) {
					// kezdődik a játék
					return null;
				}
				
				if (id == 1) {
					player = player1;
					activePlayer = 2;
				}

				if (id == 2) {
					player = player2;
					activePlayer = 3;
				}

				if (id == 3) {
					player = player3;
					activePlayer = 1;
				}
			} else {
				if (id == 1) {
					player1.setHand(Card.addTalon(player1, talon));
					player = player1;
				}

				if (id == 2) {
					player2.setHand(Card.addTalon(player2, talon));
					player = player2;
				}

				if (id == 3) {
					player3.setHand(Card.addTalon(player3, talon));
					player = player3;
				}
			}

			game.setPlayer(player);

			return game;
		}

		return null;
	}

}
