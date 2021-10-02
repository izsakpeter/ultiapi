package hu.ulti.server.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import hu.ulti.server.Constants;
import hu.ulti.server.controller.UltiController;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Player;
import hu.ulti.server.model.Result;
import hu.ulti.server.model.Strike;
import hu.ulti.server.model.StrikeList;

public class Resulthandler {

	private Game game;
	private int roundCounter;
	private List<Player> players = new ArrayList<Player>();

	private List<Result> resultList = new ArrayList<Result>();

	public static List<Integer> listPassz = Arrays.asList(0, 12, 24, 36);
	public static List<Integer> listCsendesSzaz = Arrays.asList(1, 13, 25, 37);
	public static List<Integer> listCsendesUlti = Arrays.asList(2, 14, 26, 38);
	public static List<Integer> list40100 = Arrays.asList(3, 15, 27, 39);
	public static List<Integer> listUlti = Arrays.asList(4, 16, 28, 40);
	public static List<Integer> listBetli = Arrays.asList(5, 17, 29, 41);
	public static List<Integer> listDuri = Arrays.asList(6, 18, 30, 42);
	public static List<Integer> listSzDuri = Arrays.asList(7, 19, 31, 43);
	public static List<Integer> list20100 = Arrays.asList(8, 20, 32, 44);
	public static List<Integer> listTerBetli = Arrays.asList(9, 21, 33, 45);
	public static List<Integer> listTerDuri = Arrays.asList(10, 22, 34, 46);
	public static List<Integer> listTerSzDuri = Arrays.asList(11, 23, 35, 47);

	private List<Integer> list7s = Arrays.asList(0, 8, 16, 24);
	private List<Integer> list10s = Arrays.asList(7, 15, 23, 31, 3, 11, 19, 27);

	public Resulthandler(Game game, int roundCounter, List<Player> players) {
		this.game = game;
		this.roundCounter = roundCounter;
		this.players = players;

		if (roundCounter != 10) {
			if (isBetli() && !isBetliSuccess()) {
				game.setGameOver(true);
				resultList.add(addResult(false, getRespCallId(listBetli)));
				game.setResultList(resultList);
			} else if (isTeritettBetli() && !isBetliSuccess()) {
				game.setGameOver(true);
				resultList.add(addResult(false, getRespCallId(listTerBetli)));
				game.setResultList(resultList);
			} else if (isSzintelenDuri() && !isSzintelenDuriSuccess()) {
				game.setGameOver(true);
				resultList.add(addResult(false, getRespCallId(listSzDuri)));
				game.setResultList(resultList);
			} else if (isTeritettSzintelenDuri() && !isSzintelenDuriSuccess()) {
				game.setGameOver(true);
				resultList.add(addResult(false, getRespCallId(listTerSzDuri)));
				game.setResultList(resultList);
			}

		} else {
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
				checkCsendesUlti();
			} else if (isTeritettDuri() && is40100()) {
				proccessTerDuri();
				proccess40100();
			} else if (isTeritettDuri() && isUlti()) {
				proccessTerDuri();
				proccessUlti();
				proccessPassz(false);
			} else if (isDuri() && is20100()) {
				proccessDuri();
				proccess20100();
				checkCsendesUlti();
			} else if (isDuri() && is40100()) {
				proccessDuri();
				proccess40100();
				checkCsendesUlti();
			} else if (isDuri() && isUlti()) {
				proccessDuri();
				proccessUlti();
				proccessPassz(false);
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
				proccessPassz(false);
				checkCsendesUlti();
			} else if (isTeritettBetli()) {
				resultList.add(addResult(isBetliSuccess(), getRespCallId(listTerBetli)));
			} else if (is20100()) {
				proccess20100();
				checkCsendesUlti();
			} else if (isSzintelenDuri()) {
				resultList.add(addResult(isSzintelenDuriSuccess(), getRespCallId(listSzDuri)));
			} else if (isDuri()) {
				proccessDuri();
				proccessPassz(false);
				checkCsendesUlti();
			} else if (isBetli()) {
				resultList.add(addResult(isBetliSuccess(), getRespCallId(listBetli)));
			} else if (isUlti()) {
				proccessUlti();
			} else if (is40100()) {
				proccess40100();
				checkCsendesUlti();
			} else if (isPassz()) {
				proccessPassz(true);
				checkCsendesUlti();
			}

			game.setGameOver(true);
			game.setResultList(resultList);

			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getId() == game.getLastCallerId()) {
					List<StrikeList> strikeList = new ArrayList<StrikeList>();

					strikeList.add(0, new StrikeList(players.get(i).getId(), players.get(i).getStrikes()));
					strikeList.add(1, new StrikeList(players.get(getIncreasedId(i + 1)).getId(),
							players.get(getIncreasedId(i + 1)).getStrikes()));
					strikeList.add(2, new StrikeList(players.get(getIncreasedId(i + 2)).getId(),
							players.get(getIncreasedId(i + 2)).getStrikes()));

					game.setStrikeList(strikeList);
				}
			}
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
				if (game.getPreviousCall().get(j).getCallId() == list.get(i))
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

			if (player.getStrikes().get(i).getRound() == 10)
				value += 10;
		}

		return value;
	}

	private int getRespCallId(List<Integer> list) {

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < game.getPreviousCall().size(); j++) {
				if (list.get(i) == game.getPreviousCall().get(j).getCallId())
					return list.get(i);
			}
		}

		return 0;
	}

	private void proccessPassz(boolean isPassz) {

		List<Integer> s10 = Arrays.asList(0, 0, 0);
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isPlaying())
				s10.set(i, get10Value(players.get(i)));
		}

		int talon10s = 0;

		int sum = 0;

		for (int i = 0; i < s10.size(); i++) {
			sum += s10.get(i);
		}

		if (sum != 90)
			talon10s = 90 - sum;

		for (int i = 0; i < game.getSays().size(); i++) {
			for (int j = 0; j < players.size(); j++) {
				if (game.getSays().get(i).getPlayerId() == players.get(j).getId()) {
					if (game.getSays().get(i).isHave40()) {
						s10.set(j, s10.get(j) + 40);
					}

					if (game.getSays().get(i).isHave120()) {
						s10.set(j, s10.get(j) + 20);
					}

					if (game.getSays().get(i).isHave220()) {
						s10.set(j, s10.get(j) + 40);
					}

					if (game.getSays().get(i).isHave320()) {
						s10.set(j, s10.get(j) + 60);
					}
				}
			}
		}

		int caller10s = 0;
		int others10s = 0;

		for (int j = 0; j < players.size(); j++) {
			if (game.getLastCallerId() == players.get(j).getId()) {
				caller10s = s10.get(j);
				others10s = s10.get(getIncreasedId(j + 1)) + s10.get(getIncreasedId(j + 2)) + talon10s;
				break;
			}
		}

		if (isPassz) {
			if (caller10s >= 100 || others10s >= 100) {
				resultList.add(addResult(caller10s > others10s, getCsendesSzazId(), caller10s + " - " + others10s));
			} else {
				resultList
						.add(addResult(caller10s > others10s, getRespCallId(listPassz), caller10s + " - " + others10s));
			}
		} else {
			if (caller10s >= 100 || others10s >= 100) {
				resultList.add(addResult(caller10s > others10s, getCsendesSzazId(), caller10s + " - " + others10s));
			}
		}
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
			proccessPassz(true);
		}

		resultList.add(addResult(isUltiSuccess(), getRespCallId(listUlti)));
	}

	private boolean isUltiSuccess() {
		boolean isSuccess = false;
		Player player = UltiController.getPlayerById(game.getLastCallerId());

		for (int i = 0; i < player.getStrikes().size(); i++) {
			if (player.getStrikes().get(i).getRound() == 10) {

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
					int adu = StrikeHandler.getColor(game.getPreviousCall().get(0).getCallId());

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
		int adu = StrikeHandler.getColor(game.getPreviousCall().get(0).getCallId());

		for (int i = 0; i < list7s.size(); i++) {
			if (card == list7s.get(i) && color == adu) {
				return true;
			}
		}

		return false;
	}

	private void checkCsendesUlti() {

		Strike lastStrike = null;

		for (int j = 0; j < players.size(); j++) {
			for (int i = 0; i < players.get(j).getStrikes().size(); i++) {
				if (players.get(j).getStrikes().get(i).getRound() == 10) {
					lastStrike = players.get(j).getStrikes().get(i);
				}
			}
		}

		if (hasAdu7(lastStrike.getCard1Id()) || hasAdu7(lastStrike.getCard2Id()) || hasAdu7(lastStrike.getCard3Id())) {

			int card1 = lastStrike.getCard1Id();
			int card2 = lastStrike.getCard2Id();
			int card3 = lastStrike.getCard3Id();
			int card1Color = StrikeHandler.getColor(card1);
			int card2Color = StrikeHandler.getColor(card2);
			int card3Color = StrikeHandler.getColor(card3);
			int aduCounter = 0;
			int adu = StrikeHandler.getColor(game.getPreviousCall().get(0).getCallId());

			if (card1Color == adu)
				aduCounter++;
			if (card2Color == adu)
				aduCounter++;
			if (card3Color == adu)
				aduCounter++;

			if (aduCounter == 1) {
				int playerId = getPlayer7(lastStrike);

				for (int i = 0; i < players.size(); i++) {
					if (players.get(i).getId() == playerId)
						resultList.add(addResult(true, getCsendesUltiId(), "nyert: " + players.get(i).getId()));
				}
			} else {
				int playerId = getPlayer7(lastStrike);

				for (int i = 0; i < players.size(); i++) {
					if (players.get(i).getId() == playerId)
						resultList.add(addResult(false, getCsendesUltiId(), "bukta: " + players.get(i).getId()));
				}
			}
		}
	}

	private int getPlayer7(Strike lastStrike) {

		if (list7s.contains(lastStrike.getCard1Id()))
			return lastStrike.getCard1PlayerId();
		else if (list7s.contains(lastStrike.getCard2Id()))
			return lastStrike.getCard2PlayerId();
		else if (list7s.contains(lastStrike.getCard3Id()))
			return lastStrike.getCard3PlayerId();

		return 0;
	}

	private int getCsendesSzazId() {
		int color = StrikeHandler.getAdu(game.getPreviousCall().get(0).getCallId());

		if (color == Constants.MAKK_ID)
			return listCsendesSzaz.get(0);
		else if (color == Constants.ZOLD_ID)
			return listCsendesSzaz.get(1);
		else if (color == Constants.TOK_ID)
			return listCsendesSzaz.get(2);
		else if (color == Constants.PIROS_ID)
			return listCsendesSzaz.get(3);

		return 0;
	}

	private int getCsendesUltiId() {
		int color = StrikeHandler.getAdu(game.getPreviousCall().get(0).getCallId());

		if (color == Constants.MAKK_ID)
			return listCsendesUlti.get(0);
		else if (color == Constants.ZOLD_ID)
			return listCsendesUlti.get(1);
		else if (color == Constants.TOK_ID)
			return listCsendesUlti.get(2);
		else if (color == Constants.PIROS_ID)
			return listCsendesUlti.get(3);

		return 0;
	}

	private int getIncreasedId(int id) {
		if (id == 3)
			return 0;
		else if (id == 4)
			return 1;

		return id;
	}
}
