package hu.ulti.server.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import hu.ulti.server.Helper;
import hu.ulti.server.model.Call;
import hu.ulti.server.model.Card;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Request;
import hu.ulti.server.model.Strike;
import hu.ulti.server.model.Player;

@CrossOrigin
@RestController
public class UltiController {
	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player player3 = new Player();

	private int dealer = 3;

	private List<List<Card>> hands = null;
	private List<Card> talon = new ArrayList<Card>();

	Game game = new Game();

	private final static Long LONG_POLLING_TIMEOUT = 60000L;
	private ExecutorService statusPoll = Executors.newFixedThreadPool(5);

	@PostMapping("status")
	public DeferredResult<Game> keepAlive(@RequestBody Request request) {
		DeferredResult<Game> output = new DeferredResult<>(LONG_POLLING_TIMEOUT);
		statusPoll.execute(new Runnable() {
			@Override
			public void run() {

				int i = 0;

				try {
					while (i < 600) {
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

		if (player1.getId() == 0)
			player1 = new Player(id);
		else if (player2.getId() == 0 && id != player1.getId())
			player2 = new Player(id);
		else if (player3.getId() == 0 && id != player1.getId() && id != player2.getId())
			player3 = new Player(id);

		if (!player1.isReady() || !player2.isReady() || !player3.isReady()) {
			game.setLastModificationTimeStamp(System.currentTimeMillis());
			return "waiting";
		}

		if (game.isRoundStarted()) {
			game.setLastModificationTimeStamp(System.currentTimeMillis());
			return "Round started";
		} else {
			if (hands == null)
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

			player1.setHand(hands.get(0));
			player1.getHand().sort(Comparator.comparing(Card::getId));
			player2.setHand(hands.get(1));
			player2.getHand().sort(Comparator.comparing(Card::getId));
			player3.setHand(hands.get(2));
			player3.getHand().sort(Comparator.comparing(Card::getId));
			talon = hands.get(3);

			game.setRoundStarted(true);
			game.setLastModificationTimeStamp(System.currentTimeMillis());

			return "ok";
		}
	}

	@PostMapping("order")
	public String changeOrder(@RequestBody Request request) {

		if (request.getId() == player1.getId())
			player1.setColorOrder(request.isColorOrder());
		else if (request.getId() == player2.getId())
			player2.setColorOrder(request.isColorOrder());
		else if (request.getId() == player3.getId())
			player3.setColorOrder(request.isColorOrder());

		game.setLastModificationTimeStamp(System.currentTimeMillis());
		return "ok";
	}

	@PostMapping("startingvalue")
	public String setStartingValue(@RequestBody Request request) {

		if (request.getId() == game.getActivePlayer() && request.getValue() > 0) {

			if (request.getId() == player1.getId())
				player1.setHand(Card.addTalon(player1, talon));
			else if (request.getId() == player2.getId())
				player2.setHand(Card.addTalon(player2, talon));
			else if (request.getId() == player3.getId())
				player3.setHand(Card.addTalon(player3, talon));

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

			if (request.getId() == player1.getId()) {

				if (Call.callChecker(game, player1.isColorForced())) {
					if (player1.isColorForced())
						player1.setColorForced(false);

					player1.setCallOk(true);
					talon = Card.getTalonById(request.getTalonid());
					player1.setHand(Card.removeTalon(player1, talon));
					game.setLastCallerId(request.getId());
					game.setPreviousCall(game.getCall());
					game.setCall(new ArrayList<>());
					game.setActivePlayer(player2.getId());
				} else {
					player1.setCallOk(false);
				}
			} else if (request.getId() == player2.getId()) {

				if (Call.callChecker(game, player2.isColorForced())) {
					if (player2.isColorForced())
						player2.setColorForced(false);

					player2.setCallOk(true);
					talon = Card.getTalonById(request.getTalonid());
					player2.setHand(Card.removeTalon(player2, talon));
					game.setLastCallerId(request.getId());
					game.setPreviousCall(game.getCall());
					game.setCall(new ArrayList<>());
					game.setActivePlayer(player3.getId());
				} else {
					player2.setCallOk(false);
				}
			} else if (request.getId() == player3.getId()) {

				if (Call.callChecker(game, player3.isColorForced())) {
					if (player3.isColorForced())
						player3.setColorForced(false);

					player3.setCallOk(true);
					talon = Card.getTalonById(request.getTalonid());
					player3.setHand(Card.removeTalon(player3, talon));
					game.setLastCallerId(request.getId());
					game.setPreviousCall(game.getCall());
					game.setCall(new ArrayList<>());
					game.setActivePlayer(player1.getId());
				} else {
					player3.setCallOk(false);
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
					game.setLastModificationTimeStamp(System.currentTimeMillis());
					return "kezdődik a játék";
				}

				if (request.getId() == player1.getId())
					game.setActivePlayer(player2.getId());
				else if (request.getId() == player2.getId())
					game.setActivePlayer(player3.getId());
				else if (request.getId() == player3.getId())
					game.setActivePlayer(player1.getId());

				game.setLastModificationTimeStamp(System.currentTimeMillis());

				return "passz";

			} else {
				if (request.getId() == player1.getId())
					player1.setHand(Card.addTalon(player1, talon));
				else if (request.getId() == player2.getId())
					player2.setHand(Card.addTalon(player2, talon));
				else if (request.getId() == player3.getId())
					player3.setHand(Card.addTalon(player3, talon));

				game.setLastModificationTimeStamp(System.currentTimeMillis());

				return "felvette";
			}
		}

		return "bad";
	}

	@PostMapping("play")
	public String play(@RequestBody Request request) {

		if (game.isPlayReadyToStart() && request.getId() == game.getActivePlayer()) {
			if (request.getId() == player1.getId()) {
				game.getRound().setCard1Id(request.getCardid());
				player1.setHand(Card.removeCardbyId(player1, request.getCardid()));
				game.setActivePlayer(player2.getId());
			} else if (request.getId() == player2.getId()) {
				game.getRound().setCard2Id(request.getCardid());
				player2.setHand(Card.removeCardbyId(player2, request.getCardid()));
				game.setActivePlayer(player3.getId());

			} else if (request.getId() == player3.getId()) {
				game.getRound().setCard3Id(request.getCardid());
				player3.setHand(Card.removeCardbyId(player3, request.getCardid()));
				game.setActivePlayer(player1.getId());
			}
			
			if (game.getRound().getCard1Id() != -1 && game.getRound().getCard2Id() != -1 && game.getRound().getCard3Id() != -1) {
				
				if (true) {
					
					player1.addStrike(new Strike(game.getRound().getCard1Id(), game.getRound().getCard2Id(), game.getRound().getCard3Id()));
					
					game.getRound().setCard1Id(-1);
					game.getRound().setCard2Id(-1);
					game.getRound().setCard3Id(-1);
				}
			}
			
			game.setLastModificationTimeStamp(System.currentTimeMillis());
			return "ok";
		}

		game.setLastModificationTimeStamp(System.currentTimeMillis());
		return "bad";
	}

	private Player getPlayerById(int id) {

		Player player = null;

		if (id == player1.getId())
			player = player1;
		else if (id == player2.getId())
			player = player2;
		else if (id == player3.getId())
			player = player3;

		return player;
	}
}
