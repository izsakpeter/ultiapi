package hu.ulti.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import hu.ulti.server.model.Game;
import hu.ulti.server.request.Request;
import hu.ulti.server.request.StatusRequest;
import hu.ulti.server.response.Response;
import hu.ulti.server.service.GameService;

@CrossOrigin
@RestController
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@PostMapping("gamestatus")
	public DeferredResult<Game> keepAlive(@RequestBody StatusRequest request) {
		return gameService.gameStatus(request);
	}
	
	@PostMapping("order")
	public Response changeOrder(@RequestBody Request request) {
		return gameService.changeOrder(request);
	}
}
