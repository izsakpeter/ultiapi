package hu.ulti.server.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.ulti.server.Helper;
import hu.ulti.server.StrikeHandler;
import hu.ulti.server.model.Call;
import hu.ulti.server.model.Card;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;

@RestController
public class UltiController {
	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player player3 = new Player();

	private int dealer = 3;

	private List<List<Card>> hands = null;
	private List<Card> talon = new ArrayList<Card>();

	Game game = new Game();

	@GetMapping("/start")
	public Game shuffle(@RequestParam int id) {

		if (player1.getId() == 0)
			player1 = new Player(id);
		else if (player2.getId() == 0)
			player2 = new Player(id);
		else if (player3.getId() == 0)
			player3 = new Player(id);

		if (player1.isReady() && player2.isReady() && player3.isReady()) {

			if (!game.isRoundStarted()) {

				if (hands == null)
					hands = Helper.getHands(dealer);

				switch (dealer) {
				case 1:
					game.setActivePlayer(player2.getId());
					break;
				case 2:
					game.setActivePlayer(player3.getId());
					break;
				case 3:
					game.setActivePlayer(player1.getId());
					break;
				}

				player1.setHand(hands.get(0));
				player2.setHand(hands.get(1));
				player3.setHand(hands.get(2));
				talon = hands.get(3);

				game.setRoundStarted(true);
			}

			Player player = getPlayerById(id);
			player.getHand().sort(Comparator.comparing(Card::getOrderColorId));
			game.setPlayer(player);

			return game;
		}

		return null;
	}

	@GetMapping("/order")
	public Game changeOrder(@RequestParam int id, @RequestParam boolean iscolororder) {

		Player player = getPlayerById(id);

		if (iscolororder)
			player.setColorOrder(true);
		else
			player.setColorOrder(false);

		game.setPlayer(player);

		return game;
	}

	@GetMapping("/startingvalue")
	public Game setStartingValue(@RequestParam int id, @RequestParam int value) {

		if (id == game.getActivePlayer()) {

			Player player = new Player();

			if (id == player1.getId()) {
				player1.setHand(Card.addTalon(player1, talon));
				player = player1;
			} else if (id == player2.getId()) {
				player2.setHand(Card.addTalon(player2, talon));
				player = player2;
			} else if (id == player3.getId()) {
				player3.setHand(Card.addTalon(player3, talon));
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

		if (id == game.getActivePlayer()) {
			Player player = new Player();

			if (id == player1.getId()) {

				if (Call.callChecker(game, call, player1.isColorForced())) {
					if (player1.isColorForced())
						player1.setColorForced(false);
					
					player1.setCallOk(true);
					talon = Card.getTalonById(talonid);
					game.setLastCallerId(id);
					game.setPreviousCall(call);
					player1.setHand(Card.removeTalon(player1, talon));
					game.setActivePlayer(player2.getId());
					player = player1;
				} else {
					player1.setCallOk(false);
					player = player1;
				}
			} else if (id == player2.getId()) {

				if (Call.callChecker(game, call, player2.isColorForced())) {
					if (player2.isColorForced())
						player2.setColorForced(false);
					
					player2.setCallOk(true);
					talon = Card.getTalonById(talonid);
					game.setLastCallerId(id);
					game.setPreviousCall(call);
					player2.setHand(Card.removeTalon(player2, talon));
					game.setActivePlayer(player3.getId());
					player = player2;
				} else {
					player2.setCallOk(false);
					player = player2;
				}
			} else if (id == player3.getId()) {

				if (Call.callChecker(game, call, player3.isColorForced())) {
					if (player3.isColorForced())
						player3.setColorForced(false);
					
					player3.setCallOk(true);
					talon = Card.getTalonById(talonid);
					game.setLastCallerId(id);
					game.setPreviousCall(call);
					player3.setHand(Card.removeTalon(player3, talon));
					game.setActivePlayer(player1.getId());
					
					player = player3;
				} else {
					player3.setCallOk(false);
					player = player3;
				}
			}

			game.setPlayer(player);

			return game;
		}

		return null;
	}

	@GetMapping("/join")
	public Game call(@RequestParam int id, @RequestParam boolean isjoin) {
		if (id == game.getActivePlayer()) {

			Player player = new Player();

			if (!isjoin) {

				if (game.getLastCallerId() == game.getActivePlayer()) {
					player = getPlayerById(id);
					game.setPlayReadyToStart(true);
					game.setPlayer(player);

					return game;
				}

				if (id == player1.getId()) {
					player = player1;
					game.setActivePlayer(player2.getId());
				} else if (id == player2.getId()) {
					player = player2;
					game.setActivePlayer(player3.getId());
				} else if (id == player3.getId()) {
					player = player3;
					game.setActivePlayer(player1.getId());
				}
			} else {
				if (id == player1.getId()) {
					player1.setHand(Card.addTalon(player1, talon));
					player = player1;
				} else if (id == player2.getId()) {
					player2.setHand(Card.addTalon(player2, talon));
					player = player2;
				} else if (id == player3.getId()) {
					player3.setHand(Card.addTalon(player3, talon));
					player = player3;
				}
			}

			game.setPlayer(player);

			return game;
		}

		return null;
	}

	@GetMapping("/play")
	public Game play(@RequestParam int id, @RequestParam int cardid) {

		if (game.isPlayReadyToStart() && id == game.getActivePlayer()) {

			Player player = new Player();

			if (id == player1.getId()) {
				game.getRound().setCard1Id(cardid);
				player1.setHand(Card.removeCardbyId(player1, cardid));
				game.setActivePlayer(player2.getId());
				player = player1;

			} else if (id == player2.getId()) {
				game.getRound().setCard2Id(cardid);
				player2.setHand(Card.removeCardbyId(player1, cardid));
				game.setActivePlayer(player3.getId());
				player = player2;

			} else if (id == player3.getId()) {
				game.getRound().setCard3Id(cardid);
				player3.setHand(Card.removeCardbyId(player1, cardid));
				game.setActivePlayer(player1.getId());
				player = player3;
			}

			game = StrikeHandler.strikeHandler(game);

			if (id == player3.getId()) {
				switch (game.getLastStrikeId()) {
				case 1:
					player1.setStrikes(game.getLastStrike());
					break;
				case 2:
					player2.setStrikes(game.getLastStrike());
					break;
				case 3:
					player3.setStrikes(game.getLastStrike());
					break;
				}
			} else if (id == player1.getId()) {

				switch (game.getLastStrikeId()) {
				case 1:
					player2.setStrikes(game.getLastStrike());
					break;
				case 2:
					player3.setStrikes(game.getLastStrike());
					break;
				case 3:
					player1.setStrikes(game.getLastStrike());
					break;
				}
			} else if (id == player2.getId()) {

				switch (game.getLastStrikeId()) {
				case 1:
					player3.setStrikes(game.getLastStrike());
					break;
				case 2:
					player1.setStrikes(game.getLastStrike());
					break;
				case 3:
					player2.setStrikes(game.getLastStrike());
					break;
				}
			}
			game.setLastStrikeId(0);

			if (player1.getHand().size() == 0 && player2.getHand().size() == 0 && player3.getHand().size() == 0) {
				// eredmény
			}

			game.setPlayer(player);

		}

		return game;
	}

	private Player getPlayerById(int id) {

		Player player = new Player();

		if (id == player1.getId())
			player = player1;
		else if (id == player2.getId())
			player = player2;
		else if (id == player3.getId())
			player = player3;

		return player;
	}
}
