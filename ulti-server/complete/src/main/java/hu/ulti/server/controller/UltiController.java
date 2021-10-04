package hu.ulti.server.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import hu.ulti.server.Helper;
import hu.ulti.server.handler.CallHandler;
import hu.ulti.server.handler.KontraHandler;
import hu.ulti.server.handler.Resulthandler;
import hu.ulti.server.handler.StrikeHandler;
import hu.ulti.server.model.Call;
import hu.ulti.server.model.Card;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Hand;
import hu.ulti.server.model.Request;
import hu.ulti.server.model.Result;
import hu.ulti.server.model.Say;
import hu.ulti.server.model.SayMsg;
import hu.ulti.server.model.Strike;
import hu.ulti.server.model.Player;

@CrossOrigin
@RestController
public class UltiController {

	private static int playersNumber = 4;
	private static List<Player> players = Helper.getPlayerList(playersNumber);
	private int dealer = Helper.setFirstDealer(playersNumber);

	private static List<Hand> handList = new ArrayList<Hand>();
	private List<List<Card>> hands = null;
	private List<Card> talon = new ArrayList<Card>();
	private Game game = new Game();
	private static int roundCounter = 1;

	private final static Long LONG_POLLING_TIMEOUT = 600000L;
	private ExecutorService statusPoll = Executors.newFixedThreadPool(5);

	public static final Logger log = LoggerFactory.getLogger(UltiController.class);

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
				players.set(i, new Player(id));
				break;
			}
		}

		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).isReady()) {
				game.setLastModificationTimeStamp(System.currentTimeMillis());
				return "waiting";
			}
		}

		if (game.isRoundStarted()) {
			game.setLastModificationTimeStamp(System.currentTimeMillis());
			return "Round started";
		} else {
			setStarterPlayer();
			setHands(dealer);

			talon = hands.get(3);
			game.setTalon(Helper.fillTalonWithMinusOne());
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
					players.get(i).setHand(Helper.addTalon(players.get(i), talon));
					handList.set(i, Helper.fillHandWithCoveredCards(players.get(i)));
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
						talon = Helper.getTalonById(request.getTalonid());
						players.get(i).setHand(Helper.removeTalon(players.get(i), talon));
						handList.set(i, Helper.fillHandWithCoveredCards(players.get(i)));
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
					game.setPreviousCall(CallHandler.getFinalCall(game, players));
					return "kezdődik a játék";
				}

				for (int i = 0; i < players.size(); i++) {
					if (players.get(i).getId() == game.getActivePlayer()) {
						game.setActivePlayer(getIncreasedPlayerId(i));
						break;
					}
				}

				game.setLastModificationTimeStamp(System.currentTimeMillis());

				return "passz";

			} else {

				for (int i = 0; i < players.size(); i++) {
					if (request.getId() == players.get(i).getId()) {
						players.get(i).setHand(Helper.addTalon(players.get(i), talon));
						handList.set(i, Helper.fillHandWithCoveredCards(players.get(i)));
						game.setHands(handList);
					}
				}

				game.setLastModificationTimeStamp(System.currentTimeMillis());

				return "felvette";
			}
		}

		return "bad";
	}

	@PostMapping("sayparti")
	public String sayParti(@RequestBody Request request) {
		Say someSay = new Say(request.getId(), request);

		for (int i = 0; i < players.size(); i++) {
			if (someSay.getPlayerId() == players.get(i).getId()) {
				players.get(i).setSaid(true);
				game.addSayToList(someSay);
				checkLastSay(someSay);
				break;
			}
		}

		game.setLastModificationTimeStamp(System.currentTimeMillis());

		return "someSayParti";
	}

	@PostMapping("saykontra")
	public String saykontra(@RequestBody Request request) {
		Say someSay = new Say(request);

		for (int i = 0; i < players.size(); i++) {
			if (someSay.getPlayerId() == players.get(i).getId() && someSay.getKontraId() == 1) {
				players.get(i).setSaid(true);
				break;
			}
		}

		game.setPreviousCall(KontraHandler.kontraHandler(someSay, game));

		if (game.isKontraPartFinished()) {

			if (Helper.isTeritett(game.getPreviousCall())) {
				for (int i = 0; i < players.size(); i++) {
					handList.set(i, Helper.fillHandWithUncoveredCards(players.get(i)));
					game.setHands(handList);
				}
			}

			game.setActivePlayer(getActivePlayerIdAfterKontra());
		}

		game.setLastModificationTimeStamp(System.currentTimeMillis());
		return "someSayKontra";
	}

	@PostMapping("play")
	public String play(@RequestBody Request request) {

		if (game.isPlayReadyToStart() && request.getId() == game.getActivePlayer()) {

			if (game.getRound().getCard1Id() != -1 && game.getRound().getCard2Id() != -1
					&& game.getRound().getCard3Id() != -1) {
				game.getRound().clearStrike();
			}

			for (int i = 0; i < players.size(); i++) {
				if (request.getId() == players.get(i).getId()) {
					game.getRound().addCardToStrike(request.getCardid(), request.getId());
					players.get(i).setHand(Helper.removeCardbyId(players.get(i), request.getCardid()));
					handList.set(i, Helper.fillHandWithCoveredCards(players.get(i)));
					game.setHands(handList);
					game.setActivePlayer(getIncreasedPlayerId(i));
				}
			}

			if (game.getRound().getCard1Id() != -1 && game.getRound().getCard2Id() != -1
					&& game.getRound().getCard3Id() != -1) {
				game.setFirstTurn(false);

				StrikeHandler strikeHandler = new StrikeHandler(roundCounter, game, players);
				game = strikeHandler.getGame();

				for (int i = 0; i < players.size(); i++) {
					players.set(i, strikeHandler.getPlayers().get(i));
				}

				if (!game.isKontraPartFinished()) {
					game.setActivePlayer(game.getLastCallerId());
				} else {

					if (Helper.isTeritett(game.getPreviousCall())) {
						for (int i = 0; i < players.size(); i++) {
							handList.set(i, Helper.fillHandWithUncoveredCards(players.get(i)));
							game.setHands(handList);
						}
					}

					Resulthandler resultHandler = new Resulthandler(game, roundCounter, players);
					game = resultHandler.getGame();

					roundCounter++;

					if (game.isGameOver()) {
						for (int i = 0; i < players.size(); i++) {
							players.get(i).setReady(false);
							players.get(i).setHand(new ArrayList<Card>());
						}
						hands = null;
						game.setTalon(talon);
						talon = null;
						game.setRoundStarted(false);
						game.setPlayReadyToStart(false);
						game.setStartingValue(0);
						game.setLastCallerId(0);
						game.setPreviousCall(new ArrayList<Call>());
						game.setSayMsgList(new ArrayList<SayMsg>());
						game.setSays(new ArrayList<Say>());
						game.setFirstTurn(true);
					}
				}
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
			if (players.get(i).getId() == id) {
				players.get(i).setReady(true);
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
			game.getRound().clearStrike();
			game.setResultList(new ArrayList<Result>());
			roundCounter = 1;

			dealer = Helper.dealerHandler(dealer, playersNumber);
			setStarterPlayer();
			setHands(dealer);

			talon = hands.get(3);
			game.setTalon(Helper.fillTalonWithMinusOne());
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

	private int getIncreasedPlayerId(int activePlayerIndex) {

		int indexPl = activePlayerIndex + 1 >= players.size() ? 0 : activePlayerIndex + 1;

		if (!players.get(indexPl).isPlaying())
			return getIncreasedPlayerId(indexPl);

		return players.get(indexPl).getId();
	}

	private int getActivePlayerIdAfterKontra() {

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getStrikes().size() > 0)
				return players.get(i).getId();
		}

		return -1;
	}

	private void checkLastSay(Say say) {
		if (say.isHave120())
			game.addSayMsgToList(new SayMsg(say.getPlayerId(), "20"));

		if (say.isHave220())
			game.addSayMsgToList(new SayMsg(say.getPlayerId(), "2x20"));

		if (say.isHave320())
			game.addSayMsgToList(new SayMsg(say.getPlayerId(), "3x20"));

		if (say.isHave40())
			game.addSayMsgToList(new SayMsg(say.getPlayerId(), "40"));
	}

	private void setStarterPlayer() {
		for (int i = 0; i < players.size(); i++) {
			if (dealer == i) {
				int nextPl = i + 1 >= players.size() ? 0 : i + 1;
				players.get(nextPl).setColorForced(true);
				game.setActivePlayer(players.get(nextPl).getId());
				break;
			}
		}
	}

	private void setHands(int dealer) {
		game.setHands(new ArrayList<Hand>());
		handList = new ArrayList<Hand>();
		hands = Helper.getHands();

		if (players.size() == 4) {
			int handIndex = 0;

			for (int i = 0; i < players.size(); i++) {

				if (dealer == i) {
					players.get(i).setPlaying(false);
					players.get(i).setHand(new ArrayList<Card>());
					handList.add(i, new Hand());
					handList.set(i, Helper.setEmptyHand(players.get(i)));
				} else {
					players.get(i).setPlaying(true);
					players.get(i).setHand(hands.get(handIndex));
					players.get(i).getHand().sort(Comparator.comparing(Card::getId));
					handList.add(i, new Hand());
					handList.set(i, Helper.fillHandWithCoveredCards(players.get(i)));
					handIndex++;
				}
			}
		} else {
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setPlaying(true);
				players.get(i).setHand(hands.get(i));
				players.get(i).getHand().sort(Comparator.comparing(Card::getId));
				handList.add(i, new Hand());
				handList.set(i, Helper.fillHandWithCoveredCards(players.get(i)));
			}
		}
	}
}