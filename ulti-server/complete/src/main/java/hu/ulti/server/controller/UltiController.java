package hu.ulti.server.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.ulti.server.Helper;
import hu.ulti.server.model.Call;
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

	@GetMapping("/ulti")
	public Game shuffle(@RequestParam int id) {

		switch (id) {
		case 1:
			isPlayer1Ready = true;
			player1.setId(id);
			break;
		case 2:
			isPlayer2Ready = true;
			player2.setId(id);
			break;
		case 3:
			player3.setId(id);
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

			if (id == player1.getId())
				player = player1;
			if (id == player2.getId())
				player = player2;
			if (id == player3.getId())
				player = player3;

			player.getHand().sort(Comparator.comparing(Card::getOrderColorId));

			game.setPlayer(player);

			return game;
		}

		return null;
	}

	@GetMapping("/order")
	public Game changeOrder(@RequestParam int id, @RequestParam int orderid) {

		Player player = new Player();

		if (id == player1.getId())
			player = player1;
		if (id == player2.getId())
			player = player2;
		if (id == player3.getId())
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

		if (id == game.getActivePlayer()) {
			Player player = new Player();

			if (id == player1.getId()) {
				player1.setHand(Card.addTalon(player1, talon));
				player1.setForcedCallId(value);
				player = player1;
			}

			if (id == player2.getId()) {
				player2.setHand(Card.addTalon(player2, talon));
				player2.setForcedCallId(value);
				player = player2;
			}

			if (id == player3.getId()) {
				player3.setHand(Card.addTalon(player3, talon));
				player3.setForcedCallId(value);
				player = player3;
			}

			game.setStartingValue(value);
			game.setPlayer(player);

			return game;
		}

		return null;
	}

	@GetMapping("/call")
	public Game call(@RequestParam int id, @RequestParam List<Integer> call, @RequestParam List<Integer> talonid) {

		talon = Card.getTalonById(talonid);

		if (id == game.getActivePlayer()) {
			Player player = new Player();

			if (id == player1.getId()) {

				if (Call.callChecker(game.getPreviousCall(), call, player.getForcedCallId())) {
					game.setLastCallerId(id);
					game.setPreviousCall(call);
					player1.setHand(Card.removeTalon(player1, talon));
					player = player1;
					game.setActivePlayer(player2.getId());

				} else {
					player1.setCallOk(false);
					player = player1;
				}
			}

			// p2 p3

			game.setPlayer(player);

			return game;
		}

		return null;
	}

	@GetMapping("/join")
	public Game call(@RequestParam int id, @RequestParam int action) {
		// action 0 passz
		// action 1 beszállás

		if (id == game.getActivePlayer()) {

			Player player = new Player();

			if (action == 0) {

				if (game.getLastCallerId() == game.getActivePlayer()) {
					if (id == player1.getId())
						player = player1;
					if (id == player2.getId())
						player = player2;
					if (id == player3.getId())
						player = player3;

					game.setGameReadyToStart(true);
					game.setPlayer(player);

					return game;
				}

				if (id == player1.getId()) {
					player = player1;
					game.setActivePlayer(player2.getId());
				}

				if (id == player2.getId()) {
					player = player2;
					game.setActivePlayer(player3.getId());
				}

				if (id == player3.getId()) {
					player = player3;
					game.setActivePlayer(player1.getId());
				}
			} else {
				if (id == player1.getId()) {
					player1.setHand(Card.addTalon(player1, talon));
					player = player1;
				}

				if (id == player2.getId()) {
					player2.setHand(Card.addTalon(player2, talon));
					player = player2;
				}

				if (id == player3.getId()) {
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
