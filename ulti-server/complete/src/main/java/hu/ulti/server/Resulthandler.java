package hu.ulti.server;

import hu.ulti.server.controller.UltiController;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;

public class Resulthandler {
	
	private Game game;
	private int roundCounter;
	
	public Resulthandler(Game game, int roundCounter) {
		this.game = game;
		this.roundCounter = roundCounter;
		
		if (roundCounter != 10) {
			if (Helper.isBetli(game) && isBetliOver()) {
				game.setGameOver(true);
			} else if (Helper.isSzintelenDuri(game)) {
				game.setGameOver(isSzintelenDuriOver());
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
}
