package hu.ulti.server.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player player3 = new Player();

	Game game = new Game();

	private int dealer = 3;
	private int activePlayer = 1;
	private int startingValue = 0;

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

			player1.setHand(hands.get(0));
			player2.setHand(hands.get(1));
			player3.setHand(hands.get(2));

			Player player = new Player();

			if (id == 1)
				player = player1;
			if (id == 2)
				player = player2;
			if (id == 3)
				player = player3;

			player.getHand().sort(Comparator.comparing(Card::isTalon).thenComparing(Card::getColorId));

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

		if (id == activePlayer) {
			startingValue = value;

			Player player = new Player();

			if (id == 1) {
				player1.setHand(Card.showTalon(player1.getHand(), player1.getOrder()));
				player = player1;
			}

			if (id == 2) {
				player2.setHand(Card.showTalon(player2.getHand(), player2.getOrder()));
				player = player2;
			}

			if (id == 3) {
				player3.setHand(Card.showTalon(player3.getHand(), player3.getOrder()));
				player = player3;
			}

			game.setStartingValue(startingValue);
			game.setPlayer(player);

			return game;
		}

		return null;
	}

	@GetMapping("/call")
	public Game call(@RequestParam int id, @RequestParam int value, @RequestParam int talon1,
			@RequestParam int talon2) {

		if (id == activePlayer) {
			Player player = new Player();

			if (id == 1) {
				player1.setHand(Card.removeTalon(player1.getHand(), player1.getOrder(), talon1, talon2));
				player2.setHand(Card.addTalon(player2.getHand(), player2.getOrder(), talon1, talon2));
				player = player1;
				activePlayer = 2;
			}

			if (id == 2) {
				player2.setHand(Card.removeTalon(player2.getHand(), player2.getOrder(), talon1, talon2));
				player3.setHand(Card.addTalon(player3.getHand(), player3.getOrder(), talon1, talon2));
				player = player2;
				activePlayer = 3;
			}

			if (id == 3) {
				player3.setHand(Card.removeTalon(player3.getHand(), player3.getOrder(), talon1, talon2));
				player1.setHand(Card.addTalon(player1.getHand(), player1.getOrder(), talon1, talon2));
				player = player3;
				activePlayer = 1;
			}

			game.setPlayer(player);

			return game;
		}

		return null;
	}

}
