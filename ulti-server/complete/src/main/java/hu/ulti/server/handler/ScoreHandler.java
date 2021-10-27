package hu.ulti.server.handler;

import java.util.List;

import hu.ulti.server.Helper;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Result;
import hu.ulti.server.model.Score;

public class ScoreHandler {

	public static List<Score> setScores(Game game, List<Integer> players) {
		List<Result> resultList = game.getResultList();

		for (int i = 0; i < resultList.size(); i++) {

			int callScore = Helper.getCallValue(resultList.get(i).getCallId());

			if (resultList.get(i).isSuccess()) {
				for (int j = 0; j < game.getScores().size(); j++) {
					if (game.getScores().get(j).getId() == resultList.get(i).getPlayerId()) {
						game.getScores().get(j).setLastPartyScore(2 * callScore);
						game.getScores().get(j).setSumScore(2 * callScore);
					} else if (istOtherPlayer(game.getScores().get(j).getId(), players)) {
						game.getScores().get(j).setLastPartyScore(-1 * callScore);
						game.getScores().get(j).setSumScore(-1 * callScore);
					}
				}
			} else {
				for (int j = 0; j < game.getScores().size(); j++) {
					if (game.getScores().get(j).getId() == resultList.get(i).getPlayerId()) {
						game.getScores().get(j).setLastPartyScore(-2 * callScore);
						game.getScores().get(j).setSumScore(-2 * callScore);
					} else if (istOtherPlayer(game.getScores().get(j).getId(), players)) {
						game.getScores().get(j).setLastPartyScore(callScore);
						game.getScores().get(j).setSumScore(callScore);
					}
				}
			}
		}

		return game.getScores();
	}
	
	private static boolean istOtherPlayer(int id, List<Integer> players) {
		
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) == id)
				return true;
		}
		
		return false;
	}
}
