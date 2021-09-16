package hu.ulti.server.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import hu.ulti.server.CallHandler;
import hu.ulti.server.Helper;
import hu.ulti.server.Resulthandler;
import hu.ulti.server.StrikeHandler;
import hu.ulti.server.model.Call;
import hu.ulti.server.model.Card;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Hand;
import hu.ulti.server.model.Request;
import hu.ulti.server.model.Say;
import hu.ulti.server.model.Strike;
import hu.ulti.server.model.Player;

@CrossOrigin
@RestController
public class UltiController {
	private static List<Player> players = Player.getPlayerList();
	private int dealer = 3;
	private List<List<Card>> hands = null;
	private List<Card> talon = new ArrayList<Card>();

	Game game = new Game();

	private final static Long LONG_POLLING_TIMEOUT = 600000L;
	private ExecutorService statusPoll = Executors.newFixedThreadPool(5);

	private static int roundCounter = 1;

	@PostMapping("status")
	public DeferredResult<Game> keepAlive(@RequestBody Request request) {
		DeferredResult<Game> output = new DeferredResult<>(LONG_POLLING_TIMEOUT);
		statusPoll.execute(new Runnable() {
			@Override
			public void run() {

				int i = 0;

				try {
					while (i < 6000) {
						if (game.getLastModificationTimeStamp() > request.getLastTimeStamp()) {
							Player player = getPlayerById(request.getId());
							Game clone = game.clone();
							clone.setPlayer(player);
							output.setResult(clone);
							break;
						}
						i++;
						Thread.sleep(100);
					}

				} catch (Exception e) {
					output.setErrorResult("ERROR");
				}
			}
		});

		output.onTimeout(() -> {
			game.setErrorMessage("TIMEOUT");
			output.setResult(game);
		});

		return output;
	}

	@PostMapping("start")
	public String shuffle(@RequestBody Request request) {

		int id = request.getId();
		
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == 0) {
				players.get(i).setId(id);
			}
		}

		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).isReady())
				return "waiting";
		}

		if (game.isRoundStarted()) {
			game.setLastModificationTimeStamp(System.currentTimeMillis());
			return "Round started";
		} else {
			if (hands == null)
				hands = Helper.getHands(dealer);

			if (dealer == 1) {
				players.get(1).setColorForced(true);
				game.setActivePlayer(players.get(1).getId());
			} else if (dealer == 2) {
				players.get(2).setColorForced(true);
				game.setActivePlayer(players.get(2).getId());
			} else if (dealer == 3) {
				players.get(0).setColorForced(true);
				game.setActivePlayer(players.get(0).getId());
			}

			for (int i = 0; i < players.size(); i++) {
				players.get(i).setHand(hands.get(i));
				players.get(i).getHand().sort(Comparator.comparing(Card::getId));
			}

			talon = hands.get(3);

			game.setPlayer1Hand(Hand.fillHandWithMinusOne(player1));
			game.setPlayer2Hand(Hand.fillHandWithMinusOne(player2));
			game.setPlayer3Hand(Hand.fillHandWithMinusOne(player3));

			game.setRoundStarted(true);
			game.setLastModificationTimeStamp(System.currentTimeMillis());

			return "ok";
		}
	}

	@PostMapping("order")
	public String changeOrder(@RequestBody Request request) {

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == request.getId())
				players.get(i).setColorOrder(request.isColorOrder());
		}

		game.setLastModificationTimeStamp(System.currentTimeMillis());
		return "ok";
	}

	@PostMapping("startingvalue")
	public String setStartingValue(@RequestBody Request request) {

		if (request.getId() == game.getActivePlayer() && request.getValue() > 0) {

			
			for (int i = 0; i < players.size(); i++) {
				if (request.getId() == players.get(i).getId()) {
					players.get(i).setHand(Card.addTalon(players.get(i), talon));
					game.setPlayer1Hand(Hand.fillHandWithMinusOne(players.get(i)));
				}
			}

			game.setStartingValue(request.getValue());

			game.setLastModificationTimeStamp(System.currentTimeMillis());

			return "ok";
		}

		game.setLastModificationTimeStamp(System.currentTimeMillis());
		return "bad";
	}

	@PostMapping("call")
	public String call(@RequestBody Request request) {

		if (request.getId() == game.getActivePlayer()) {

			game.setCall(request.getCall());
			
			for (int i = 0; i < players.size(); i++) {
				if (request.getId() == players.get(i).getId()) {
					if (CallHandler.callChecker(game, players.get(i).isColorForced())) {
						if (players.get(i).isColorForced())
							players.get(i).setColorForced(false);

						players.get(i).setCallOk(true);
						players.get(i).setBluff4020(request.isBluff4020());
						talon = Card.getTalonById(request.getTalonid());
						players.get(i).setHand(Card.removeTalon(players.get(i), talon));
						game.setPlayer1Hand(Hand.fillHandWithMinusOne(players.get(i)));
						game.setLastCallerId(request.getId());
						game.setPreviousCall(game.getCall());
						game.setCall(new ArrayList<>());
						game.setActivePlayer(player2.getId()); // id nővelő kell ide
					} else {
						players.get(i).setCallOk(false);
					}
				}
			}

			game.setLastModificationTimeStamp(System.currentTimeMillis());
			return "ok";
		}

		game.setLastModificationTimeStamp(System.currentTimeMillis());
		return "bad";
	}

	@PostMapping("join")
	public String join(@RequestBody Request request) {

		if (request.getId() == game.getActivePlayer()) {
			if (!request.isIsjoin()) {

				if (game.getLastCallerId() == game.getActivePlayer()) {
					game.setPlayReadyToStart(true);
					game.setFirstTurn(true);
					game.setLastModificationTimeStamp(System.currentTimeMillis());
					return "kezdődik a játék";
				}

				//idnővelő
				if (request.getId() == player1.getId())
					game.setActivePlayer(player2.getId());
				else if (request.getId() == player2.getId())
					game.setActivePlayer(player3.getId());
				else if (request.getId() == player3.getId())
					game.setActivePlayer(player1.getId());

				game.setLastModificationTimeStamp(System.currentTimeMillis());

				return "passz";

			} else {

				//hand refaktor
				if (request.getId() == player1.getId()) {
					player1.setHand(Card.addTalon(player1, talon));
					game.setPlayer1Hand(Hand.fillHandWithMinusOne(player1));
				} else if (request.getId() == player2.getId()) {
					player2.setHand(Card.addTalon(player2, talon));
					game.setPlayer2Hand(Hand.fillHandWithMinusOne(player2));
				} else if (request.getId() == player3.getId()) {
					player3.setHand(Card.addTalon(player3, talon));
					game.setPlayer3Hand(Hand.fillHandWithMinusOne(player3));
				}

				game.setLastModificationTimeStamp(System.currentTimeMillis());

				return "felvette";
			}
		}

		return "bad";
	}

	@PostMapping("say")
	public String say(@RequestBody Request request) {
		Say someSay = new Say(request.getId(), request.isHave40(), request.isHave120(), request.isHave220(),
				request.isHave320());

		boolean haveSay = false;

		for (int i = 0; i < game.getSays().size(); i++) {
			if (game.getSays().get(i).getPlayerId() == someSay.getPlayerId())
				haveSay = true;
		}

		if (!haveSay)
			game.addSayToList(someSay);

		return "someSay";
	}

	@PostMapping("play")
	public String play(@RequestBody Request request) {

		if (game.isPlayReadyToStart() && request.getId() == game.getActivePlayer()) {
			if (request.getId() == player1.getId()) {
				game.getRound().addCardToStrike(request.getCardid(), request.getId());
				player1.setHand(Card.removeCardbyId(player1, request.getCardid()));
				game.setPlayer1Hand(Hand.fillHandWithMinusOne(player1));
				game.setActivePlayer(player2.getId());
			} else if (request.getId() == player2.getId()) {
				game.getRound().addCardToStrike(request.getCardid(), request.getId());
				player2.setHand(Card.removeCardbyId(player2, request.getCardid()));
				game.setPlayer2Hand(Hand.fillHandWithMinusOne(player2));
				game.setActivePlayer(player3.getId());

			} else if (request.getId() == player3.getId()) {
				game.getRound().addCardToStrike(request.getCardid(), request.getId());
				player3.setHand(Card.removeCardbyId(player3, request.getCardid()));
				game.setPlayer3Hand(Hand.fillHandWithMinusOne(player3));
				game.setActivePlayer(player1.getId());
			}

			if (game.getRound().getCard1Id() != -1 && game.getRound().getCard2Id() != -1
					&& game.getRound().getCard3Id() != -1) {

				game.setFirstTurn(false);
				StrikeHandler strikeHandler = new StrikeHandler(roundCounter, game, player1, player2, player3);
				game = strikeHandler.getGame();
				player1 = strikeHandler.getPlayer1();
				player2 = strikeHandler.getPlayer2();
				player3 = strikeHandler.getPlayer3();

				if (roundCounter == 1 && Call.isTeritett(game.getPreviousCall())) {
					game.setPlayer1Hand(Hand.setHandWithCardes(player1));
					game.setPlayer2Hand(Hand.setHandWithCardes(player2));
					game.setPlayer3Hand(Hand.setHandWithCardes(player3));
				}

				Resulthandler resultHandler = new Resulthandler(game, roundCounter, players);
				game = resultHandler.getGame();

				if (game.isGameOver()) {
					for (int i = 0; i < players.size(); i++) {
						players.get(i).setReady(false);
						players.get(i).setHand(null);
					}
					hands = null;					
					talon = null;
					game.setRoundStarted(false);
					game.setPlayReadyToStart(false);
					game.setStartingValue(0);
					game.setLastCallerId(0);
					game.setPreviousCall(new ArrayList<Integer>());
				}

				roundCounter++;
			}

			game.setLastModificationTimeStamp(System.currentTimeMillis());
			return "ok";
		}

		game.setLastModificationTimeStamp(System.currentTimeMillis());
		return "bad";
	}

	@PostMapping("newgame")
	public String newGame(@RequestBody Request request) {

		int id = request.getId();

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == 0) {
				players.get(i).setId(id);
			}
		}

		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).isReady())
				return "waiting";
		}

		if (game.isRoundStarted()) {
			game.setLastModificationTimeStamp(System.currentTimeMillis());
			return "Round started";
		} else {
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setStrikes(new ArrayList<Strike>());
			}			
			game.setGameOver(false);

			dealer = Helper.dealerHandler(dealer);
			hands = Helper.getHands(dealer);

			if (dealer == 1) {
				player2.setColorForced(true);
				game.setActivePlayer(player2.getId());
			} else if (dealer == 2) {
				player3.setColorForced(true);
				game.setActivePlayer(player3.getId());
			} else if (dealer == 3) {
				player1.setColorForced(true);
				game.setActivePlayer(player1.getId());
			}

			for (int i = 0; i < players.size(); i++) {
				players.get(i).setHand(hands.get(i));
				players.get(i).getHand().sort(Comparator.comparing(Card::getId));
			}
			talon = hands.get(3);

			game.setPlayer1Hand(Hand.fillHandWithMinusOne(player1));
			game.setPlayer2Hand(Hand.fillHandWithMinusOne(player2));
			game.setPlayer3Hand(Hand.fillHandWithMinusOne(player3));

			game.setRoundStarted(true);
			game.setLastModificationTimeStamp(System.currentTimeMillis());

			return "ok";
		}
	}

	public static Player getPlayerById(int id) {
		
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == id)
				return players.get(i);
		}

		return null;
	}
}
