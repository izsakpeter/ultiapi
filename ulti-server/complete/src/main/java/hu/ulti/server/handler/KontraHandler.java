package hu.ulti.server.handler;

import java.util.List;

import hu.ulti.server.Helper;
import hu.ulti.server.model.Call;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Say;

public class KontraHandler {

	public static List<Call> kontraHandler(Say say, Game game) {

		boolean isSzintelen = Helper.isBetli(game.getPreviousCall()) || Helper.isSzintelenDuri(game.getPreviousCall());

		if (isSzintelen) {
			for (int i = 0; i < game.getPreviousCall().size(); i++) {
				if (Resulthandler.listBetli.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, true, say.isKontraBetli());
				} else if (Resulthandler.listTerBetli.contains(game.getPreviousCall().get(i).getCallId())) {
					processKontrazasok(game, say, i, true, say.isKontraDuriTerSz());
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

	private static Game processKontrazasok(Game game, Say say, int i, boolean isSzintelen, boolean isCallKontra) {

		if (say.getKontraId() == 1 && isCallKontra) {
			return proccessKontra(game, say, i, isSzintelen);

		} else if (say.getKontraId() == 2) {
			return proccessRekontra(game, say, i);
		}

		return null;
	}

	private static Game proccessKontra(Game game, Say say, int i, boolean isSzintelen) {
		for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
			if (isSzintelen) {
				if (say.getPlayerId() == game.getPreviousCall().get(i).getKontra().get(j).getPlayerId()) {
					game.getPreviousCall().get(i).getKontra().get(j).getKontra().setSaid(true);
					break;
				}
			} else {
				game.getPreviousCall().get(i).getKontra().get(j).getKontra().setSaid(true);
				break;
			}
		}

		return game;
	}

	private static Game proccessRekontra(Game game, Say say, int i) {
		if (say.isKontraBetli()) {
			for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
				if (game.getPreviousCall().get(i).getKontra().get(j).getKontra().isSaid()) {
					game.getPreviousCall().get(i).getKontra().get(j).getKontra().setAckBy(say.getPlayerId());
					game.getPreviousCall().get(i).getKontra().get(j).getRekontra().setSaid(true);
				}
			}
		} else {
			for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
				if (game.getPreviousCall().get(i).getKontra().get(j).getKontra().isSaid()) {
					game.getPreviousCall().get(i).getKontra().get(j).getKontra().setAckBy(say.getPlayerId());
				}
			}
		}

		return game;
	}
}
