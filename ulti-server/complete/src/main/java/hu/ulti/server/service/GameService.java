package hu.ulti.server.service;

import org.springframework.web.context.request.async.DeferredResult;

import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;
import hu.ulti.server.request.StatusRequest;

public interface GameService {
	
	void setPlayerReady(Player player);
	
	DeferredResult<Game> gameStatus(StatusRequest request);

}
