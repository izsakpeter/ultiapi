package hu.ulti;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.ulti.server.controller.UltiController;
import hu.ulti.server.handlerOld.PlayedCardHandler;
import hu.ulti.server.modelOld.Call;
import hu.ulti.server.modelOld.Card;
import hu.ulti.server.modelOld.Game;
import hu.ulti.server.modelOld.Player;
import hu.ulti.server.modelOld.Request;

public class PlayedCardChecker {

	public static final Logger log = LoggerFactory.getLogger(UltiController.class);

	private Request request = new Request();
	private Game game = new Game();
	private List<Call> callList = new ArrayList<Call>();
	private List<Player> playersRound1 = getPlayersRound1();
	private List<Player> playersRound10 = getPlayersRound10();

	// p:1 c: 0 3 4 9 12 22 24 25 26 31
	// p:8 c: 2 1 5 10 14 16 23 27 28 30
	// p:88 c: 6 7 11 13 15 17 18 20 21 29
	
	@Test
	void ultiPlayer2Round10OK() {
		request.setId(1);
		request.setCardid(0);

		callList.add(new Call(0, null));
		callList.add(new Call(4, null));
		game.setPreviousCall(callList);
		game.setLastCallerId(1);

		game.getRound().setCard1Id(6);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);

		Assertions.assertTrue(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound10));
	}
	
	@Test
	void ultiPlayer2Round1False() {
		request.setId(1);
		request.setCardid(0);

		callList.add(new Call(0, null));
		callList.add(new Call(4, null));
		game.setPreviousCall(callList);
		game.setLastCallerId(1);

		game.getRound().setCard1Id(6);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);

		Assertions.assertFalse(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}
	
	@Test
	void ultiPlayer1Round1OK() {
		request.setId(1);
		request.setCardid(0);

		callList.add(new Call(0, null));
		callList.add(new Call(4, null));
		game.setPreviousCall(callList);
		game.setLastCallerId(1);

		game.getRound().setCard1Id(-1);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);

		Assertions.assertTrue(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound10));
	}
	
	@Test
	void ultiPlayer1Round1False() {
		request.setId(1);
		request.setCardid(0);

		callList.add(new Call(0, null));
		callList.add(new Call(4, null));
		game.setPreviousCall(callList);
		game.setLastCallerId(1);

		game.getRound().setCard1Id(-1);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);

		Assertions.assertFalse(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}
	

	@Test
	void makkPasszPlayer2() {
		request.setId(8);
		request.setCardid(5);

		callList.add(new Call(0, null));
		game.setPreviousCall(callList);

		game.getRound().setCard1Id(4);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);

		Assertions.assertTrue(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}

	@Test
	void makkPasszPlayer1() {
		request.setId(1);
		request.setCardid(3);

		callList.add(new Call(0, null));
		game.setPreviousCall(callList);

		game.getRound().setCard1Id(-1);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);

		Assertions.assertTrue(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}

	@Test
	void szintelenduriPlayer3WC() {

		request.setId(88);
		request.setCardid(29);

		callList.add(new Call(47, null));
		game.setPreviousCall(callList);

		game.getRound().setCard1Id(12);
		game.getRound().setCard2Id(14);
		game.getRound().setCard3Id(-1);

		Assertions.assertFalse(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}

	@Test
	void szintelenduriPlayer3S() {

		request.setId(88);
		request.setCardid(13);

		callList.add(new Call(47, null));
		game.setPreviousCall(callList);

		game.getRound().setCard1Id(12);
		game.getRound().setCard2Id(14);
		game.getRound().setCard3Id(-1);

		Assertions.assertFalse(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}

	@Test
	void szintelenduriPlayer3B() {

		request.setId(88);
		request.setCardid(29);

		callList.add(new Call(47, null));
		game.setPreviousCall(callList);

		game.getRound().setCard1Id(26);
		game.getRound().setCard2Id(28);
		game.getRound().setCard3Id(-1);

		Assertions.assertTrue(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}

	@Test
	void szintelenduriPlayer3OK() {

		request.setId(88);
		request.setCardid(29);

		callList.add(new Call(47, null));
		game.setPreviousCall(callList);

		game.getRound().setCard1Id(31);
		game.getRound().setCard2Id(27);
		game.getRound().setCard3Id(-1);

		Assertions.assertTrue(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}

	@Test
	void betliPlayer2WC() {

		request.setId(8);
		request.setCardid(10);

		callList.add(new Call(41, null));
		game.setPreviousCall(callList);

		game.getRound().setCard1Id(22);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);

		Assertions.assertFalse(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}

	@Test
	void betliPlayer2S() {

		request.setId(8);
		request.setCardid(16);

		callList.add(new Call(21, null));
		game.setPreviousCall(callList);

		game.getRound().setCard1Id(22);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);

		Assertions.assertFalse(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}

	@Test
	void betliPlayer2B() {

		request.setId(8);
		request.setCardid(28);

		callList.add(new Call(21, null));
		game.setPreviousCall(callList);

		game.getRound().setCard1Id(26);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);

		Assertions.assertTrue(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}

	@Test
	void betliPlayer1() {

		request.setId(1);
		request.setCardid(3);

		callList.add(new Call(5, null));
		game.setPreviousCall(callList);

		game.getRound().setCard1Id(-1);
		game.getRound().setCard2Id(-1);
		game.getRound().setCard3Id(-1);

		Assertions.assertTrue(PlayedCardHandler.isPlayedCardCorrect(game, request, playersRound1));
	}

	private List<Player> getPlayersRound1() {
		List<Player> players = new ArrayList<Player>();
		Player player1 = new Player(1);
		Player player2 = new Player(8);
		Player player3 = new Player(88);

		List<Card> player1Hand = new ArrayList<Card>(); // 2 3 4 9 12 22 24 25 26 31
		List<Card> player2Hand = new ArrayList<Card>(); // 0 1 5 10 14 16 23 27 28 30
		List<Card> player3Hand = new ArrayList<Card>(); // 6 7 11 13 15 17 18 20 21 29

		player1Hand.add(new Card(0, 1));
		player1Hand.add(new Card(3, 1));
		player1Hand.add(new Card(4, 1));
		player1Hand.add(new Card(9, 2));
		player1Hand.add(new Card(12, 2));
		player1Hand.add(new Card(22, 3));
		player1Hand.add(new Card(24, 4));
		player1Hand.add(new Card(25, 4));
		player1Hand.add(new Card(26, 4));
		player1Hand.add(new Card(31, 4));
		player1.setHand(player1Hand);

		player2Hand.add(new Card(1, 1));
		player2Hand.add(new Card(2, 1));
		player2Hand.add(new Card(5, 1));
		player2Hand.add(new Card(10, 2));
		player2Hand.add(new Card(14, 2));
		player2Hand.add(new Card(16, 3));
		player2Hand.add(new Card(23, 3));
		player2Hand.add(new Card(27, 4));
		player2Hand.add(new Card(28, 4));
		player2Hand.add(new Card(30, 4));
		player2.setHand(player2Hand);

		player3Hand.add(new Card(6, 1));
		player3Hand.add(new Card(7, 1));
		player3Hand.add(new Card(11, 2));
		player3Hand.add(new Card(13, 2));
		player3Hand.add(new Card(15, 2));
		player3Hand.add(new Card(17, 3));
		player3Hand.add(new Card(18, 3));
		player3Hand.add(new Card(20, 3));
		player3Hand.add(new Card(21, 3));
		player3Hand.add(new Card(29, 4));
		player3.setHand(player3Hand);

		players.add(player1);
		players.add(player2);
		players.add(player3);

		return players;
	}
	
	private List<Player> getPlayersRound10() {
		List<Player> players = new ArrayList<Player>();
		Player player1 = new Player(1);
		Player player2 = new Player(8);
		Player player3 = new Player(88);

		List<Card> player1Hand = new ArrayList<Card>(); // 2 3 4 9 12 22 24 25 26 31
		List<Card> player2Hand = new ArrayList<Card>(); // 0 1 5 10 14 16 23 27 28 30
		List<Card> player3Hand = new ArrayList<Card>(); // 6 7 11 13 15 17 18 20 21 29

		player1Hand.add(new Card(0, 1));
		player1.setHand(player1Hand);

		player2Hand.add(new Card(1, 1));
		player2.setHand(player2Hand);

		player3Hand.add(new Card(6, 1));
		player3.setHand(player3Hand);

		players.add(player1);
		players.add(player2);
		players.add(player3);

		return players;
	}
}
