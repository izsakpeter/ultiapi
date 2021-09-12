package hu.ulti.server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hu.ulti.server.controller.UltiController;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;
import hu.ulti.server.model.Result;

public class Resulthandler {
	
	private Game game;
	private int roundCounter;
	
	public Resulthandler(Game game, int roundCounter) {
		this.game = game;
		this.roundCounter = roundCounter;
		
		if (roundCounter != 10) {
			if (Helper.isBetli(game) && isBetliOver()) {
				game.setGameOver(true);
				game.setResultList(setResult(game, false));
			} else if (Helper.isSzintelenDuri(game) && isSzintelenDuriOver()) {
				game.setGameOver(true);
				game.setResultList(setResult(game, false));
			}
		} else {
			if (Helper.isBetli(game)) {
				game.setGameOver(isBetliOver());
			} else if (Helper.isSzintelenDuri(game)) {
				game.setGameOver(isSzintelenDuriOver());
			}
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	private boolean isBetliOver() {
		Player player = UltiController.getPlayerById(game.getLastCallerId());
		return player.getStrikes().size() > 0;
	}

	private boolean isSzintelenDuriOver() {
		Player player = UltiController.getPlayerById(game.getLastCallerId());
		return player.getStrikes().size() != roundCounter;
	}
	
	private List<Result> setResult(Game game, boolean isSuccess){
		List<Result> resultList = new ArrayList<>();
		UUID uuid = UUID.randomUUID();
		
		Result result = new Result();
		result.setId(uuid.toString());
		result.setPlayerId(game.getLastCallerId());
		result.setCallId(game.getPreviousCall().get(0));
		result.setSuccess(isSuccess);
		
		resultList.add(result);
		
		return resultList;
	}
}
