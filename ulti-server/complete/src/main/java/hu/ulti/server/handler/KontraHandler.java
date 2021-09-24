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
				if (say.getKontraId() == 1 && say.isKontraBetli()
						&& Resulthandler.listBetli.contains(game.getPreviousCall().get(i).getCallId())) {
					for (int j = 0; j < game.getPreviousCall().get(i).getKontra().size(); j++) {
						if (say.getPlayerId() == game.getPreviousCall().get(i).getKontra().get(j).getPlayerId()) {
							game.getPreviousCall().get(i).getKontra().get(j).getKontra().setSaid(true);
							break;
						}
					}
				} else if (say.getKontraId() == 2 && Resulthandler.listBetli.contains(game.getPreviousCall().get(i).getCallId())) {
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
				}
			}
		} else {

		}

		return game.getPreviousCall();
	}

}
