package hu.ulti.server.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.ulti.server.Constants;
import hu.ulti.server.Helper;
import hu.ulti.server.controller.UltiController;
import hu.ulti.server.model.Call;
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
	
	
	
	
	
	public static List<Integer> listDuri = Arrays.asList(6, 18, 30, 42);
	public static List<Integer> listSzDuri = Arrays.asList(7, 19, 31, 43);
	public static List<Integer> list20100 = Arrays.asList(8, 20, 32, 44);
	public static List<Integer> listTerBetli = Arrays.asList(9, 21, 33, 45);
	public static List<Integer> listTerDuri = Arrays.asList(10, 22, 34, 46);
	public static List<Integer> listTerSzDuri = Arrays.asList(11, 23, 35, 47);

	private List<Integer> list10s = Arrays.asList(7, 15, 23, 31, 3, 11, 19, 27);

	public Resulthandler(Game game, int roundCounter, List<Player> players) {
		this.game = game;
		this.roundCounter = roundCounter;
		this.players = players;

		if (roundCounter != 10) {
			if (isBetli() && !isBetliSuccess()) {
				game.setGameOver(true);
				resultList.add(addResult(false, getRespCallId(Constants.CALL_BETLI)));
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
				resultList.add(addResult(isBetliSuccess(), getRespCallId(Constants.CALL_BETLI)));
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
			game.setScores(ScoreHandler.setScores(game, Helper.getPlayers(players)));
			game.setStrikeList(getStrikeList());
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
		return addResult(isSuccess, callIndex, comment, game.getLastCallerId());
	}

	private Result addResult(boolean isSuccess, int callIndex, String comment, int playerId) {
		return new Result(Helper.getUUid().toString(), playerId, callIndex, isSuccess, comment);
	}

	private boolean isPassz() {
		return isListItemInList(Constants.CALL_PASSZ, game.getPreviousCall());
	}

	private boolean is40100() {
		return isListItemInList(Constants.CALL_40100, game.getPreviousCall());
	}

	private boolean isUlti() {
		return isListItemInList(Constants.CALL_ULTI, game.getPreviousCall());
	}

	private boolean isBetli() {
		return isListItemInList(Constants.CALL_BETLI, game.getPreviousCall());
	}

	private boolean isDuri() {
		return isListItemInList(listDuri, game.getPreviousCall());
	}

	private boolean isSzintelenDuri() {
		return isListItemInList(listSzDuri, game.getPreviousCall());
	}

	private boolean is20100() {
		return isListItemInList(list20100, game.getPreviousCall());
	}

	private boolean isTeritettBetli() {
		return isListItemInList(listTerBetli, game.getPreviousCall());
	}

	private boolean isTeritettDuri() {
		return isListItemInList(listTerDuri, game.getPreviousCall());
	}

	private boolean isTeritettSzintelenDuri() {
		return isListItemInList(listTerSzDuri, game.getPreviousCall());
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
		Map<Integer, Integer> s10Map = new HashMap<>();
		int talon10s = 0;
		int sum = 0;

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isPlaying()) {
				s10Map.put(players.get(i).getId(), get10Value(players.get(i)));
				sum += get10Value(players.get(i));
			}
		}

		if (sum != 90)
			talon10s = 90 - sum;

		for (int i = 0; i < game.getSays().size(); i++) {
			for (int j = 0; j < players.size(); j++) {
				if (game.getSays().get(i).getPlayerId() == players.get(j).getId()) {
					if (game.getSays().get(i).isHave40()) {
						s10Map.put(players.get(j).getId(), s10Map.get(players.get(j).getId()) + 40);
					}

					if (game.getSays().get(i).isHave120()) {
						s10Map.put(players.get(j).getId(), s10Map.get(players.get(j).getId()) + 20);
					}

					if (game.getSays().get(i).isHave220()) {
						s10Map.put(players.get(j).getId(), s10Map.get(players.get(j).getId()) + 40);
					}

					if (game.getSays().get(i).isHave320()) {
						s10Map.put(players.get(j).getId(), s10Map.get(players.get(j).getId()) + 60);
					}
				}
			}
		}

		int caller10s = 0;
		int others10s = 0;

		for (int j = 0; j < players.size(); j++) {
			if (players.get(j).isPlaying()) {
				if (game.getLastCallerId() == players.get(j).getId()) {
					caller10s = s10Map.get(players.get(j).getId());
				} else {
					others10s += s10Map.get(players.get(j).getId());
				}
			}
		}

		others10s += talon10s;

		if (isPassz) {
			if (caller10s >= 100 || others10s >= 100) {
				resultList.add(addResult(caller10s > others10s, getCsendesSzazId(), caller10s + " - " + others10s));
			} else {
				resultList
						.add(addResult(caller10s > others10s, getRespCallId(Constants.CALL_PASSZ), caller10s + " - " + others10s));
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

		resultList.add(addResult(caller10Value >= 100, getRespCallId(Constants.CALL_40100), String.valueOf(caller10Value)));
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

		resultList.add(addResult(isUltiSuccess(), getRespCallId(Constants.CALL_ULTI)));
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
					int card2Color = Helper.getColorId(card2);
					int card3Color = Helper.getColorId(card3);
					int adu = Helper.getAduByCall(game.getPreviousCall().get(0).getCallId());

					if (adu == card2Color || adu == card3Color)
						return false;

					return true;
				}
			}
		}

		return isSuccess;
	}

	private boolean hasAdu7(int card) {

		int color = Helper.getColorId(card);
		int adu = Helper.getAduByCall(game.getPreviousCall().get(0).getCallId());

		for (int i = 0; i < Constants.CARD_7LIST.size(); i++) {
			if (card == Constants.CARD_7LIST.get(i) && color == adu) {
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
			int card1Color = Helper.getColorId(card1);
			int card2Color = Helper.getColorId(card2);
			int card3Color = Helper.getColorId(card3);
			int aduCounter = 0;
			int adu = Helper.getColorId(game.getPreviousCall().get(0).getCallId());

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
						resultList.add(addResult(true, getCsendesUltiId(), "", players.get(i).getId()));
				}
			} else {
				int playerId = getPlayer7(lastStrike);

				for (int i = 0; i < players.size(); i++) {
					if (players.get(i).getId() == playerId)
						resultList.add(addResult(false, getCsendesUltiId(), "", players.get(i).getId()));
				}
			}
		}
	}

	private int getPlayer7(Strike lastStrike) {

		if (Constants.CARD_7LIST.contains(lastStrike.getCard1Id()))
			return lastStrike.getCard1PlayerId();
		else if (Constants.CARD_7LIST.contains(lastStrike.getCard2Id()))
			return lastStrike.getCard2PlayerId();
		else if (Constants.CARD_7LIST.contains(lastStrike.getCard3Id()))
			return lastStrike.getCard3PlayerId();

		return 0;
	}

	private int getCsendesSzazId() {
		int color = Helper.getAduByCall(game.getPreviousCall().get(0).getCallId());

		if (color == Constants.MAKK_ID)
			return Constants.CALL_CSENDES_SZAZ.get(0);
		else if (color == Constants.ZOLD_ID)
			return Constants.CALL_CSENDES_SZAZ.get(1);
		else if (color == Constants.TOK_ID)
			return Constants.CALL_CSENDES_SZAZ.get(2);
		else if (color == Constants.PIROS_ID)
			return Constants.CALL_CSENDES_SZAZ.get(3);

		return 0;
	}

	private int getCsendesUltiId() {
		int color = Helper.getAduByCall(game.getPreviousCall().get(0).getCallId());

		if (color == Constants.MAKK_ID)
			return Constants.CALL_CSENDES_ULTI.get(0);
		else if (color == Constants.ZOLD_ID)
			return Constants.CALL_CSENDES_ULTI.get(1);
		else if (color == Constants.TOK_ID)
			return Constants.CALL_CSENDES_ULTI.get(2);
		else if (color == Constants.PIROS_ID)
			return Constants.CALL_CSENDES_ULTI.get(3);

		return 0;
	}

	private List<StrikeList> getStrikeList() {
		List<StrikeList> strikeList = new ArrayList<StrikeList>();

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isPlaying()) {
				strikeList.add(new StrikeList(players.get(i).getId(), players.get(i).getStrikes()));
			}
		}

		return strikeList;
	}
	
	private boolean isListItemInList(List<Integer> list, List<Call> finalCall) {

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < finalCall.size(); j++) {
				if (finalCall.get(j).getCallId() == list.get(i))
					return true;
			}
		}

		return false;
	}
}
