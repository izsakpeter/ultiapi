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
import hu.ulti.server.handler.ScoreHandler;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Result;
import hu.ulti.server.model.Score;

public class ScoreHandlerTest {

	public static final Logger log = LoggerFactory.getLogger(UltiController.class);

	@Test
	void scoreTest() {
		List<Score> scoreListExcept = new ArrayList<Score>();
		scoreListExcept.add(new Score(1, 91, -5));
		scoreListExcept.add(new Score(2, -38, 10));
		scoreListExcept.add(new Score(3, -53, -5));

		List<Result> resultList = new ArrayList<Result>();
		resultList.add(new Result("xxxx", 2, 5, true, ""));

		List<Score> scoreList = new ArrayList<Score>();
		scoreList.add(new Score(1, 96, 96));
		scoreList.add(new Score(2, -48, -48));
		scoreList.add(new Score(3, -48, -48));

		Game game = new Game();
		game.setResultList(resultList);
		game.setScores(scoreList);

		Assertions.assertTrue(isSameScore(scoreListExcept, ScoreHandler.setScores(game, null)));
	}

	private boolean isSameScore(List<Score> scoreList1, List<Score> scoreList2) {

		log.info("list1 size: " + scoreList1.size());
		for (int i = 0; i < scoreList1.size(); i++) {
			log.info("id: " + scoreList1.get(i).getId() + ", last: " + scoreList1.get(i).getLastPartyScore() + ", sum: "
					+ scoreList1.get(i).getSumScore());
		}

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
