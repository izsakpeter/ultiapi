package hu.ulti.server.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import hu.ulti.server.Helper;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;
import hu.ulti.server.request.StatusRequest;

@Service
public class GameServiceImp implements GameService {
	
	private static int playersNumber = 3;
	private static List<Player> players = Helper.getEmptyPlayers(playersNumber);
	
	private final static Long LONG_POLLING_TIMEOUT = 600000L;
	private ExecutorService statusPoll = Executors.newFixedThreadPool(5);
	
	private Game game = new Game();

	@Override
	public void setPlayerReady(Player player) {

		for (int i = 0; i < players.size(); i++) {
			
			if (players.get(i).getPlayerId() == player.getPlayerId())
				break;
			
			if (players.get(i).getPlayerId() == -1) {
				players.set(i, player);
				game.setLastModificationTimeStamp(System.currentTimeMillis());
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
		output.onTimeout(() -> {
			game.setErrorMessage("TIMEOUT");
			output.setResult(game);
		});*/

		return output;
	}
}
