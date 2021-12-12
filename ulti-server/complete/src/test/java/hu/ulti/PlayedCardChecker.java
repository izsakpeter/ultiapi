package hu.ulti;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.ulti.server.Helper;
import hu.ulti.server.controller.UltiController;
import hu.ulti.server.model.Call;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;
import hu.ulti.server.model.Request;

public class PlayedCardChecker {

	public static final Logger log = LoggerFactory.getLogger(UltiController.class);

	@Test
	void playedCardTest() {

		Request request = new Request();
		request.setId(1);
		request.setCardId(0);

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(1));
		players.add(new Player(8));
		players.add(new Player(88));
		
		Game game = new Game();
		List<Call> callList = new ArrayList<Call>();
		callList.add(new Call(4, null));
		game.setPreviousCall(callList);
		
		game.getRound().setCard1Id(-1);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);
		

		Assertions.assertTrue(Helper.isPlayedCardCorrect(game, request, players));

	}

}
