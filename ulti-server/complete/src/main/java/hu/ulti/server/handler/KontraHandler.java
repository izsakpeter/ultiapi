package hu.ulti.server.handler;

import java.util.List;

import hu.ulti.server.Helper;
import hu.ulti.server.model.Call;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Say;
import hu.ulti.server.model.SayMsg;

public class KontraHandler {

	public static List<Call> kontraHandler(Say say, Game game) {
		game.setKontraPartFinished(false);
		boolean isSzintelen = Helper.isSzintelenByList(game.getPreviousCall());

		if (isSzintelen) {
			for (int i = 0; i < game.getPreviousCall().size(); i++) {
				if (Resulthandler.listBetli.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, true, say.isKontraBetli());
				} else if (Resulthandler.listTerBetli.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, true, say.isKontraBetliTer());
				} else if (Resulthandler.listSzDuri.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, true, say.isKontraDuriSz());
				} else if (Resulthandler.listTerSzDuri.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, true, say.isKontraDuriTerSz());
				}
			}
		} else {
			
			for (int i = 0; i < game.getPreviousCall().size(); i++) {
				if (Resulthandler.listPassz.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, false, say.isKontraPassz());
				} else if (Resulthandler.list40100.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, false, say.isKontra40100());
				} else if (Resulthandler.listUlti.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, false, say.isKontraUlti());
				} else if (Resulthandler.listDuri.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, false, say.isKontraDuri());
				} else if (Resulthandler.list20100.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, false, say.isKontra20100());
				} else if (Resulthandler.listTerDuri.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, false, say.isKontraDuriTer());
				}
			}
		}

		return game.getPreviousCall();
	}

	private static Game processKontrazasok(Game game, Say say, int callIndex, boolean isSzintelen, boolean isKontra) {

		if (say.getKontraId() == 1 && isKontra) {
			return proccessKontra(game, say.getPlayerId(), callIndex, isSzintelen);
		} else if (say.getKontraId() == 2) {
			return proccessRekontra(game, say.getPlayerId(), callIndex, isKontra);
		} else if (say.getKontraId() == 3) {
			return proccessSzupkontra(game, say.getPlayerId(), callIndex, isKontra, isSzintelen);
		} else if (say.getKontraId() == 4) {
			return proccessSzuprekontra(game, say.getPlayerId(), callIndex, isKontra, isSzintelen);
		} else if (say.getKontraId() == 5) {
			return proccessMaxkontra(game, say.getPlayerId(), callIndex, isKontra, isSzintelen);
		}

		return null;
	}

	private static Game proccessKontra(Game game, int playerId, int i, boolean isSzintelen) {
		for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
			if (isSzintelen) {
				if (playerId == game.getPreviousCall().get(i).getKontra().get(j).getPlayerId()) {
					game.getPreviousCall().get(i).getKontra().get(j).getKontra().setSaid(true);
					game.addSayMsgToList(new SayMsg(playerId, 1, game.getPreviousCall().get(i).getCallId()));
				}
			} else {
				if (playerId == game.getPreviousCall().get(i).getKontra().get(j).getPlayerId()) {
					game.getPreviousCall().get(i).getKontra().get(j).getKontra().setSaid(true);
					game.addSayMsgToList(new SayMsg(playerId, 1, game.getPreviousCall().get(i).getCallId()));
				}
			}
		}

		return game;
	}

	private static Game proccessRekontra(Game game, int playerId, int i, boolean isRekontra) {
		
		if (isRekontra) {
			for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
				if (game.getPreviousCall().get(i).getKontra().get(j).getKontra().isSaid()) {
					game.getPreviousCall().get(i).getKontra().get(j).getKontra().setAckBy(playerId);
					game.getPreviousCall().get(i).getKontra().get(j).getRekontra().setSaid(true);
					game.getPreviousCall().get(i).getKontra().get(j).getRekontra().setAckBy(playerId);// kuka lesz
					game.addSayMsgToList(new SayMsg(playerId, 2, game.getPreviousCall().get(i).getCallId()));
					game.addSayMsgToList(new SayMsg(playerId, 2, game.getPreviousCall().get(i).getCallId(), "ok")); // kuka lesz
					game.setKontraPartFinished(true); // kuka lesz
				}
			}
		} else {
			for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
				if (game.getPreviousCall().get(i).getKontra().get(j).getKontra().isSaid()) {
					game.getPreviousCall().get(i).getKontra().get(j).getKontra().setAckBy(playerId);
					game.addSayMsgToList(new SayMsg(playerId, 1, game.getPreviousCall().get(i).getCallId(), "ok"));
					game.setKontraPartFinished(true);
				}
			}
		}

		return game;
	}

	private static Game proccessSzupkontra(Game game, int playerId, int i, boolean isSzupkontra, boolean isSzintelen) {
		if (isSzupkontra) {
			for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
				if (isSzintelen) {
					if (playerId == game.getPreviousCall().get(i).getKontra().get(j).getPlayerId()) {
						game.getPreviousCall().get(i).getKontra().get(j).getRekontra().setAckBy(playerId);
						game.getPreviousCall().get(i).getKontra().get(j).getSzupKontra().setSaid(true);
						game.addSayMsgToList(new SayMsg(playerId, 3, game.getPreviousCall().get(i).getCallId()));
						break;
					}
				} else {
					game.getPreviousCall().get(i).getKontra().get(j).getRekontra().setAckBy(playerId);
					game.getPreviousCall().get(i).getKontra().get(j).getSzupKontra().setSaid(true);
					game.addSayMsgToList(new SayMsg(playerId, 3, game.getPreviousCall().get(i).getCallId()));
					game.setKontraPartFinished(true);
				}
			}
		} else {
			for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
				if (isSzintelen) {
					if (playerId == game.getPreviousCall().get(i).getKontra().get(j).getPlayerId()) {
						game.getPreviousCall().get(i).getKontra().get(j).getRekontra().setAckBy(playerId);
						game.addSayMsgToList(new SayMsg(playerId, 2, game.getPreviousCall().get(i).getCallId(), "ok"));
						break;
					}
				} else {
					game.getPreviousCall().get(i).getKontra().get(j).getRekontra().setAckBy(playerId);
					game.addSayMsgToList(new SayMsg(playerId, 2, game.getPreviousCall().get(i).getCallId(), "ok"));
					game.setKontraPartFinished(true);
				}
			}
		}

		return game;
	}

	private static Game proccessSzuprekontra(Game game, int playerId, int i, boolean isSzuprekontra,
			boolean isSzintelen) {

		if (isSzuprekontra) {
			for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
				if (isSzintelen) {
					if (playerId == game.getPreviousCall().get(i).getKontra().get(j).getPlayerId()) {
						game.getPreviousCall().get(i).getKontra().get(j).getSzupKontra().setAckBy(playerId);
						game.getPreviousCall().get(i).getKontra().get(j).getSzupRekontra().setSaid(true);
						break;
					}
				} else {
					game.getPreviousCall().get(i).getKontra().get(j).getSzupKontra().setAckBy(playerId);
					game.getPreviousCall().get(i).getKontra().get(j).getSzupRekontra().setSaid(true);
					game.setKontraPartFinished(true);
				}
			}
		} else {
			for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
				if (isSzintelen) {
					if (playerId == game.getPreviousCall().get(i).getKontra().get(j).getPlayerId()) {
						game.getPreviousCall().get(i).getKontra().get(j).getSzupKontra().setAckBy(playerId);
						break;
					}
				} else {
					game.getPreviousCall().get(i).getKontra().get(j).getSzupKontra().setAckBy(playerId);
					game.setKontraPartFinished(true);
				}
			}
		}

		return game;
	}

	private static Game proccessMaxkontra(Game game, int playerId, int i, boolean isMaxkontra, boolean isSzintelen) {

		if (isMaxkontra) {
			for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
				if (isSzintelen) {
					if (playerId == game.getPreviousCall().get(i).getKontra().get(j).getPlayerId()) {
						game.getPreviousCall().get(i).getKontra().get(j).getSzupKontra().setAckBy(playerId);
						game.getPreviousCall().get(i).getKontra().get(j).getMaxKontra().setSaid(true);
						break;
					}
				} else {
					game.getPreviousCall().get(i).getKontra().get(j).getSzupKontra().setAckBy(playerId);
					game.getPreviousCall().get(i).getKontra().get(j).getMaxKontra().setSaid(true);
				}
			}
		} else {
			for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
				if (isSzintelen) {
					if (playerId == game.getPreviousCall().get(i).getKontra().get(j).getPlayerId()) {
						game.getPreviousCall().get(i).getKontra().get(j).getSzupKontra().setAckBy(playerId);
						break;
					}
				} else {
					game.getPreviousCall().get(i).getKontra().get(j).getSzupKontra().setAckBy(playerId);
				}
			}
		}

		game.setKontraPartFinished(true);
		return game;
	}
}
