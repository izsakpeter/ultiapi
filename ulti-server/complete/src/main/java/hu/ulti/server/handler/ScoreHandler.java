package hu.ulti.server.handler;

import java.util.Arrays;
import java.util.List;

import hu.ulti.server.Helper;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Result;
import hu.ulti.server.model.SayMsg;
import hu.ulti.server.model.Score;

public class ScoreHandler {
	private static List<Integer> ultiList = Arrays.asList(2, 14, 26, 38);

	public static List<Score> setScores(Game game, List<Integer> players) {
		game.setScores(clearLastPartyScores(game));

		List<Result> resultList = game.getResultList();

		for (int i = 0; i < resultList.size(); i++) {

			int callScore = Helper.getCallValue(resultList.get(i).getCallId());
			int multiplier = 1;

			if (!Helper.isSzintelenbyId(resultList.get(i).getCallId())) {
				if (isThisCallHasKontra(resultList.get(i).getCallId(), game.getSayMsgList())) {
					multiplier = getMultiplier(resultList.get(i).getCallId(), game.getSayMsgList());
				}
			}

			if (resultList.get(i).isSuccess()) {
				for (int j = 0; j < game.getScores().size(); j++) {
					if (game.getScores().get(j).getId() == resultList.get(i).getPlayerId()) {

						int callerMultiplier = 0;

						if (Helper.isSzintelenbyId(resultList.get(i).getCallId())
								&& isThisCallHasKontra(resultList.get(i).getCallId(), game.getSayMsgList())) {
							callerMultiplier = getMultiplierSzintelenCaller(resultList.get(i).getCallId(),
									game.getSayMsgList());
						} else {
							callerMultiplier = multiplier * 2;
						}

						game.getScores().get(j).setLastPartyScore(callerMultiplier * callScore);
						game.getScores().get(j).setSumScore(callerMultiplier * callScore);
					} else if (isOtherPlayer(game.getScores().get(j).getId(), players)) {

						int otherMultiplier = 0;

						if (Helper.isSzintelenbyId(resultList.get(i).getCallId())
								&& isThisCallHasKontra(resultList.get(i).getCallId(), game.getSayMsgList())) {
							multiplier = getMultiplierSzintelenOther(resultList.get(i).getCallId(),
									game.getSayMsgList(), game.getScores().get(j).getId());
						}

						otherMultiplier = multiplier * -1;

						game.getScores().get(j).setLastPartyScore(otherMultiplier * callScore);
						game.getScores().get(j).setSumScore(otherMultiplier * callScore);
					}
				}
			} else {

				if (ultiList.contains(resultList.get(i).getCallId()))
					multiplier *= 2;

				for (int j = 0; j < game.getScores().size(); j++) {
					if (game.getScores().get(j).getId() == resultList.get(i).getPlayerId()) {

						int callerMultiplier = 0;

						if (Helper.isSzintelenbyId(resultList.get(i).getCallId())
								&& isThisCallHasKontra(resultList.get(i).getCallId(), game.getSayMsgList())) {
							callerMultiplier = -1
									* getMultiplierSzintelenCaller(resultList.get(i).getCallId(), game.getSayMsgList());
						} else {
							callerMultiplier = -2 * multiplier;
						}

						game.getScores().get(j).setLastPartyScore(callerMultiplier * callScore);
						game.getScores().get(j).setSumScore(callerMultiplier * callScore);

					} else if (isOtherPlayer(game.getScores().get(j).getId(), players)) {

						if (Helper.isSzintelenbyId(resultList.get(i).getCallId())
								&& isThisCallHasKontra(resultList.get(i).getCallId(), game.getSayMsgList())) {
							multiplier = getMultiplierSzintelenOther(resultList.get(i).getCallId(),
									game.getSayMsgList(), game.getScores().get(j).getId());
						}

						game.getScores().get(j).setLastPartyScore(multiplier * callScore);
						game.getScores().get(j).setSumScore(multiplier * callScore);
					}
				}
			}
		}

		return game.getScores();
	}

	private static boolean isOtherPlayer(int id, List<Integer> players) {

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) == id)
				return true;
		}

		return false;
	}

	private static List<Score> clearLastPartyScores(Game game) {

		List<Score> scores = game.getScores();

		for (int i = 0; i < scores.size(); i++) {
			scores.get(i).resetLastPartyScore();
		}

		return scores;
	}

	private static boolean isThisCallHasKontra(int callId, List<SayMsg> list) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCallId() == callId)
				return true;
		}

		return false;
	}

	private static int getMultiplier(int callId, List<SayMsg> list) {

		int maxKontraId = 0;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCallId() == callId && list.get(i).getKontraId() > maxKontraId)
				maxKontraId = list.get(i).getKontraId();
		}

		switch (maxKontraId) {
		case 1:
			return 2;
		case 2:
			return 4;
		case 3:
			return 8;
		case 4:
			return 16;
		case 5:
			return 32;
		default:
			return 0;
		}
	}

	private static int getMultiplierSzintelenCaller(int callId, List<SayMsg> list) {

		int counter1 = 0;
		int counter2 = 0;
		int counter3 = 0;
		int counter4 = 0;
		int counter5 = 0;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCallId() == callId && list.get(i).getKontraId() == 1) {
				counter1++;
			} else if (list.get(i).getCallId() == callId && list.get(i).getKontraId() == 2) {
				counter2++;
			} else if (list.get(i).getCallId() == callId && list.get(i).getKontraId() == 3) {
				counter3++;
			} else if (list.get(i).getCallId() == callId && list.get(i).getKontraId() == 4) {
				counter4++;
			} else if (list.get(i).getCallId() == callId && list.get(i).getKontraId() == 5) {
				counter5++;
			}
		}

		int multiplier = 2;

		if (counter1 > 0) {
			if (counter2 == 0) {
				if (counter1 == 2) {
					multiplier = 3;
				} else if (counter1 == 3) {
					multiplier = 4;
				}
			} else if (counter2 > 0) {
				if (counter3 == 0) {
					if (counter2 == 2) {
						if (counter1 == 2) {
							multiplier = 5;
						} else if (counter1 == 3) {
							multiplier = 6;
						}
					} else if (counter2 == 3) {
						multiplier = 8;
					}
				} else if (counter3 > 0) {
					///////////////////////////////////////////////////////////////
				}
			}
		}

		return multiplier;
	}

	private static int getMultiplierSzintelenOther(int callId, List<SayMsg> list, int id) {

		int max = 0;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPlayerId() == id && list.get(i).getKontraId() > max) {
				max = list.get(i).getKontraId();
			}
		}

		switch (max) {
		case 1:
			return 2;
		case 2:
			return 4;
		case 3:
			return 8;
		case 4:
			return 16;
		case 5:
			return 32;
		default:
			return 1;
		}
	}
}
