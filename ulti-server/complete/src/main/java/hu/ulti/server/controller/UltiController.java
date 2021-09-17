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
	private static List<Hand> handList = new ArrayList<Hand>();
	private int dealer = 3;
	private List<List<Card>> hands = null;
	private List<Card> talon = new ArrayList<Card>();
	private Game game = new Game();

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
			hands = Helper.getHands(dealer);
			setStarterPlayer();

			for (int i = 0; i < players.size(); i++) {
				players.get(i).setHand(hands.get(i));
				players.get(i).getHand().sort(Comparator.comparing(Card::getId));
				
				handList.add(i, new Hand());
				handList.set(i, Hand.fillHandWithMinusOne(players.get(i)));
			}
			
			talon = hands.get(3);

			game.setHands(handList);
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
					handList.set(i, Hand.fillHandWithMinusOne(players.get(i)));
				}
			}
			
			game.setHands(handList);
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
						handList.set(i, Hand.fillHandWithMinusOne(players.get(i)));
						game.setHands(handList);
						game.setLastCallerId(request.getId());
						game.setPreviousCall(game.getCall());
						game.setCall(new ArrayList<>());
						game.setActivePlayer(getIncreasedPlayerId(i));
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

				for (int i = 0; i < players.size(); i++) {
					game.setActivePlayer(getIncreasedPlayerId(i));
				}

				game.setLastModificationTimeStamp(System.currentTimeMillis());

				return "passz";

			} else {

				for (int i = 0; i < players.size(); i++) {
					if (request.getId() == players.get(i).getId()) {
						players.get(i).setHand(Card.addTalon(players.get(i), talon));
						handList.set(i, Hand.fillHandWithMinusOne(players.get(i)));
						game.setHands(handList);
					}
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
			
			for (int i = 0; i < players.size(); i++) {
				if (request.getId() == players.get(i).getId()) {
					game.getRound().addCardToStrike(request.getCardid(), request.getId());
					players.get(i).setHand(Card.removeCardbyId(players.get(i), request.getCardid()));
					handList.set(i, Hand.fillHandWithMinusOne(players.get(i)));
					game.setHands(handList);
					game.setActivePlayer(getIncreasedPlayerId(i));
				}
			}

			if (game.getRound().getCard1Id() != -1 && game.getRound().getCard2Id() != -1
					&& game.getRound().getCard3Id() != -1) {

				game.setFirstTurn(false);
				StrikeHandler strikeHandler = new StrikeHandler(roundCounter, game, players);
				game = strikeHandler.getGame();
				players.set(0, strikeHandler.getPlayer1());
				players.set(1, strikeHandler.getPlayer2());
				players.set(2, strikeHandler.getPlayer3());

				if (Call.isTeritett(game.getPreviousCall())) {
					for (int i = 0; i < players.size(); i++) {
						handList.set(i, Hand.setHandWithCards(players.get(i)));
						game.setHands(handList);
					}
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

			setStarterPlayer();

			for (int i = 0; i < players.size(); i++) {
				players.get(i).setHand(hands.get(i));
				players.get(i).getHand().sort(Comparator.comparing(Card::getId));
				
				handList.add(i, new Hand());
				handList.set(i, Hand.fillHandWithMinusOne(players.get(i)));
			}
			
			talon = hands.get(3);

			game.setHands(handList);
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
	
	private  int getIncreasedPlayerId(int index) {
		
		for (int i = 0; i < players.size(); i++) {
			if (index == i) {
				int indexPl = i + 1 > players.size() ? 0 : i + 1;
				return players.get(indexPl).getId();
			}
		}
		
		return 0;
	}
	
	private void setStarterPlayer() {
		for (int i = 0; i < players.size(); i++) {
			if (dealer == i) {
				int indexPl = i + 1 > players.size() ? 0 : i + 1;
				players.get(indexPl).setColorForced(true);
				game.setActivePlayer(getIncreasedPlayerId(i));
			}
		}
	}
}
