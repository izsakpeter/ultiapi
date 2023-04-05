package hu.ulti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.ulti.server.controller.UltiController;
import hu.ulti.server.handlerOld.ScoreHandler;
import hu.ulti.server.modelOld.Game;
import hu.ulti.server.modelOld.Result;
import hu.ulti.server.modelOld.SayMsg;
import hu.ulti.server.modelOld.Score;

public class ScoreHandlerTest {

	public static final Logger log = LoggerFactory.getLogger(UltiController.class);

	@Test
	void scoreTest() {
		List<Score> scoreListExcept = new ArrayList<Score>();
		scoreListExcept.add(new Score(1, 40, 40));
		scoreListExcept.add(new Score(2, -20, -20));
		scoreListExcept.add(new Score(3, 0, 0));
		scoreListExcept.add(new Score(4, -20, -20));
		
		Game game = new Game();

		List<Result> resultList = new ArrayList<Result>();
		resultList.add(new Result("xxxx", 1, 5, true, ""));
		game.setResultList(resultList);

		List<Score> scoreList = new ArrayList<Score>();
		scoreList.add(new Score(1, 0, 0));
		scoreList.add(new Score(2, 0, 0));
		scoreList.add(new Score(3, 0, 0));
		scoreList.add(new Score(4, 0, 0));
		game.setScores(scoreList);

		List<Integer> players = new ArrayList<Integer>();
		players.add(1);
		players.add(2);
		players.add(4);

		List<SayMsg> sayMsgList = new ArrayList<SayMsg>();
		sayMsgList.add(new SayMsg(2, 1, 5));
		sayMsgList.add(new SayMsg(4, 1, 5));
		sayMsgList.add(new SayMsg(1, 1, 5, "ok"));
		sayMsgList.add(new SayMsg(1, 2, 5));
		sayMsgList.add(new SayMsg(2, 2, 5, "ok"));
		sayMsgList.add(new SayMsg(4, 2, 5, "ok"));
		
		game.setSayMsgList(sayMsgList);

		Assertions.assertTrue(isSameScore(scoreListExcept, ScoreHandler.setScores(game, players)));
	}

	private boolean isSameScore(List<Score> scoreList1, List<Score> scoreList2) {

		log.info("list1 size: " + scoreList1.size());
		for (int i = 0; i < scoreList1.size(); i++) {
			log.info("id: " + scoreList1.get(i).getId() + ", last: " + scoreList1.get(i).getLastPartyScore() + ", sum: "
					+ scoreList1.get(i).getSumScore());
		}
		log.info("--------------------------------------------");
		log.info("list2 size: " + scoreList2.size());
		for (int i = 0; i < scoreList2.size(); i++) {
			log.info("id: " + scoreList2.get(i).getId() + ", last: " + scoreList2.get(i).getLastPartyScore() + ", sum: "
					+ scoreList2.get(i).getSumScore());
		}

		if (scoreList1.size() != scoreList2.size())
			return false;

		Collections.sort(scoreList1, Comparator.comparing(Score::getId));
		Collections.sort(scoreList2, Comparator.comparing(Score::getId));

		for (int i = 0; i < scoreList1.size(); i++) {
			if (scoreList1.get(i).getId() != scoreList2.get(i).getId()
					|| scoreList1.get(i).getLastPartyScore() != scoreList2.get(i).getLastPartyScore()
					|| scoreList1.get(i).getSumScore() != scoreList2.get(i).getSumScore())
				return false;
		}

		return true;
	}
}
