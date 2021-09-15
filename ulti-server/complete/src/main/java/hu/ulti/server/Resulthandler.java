package hu.ulti.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import hu.ulti.server.controller.UltiController;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;
import hu.ulti.server.model.Result;

public class Resulthandler {

	private Game game;
	private int roundCounter;
	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player player3 = new Player();

	private List<Result> resultList = new ArrayList<Result>();

	private List<Integer> listPassz = Arrays.asList(0, 10, 20, 30);
	private List<Integer> list40100 = Arrays.asList(1, 11, 21, 31);
	private List<Integer> listUlti = Arrays.asList(2, 12, 22, 32);
	private List<Integer> listBetli = Arrays.asList(3, 13, 23, 33);
	private List<Integer> listDuri = Arrays.asList(4, 14, 24, 34);
	private List<Integer> listSzDuri = Arrays.asList(5, 15, 25, 35);
	private List<Integer> list20100 = Arrays.asList(6, 16, 26, 36);
	private List<Integer> listTerBetli = Arrays.asList(7, 17, 27, 37);
	private List<Integer> listTerDuri = Arrays.asList(8, 18, 28, 38);
	private List<Integer> listTerSzDuri = Arrays.asList(9, 19, 29, 39);

	private List<Integer> list10s = Arrays.asList(7, 15, 23, 31, 3, 11, 19, 27);

	public Resulthandler(Game game, int roundCounter, Player player1, Player player2, Player player3) {
		this.game = game;
		this.roundCounter = roundCounter;
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;

		if (roundCounter != 10) {

			if (isBetli() && !isBetliSuccess()) {
				game.setGameOver(true);
				resultList.add(addResult(false, getRespCallId(listBetli)));
				game.setResultList(resultList);
			} else if (isTeritettBetli() && !isBetliSuccess()) {
				game.setGameOver(true);
				resultList.add(addResult(false, getRespCallId(listTerBetli)));
				game.setResultList(resultList);
			} else if (isSzintelenDuri() && isSzintelenDuriSuccess()) {
				game.setGameOver(true);
				resultList.add(addResult(false, getRespCallId(listSzDuri)));
				game.setResultList(resultList);
			} else if (isTeritettSzintelenDuri() && isSzintelenDuriSuccess()) {
				game.setGameOver(true);
				resultList.add(addResult(false, getRespCallId(listTerSzDuri)));
				game.setResultList(resultList);
			}

		} else {
			game.setGameOver(true);

			if (isTeritettDuri() && is20100() && isUlti()) {
				proccessTerDuri();
				proccess20100();
				proccessUlti();
			} else if (isTeritettDuri() && is40100() && isUlti()) {
				proccessTerDuri();
				proccess40100();
				proccessUlti();
			} else if (isDuri() && is20100() && isUlti()) {
				proccessDuri();
				proccess20100();
				proccessUlti();
			} else if (isDuri() && is40100() && isUlti()) {
				proccessDuri();
				proccess40100();
				proccessUlti();
			} else if (isTeritettDuri() && is20100()) {
				proccessTerDuri();
				proccess20100();
			} else if (isTeritettDuri() && is40100()) {
				proccessTerDuri();
				proccess40100();
			} else if (isTeritettDuri() && isUlti()) {
				proccessTerDuri();
				proccessUlti();
			} else if (isDuri() && is20100()) {
				proccessDuri();
				proccess20100();
			} else if (isDuri() && is40100()) {
				proccessDuri();
				proccess40100();
			} else if (isDuri() && isUlti()) {
				proccessDuri();
				proccessUlti();
			} else if (is20100() && isUlti()) {
				proccess20100();
				proccessUlti();
			} else if (is40100() && isUlti()) {
				proccess40100();
				proccessUlti();
			} else if (isTeritettSzintelenDuri()) {
				resultList.add(addResult(isSzintelenDuriSuccess(), getRespCallId(listTerSzDuri)));
			} else if (isTeritettDuri()) {
				proccessTerDuri();
			} else if (isTeritettBetli()) {
				resultList.add(addResult(isBetliSuccess(), getRespCallId(listTerBetli)));
			} else if (is20100()) {
				proccess20100();
			} else if (isSzintelenDuri()) {
				resultList.add(addResult(isSzintelenDuriSuccess(), getRespCallId(listSzDuri)));
			} else if (isDuri()) {
				proccessDuri();
			} else if (isBetli()) {
				resultList.add(addResult(isBetliSuccess(), getRespCallId(listBetli)));
			} else if (isUlti()) {
				proccessUlti();
			} else if (is40100()) {
				proccess40100();
			} else if (isPassz()) {
				proccessPassz();
			}

			game.setResultList(resultList);
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	private Result addResult(boolean isSuccess, int callIndex) {
		return addResult(isSuccess, callIndex, "");
	}

	private Result addResult(boolean isSuccess, int callIndex, String comment) {
		Result result = new Result();
		UUID uuid = UUID.randomUUID();
		result.setId(uuid.toString());
		result.setPlayerId(game.getLastCallerId());
		result.setCallId(callIndex);
		result.setSuccess(isSuccess);
		result.setComment(comment);
		return result;
	}

	private boolean isPassz() {
		return isInList(listPassz);
	}

	private boolean is40100() {
		return isInList(list40100);
	}

	private boolean isUlti() {
		return isInList(listUlti);
	}

	private boolean isBetli() {
		return isInList(listBetli);
	}

	private boolean isDuri() {
		return isInList(listDuri);
	}

	private boolean isSzintelenDuri() {
		return isInList(listSzDuri);
	}

	private boolean is20100() {
		return isInList(list20100);
	}

	private boolean isTeritettBetli() {
		return isInList(listTerBetli);
	}

	private boolean isTeritettDuri() {
		return isInList(listTerDuri);
	}

	private boolean isTeritettSzintelenDuri() {
		return isInList(listTerSzDuri);
	}

	private boolean isInList(List<Integer> list) {

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < game.getPreviousCall().size(); j++) {
				if (game.getPreviousCall().get(j) == list.get(i))
					return true;
			}
		}

		return false;
	}

	private boolean isBetliSuccess() {
		Player player = UltiController.getPlayerById(game.getLastCallerId());
		return player.getStrikes().size() == 0;
	}

	private boolean isSzintelenDuriSuccess() {
		Player player = UltiController.getPlayerById(game.getLastCallerId());
		return player.getStrikes().size() == roundCounter;
	}

	private int get10Value(Player player) {
		int value = 0;

		for (int i = 0; i < player.getStrikes().size(); i++) {
			for (int j = 0; j < list10s.size(); j++) {
				if (player.getStrikes().get(i).getCard1Id() == list10s.get(j))
					value += 10;
				else if (player.getStrikes().get(i).getCard2Id() == list10s.get(j))
					value += 10;
				else if (player.getStrikes().get(i).getCard3Id() == list10s.get(j))
					value += 10;
			}

			if (player.getStrikes().get(i).getId() == 10)
				value += 10;
		}

		return value;
	}

	private int getRespCallId(List<Integer> list) {

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < game.getPreviousCall().size(); j++) {
				if (list.get(i) == game.getPreviousCall().get(j))
					return list.get(i);
			}
		}

		return 0;
	}

	private void proccessPassz() {
		int player110s = get10Value(player1);
		int player210s = get10Value(player2);
		int player310s = get10Value(player3);
		int talon10s = 0;
		
		if ((player110s + player210s + player110s) != 90)
			talon10s = 90 - (player110s + player210s + player110s);

		for (int i = 0; i < game.getSays().size(); i++) {
			if (game.getSays().get(i).getPlayerId() == player1.getId()) {
				System.out.println();
				if (game.getSays().get(i).isHave40()) {
					player110s += 40;
				} else if (game.getSays().get(i).isHave120()) {
					player110s += 20;
				} else if (game.getSays().get(i).isHave220()) {
					player110s += 40;
				} else if (game.getSays().get(i).isHave320()) {
					player110s += 60;
				}
			} else if (game.getSays().get(i).getPlayerId() == player2.getId()) {
				if (game.getSays().get(i).isHave40()) {
					player210s += 40;
				} else if (game.getSays().get(i).isHave120()) {
					player210s += 20;
				} else if (game.getSays().get(i).isHave220()) {
					player210s += 40;
				} else if (game.getSays().get(i).isHave320()) {
					player210s += 60;
				}
			} else if (game.getSays().get(i).getPlayerId() == player3.getId()) {
				if (game.getSays().get(i).isHave40()) {
					player310s += 40;
				} else if (game.getSays().get(i).isHave120()) {
					player310s += 20;
				} else if (game.getSays().get(i).isHave220()) {
					player310s += 40;
				} else if (game.getSays().get(i).isHave320()) {
					player310s += 60;
				}
			}
		}

		int caller10s = 0;
		int others10s = 0;

		if (game.getLastCallerId() == player1.getId()) {
			caller10s = player110s;
			others10s = player210s + player310s + talon10s;
		} else if (game.getLastCallerId() == player2.getId()) {
			caller10s = player210s;
			others10s = player110s + player310s + talon10s;
		} else if (game.getLastCallerId() == player3.getId()) {
			caller10s = player310s;
			others10s = player110s + player210s + talon10s;
		}

		resultList.add(addResult(caller10s > others10s, getRespCallId(listPassz),
				caller10s + " - " + others10s));
	}

	private void proccess40100() {
		Player player = UltiController.getPlayerById(game.getLastCallerId());
		int caller10Value = get10Value(player);

		boolean validCall = !player.isBluff4020();

		if (validCall) {
			caller10Value += 40;
		}

		resultList.add(addResult(caller10Value >= 100, getRespCallId(list40100), String.valueOf(caller10Value)));
	}

	private void proccess20100() {
		Player player = UltiController.getPlayerById(game.getLastCallerId());
		int caller10Value = get10Value(player);

		boolean validCall = !player.isBluff4020();

		if (validCall) {
			caller10Value += 20;
		}

		resultList.add(addResult(caller10Value >= 100, getRespCallId(list20100), String.valueOf(caller10Value)));
	}

	private void proccessDuri() {
		Player player = UltiController.getPlayerById(game.getLastCallerId());

		resultList.add(addResult(player.getStrikes().size() == 10, getRespCallId(listDuri)));
	}

	private void proccessTerDuri() {
		Player player = UltiController.getPlayerById(game.getLastCallerId());

		resultList.add(addResult(player.getStrikes().size() == 10, getRespCallId(listTerDuri)));
	}

	private void proccessUlti() {

		if (isPassz()) {
			proccessPassz();
		}

		resultList.add(addResult(isUltiSuccess(), getRespCallId(listUlti)));
	}

	private boolean isUltiSuccess() {
		boolean isSuccess = false;
		Player player = UltiController.getPlayerById(game.getLastCallerId());

		for (int i = 0; i < player.getStrikes().size(); i++) {
			if (player.getStrikes().get(i).getId() == 10) {

				int card1 = -1;
				int card2 = -1;
				int card3 = -1;

				if (player.getId() == player.getStrikes().get(i).getCard1PlayerId()) {
					card1 = player.getStrikes().get(i).getCard1Id();
					card2 = player.getStrikes().get(i).getCard2Id();
					card3 = player.getStrikes().get(i).getCard3Id();
				} else if (player.getId() == player.getStrikes().get(i).getCard2PlayerId()) {
					card1 = player.getStrikes().get(i).getCard2Id();
					card2 = player.getStrikes().get(i).getCard3Id();
					card3 = player.getStrikes().get(i).getCard1Id();
				} else if (player.getId() == player.getStrikes().get(i).getCard3PlayerId()) {
					card1 = player.getStrikes().get(i).getCard3Id();
					card2 = player.getStrikes().get(i).getCard1Id();
					card3 = player.getStrikes().get(i).getCard2Id();
				}

				if (hasAdu7(card1)) {
					int card2Color = StrikeHandler.getColor(card2);
					int card3Color = StrikeHandler.getColor(card3);
					int adu = StrikeHandler.getColor(game.getPreviousCall().get(0));

					if (adu == card2Color || adu == card3Color)
						return false;

					return true;
				}
			}
		}

		return isSuccess;
	}

	private boolean hasAdu7(int card) {

		int color = StrikeHandler.getColor(card);
		int adu = StrikeHandler.getColor(game.getPreviousCall().get(0));
		List<Integer> list7 = Arrays.asList(0, 8, 16, 24);

		for (int i = 0; i < list7.size(); i++) {
			if (card == list7.get(i) && color == adu) {
				return true;
			}
		}

		return false;
	}
}
