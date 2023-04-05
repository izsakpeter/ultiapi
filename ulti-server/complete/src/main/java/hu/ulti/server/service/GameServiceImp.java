package hu.ulti.server.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import hu.ulti.server.helper.Helper;
import hu.ulti.server.model.Card;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Hand;
import hu.ulti.server.model.Player;
import hu.ulti.server.request.StatusRequest;

@Service
public class GameServiceImp implements GameService {

	private static int playersNumber = 3;
	private static List<Player> players = Helper.getEmptyPlayers(playersNumber);
	private int dealer = Helper.setFirstDealer(playersNumber);

	private final static Long LONG_POLLING_TIMEOUT = 600000L;
	private ExecutorService statusPoll = Executors.newFixedThreadPool(5);

	private Game game = new Game();
	
	private List<List<Card>> cardPacks = null;
	private static List<Hand> handList = new ArrayList<Hand>();
	private List<Card> talon = new ArrayList<Card>();

	@Override
	public void setPlayerReady(Player player) {

		for (int i = 0; i < players.size(); i++) {

			if (players.get(i).getPlayerId() == player.getPlayerId())
				break;

			if (players.get(i).getPlayerId() == -1) {
				players.set(i, player);
				game.setLastModificationTimeStamp(System.currentTimeMillis());

				if (i == players.size() - 1) {
					startRound();
				}
				
				break;
			}
		}
	}

	@Override
	public DeferredResult<Game> gameStatus(StatusRequest request) {

		DeferredResult<Game> output = new DeferredResult<>(LONG_POLLING_TIMEOUT);
		statusPoll.execute(new Runnable() {
			@Override
			public void run() {

				int i = 0;

				try {
					while (i < 6000) {
						if (game.getLastModificationTimeStamp() > request.getLastTimeStamp()) {
							Game clone = game.clone();
							clone.setPlayers(Helper.setPlayers(players));
							clone.setPlayer(Helper.getPlayerById(players, request.getPlayerId()));
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

		/*
		 * output.onTimeout(() -> { game.setErrorMessage("TIMEOUT");
		 * output.setResult(game); });
		 */

		return output;
	}
	
	/////////////////////////////////////////////
	
	private void startRound() {
		game.setRoundStarted(true);
		setStarterPlayer();
		setHands();
		game.setTalon(Helper.getCoveredTalon());
		game.setHands(handList);
		game.setScores(Helper.getDefaultScoreList(players));
		
	}
	
	
	private void setStarterPlayer() {
		for (int i = 0; i < players.size(); i++) {
			if (dealer == i) {
				int nextPl = i + 1 >= players.size() ? 0 : i + 1;
				players.get(nextPl).setColorForced(true);
				game.setActivePlayerId(players.get(nextPl).getPlayerId());
				break;
			}
		}
	}
	
	private void setHands() {
		cardPacks = Helper.getPacks();
		game.setHands(new ArrayList<Hand>());
		handList = new ArrayList<Hand>();
		talon = cardPacks.get(3);

		if (players.size() == 4) {
			int handIndex = 0;

			for (int i = 0; i < players.size(); i++) {

				if (dealer == i) {
					players.get(i).setPlaying(false);
					players.get(i).setHand(new ArrayList<Card>());
					handList.set(i, Helper.setEmptyHand(players.get(i)));
				} else {
					players.get(i).setPlaying(true);
					players.get(i).setHand(cardPacks.get(handIndex));
					players.get(i).getHand().sort(Comparator.comparing(Card::getId));
					handList.set(i, Helper.fillHandWithCoveredCards(players.get(i)));
					handIndex++;
				}
			}
		} else {
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setPlaying(true);
				players.get(i).setHand(cardPacks.get(i));
				players.get(i).getHand().sort(Comparator.comparing(Card::getId));
				handList.set(i, Helper.fillHandWithCoveredCards(players.get(i)));
			}
		}
	}
}
