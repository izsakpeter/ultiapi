package hu.ulti.server.handler;

import java.util.ArrayList;
import java.util.List;

import hu.ulti.server.Constants;
import hu.ulti.server.model.Call;
import hu.ulti.server.model.CallWithValue;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Kontra;
import hu.ulti.server.model.KontraAck;
import hu.ulti.server.model.Player;

public class CallHandler {

	public static boolean callChecker(Game game, boolean isColorForced) {
		boolean res = true;

		if (isColorForced) {
			int forcedColorId = game.getStartingValue();

			for (int i = 0; i < game.getCall().size(); i++) {
				if ((forcedColorId == Constants.MAKK_ID && game.getCall().get(i).getCallId() > 11)
						|| (forcedColorId == Constants.ZOLD_ID
								&& (game.getCall().get(i).getCallId() > 23 || game.getCall().get(i).getCallId() < 12))
						|| (forcedColorId == Constants.TOK_ID
								&& (game.getCall().get(i).getCallId() > 35 || game.getCall().get(i).getCallId() < 24))
						|| (forcedColorId == Constants.PIROS_ID && game.getCall().get(i).getCallId() < 36))
					res = false;
			}
		} else {
			int prevCallValue = getCallValue(game.getPreviousCall());
			int callValue = getCallValue(game.getCall());

			if (prevCallValue > callValue)
				return false;

			if (prevCallValue == callValue) {

				if (game.getPreviousCall().size() > game.getCall().size())
					return false;

				if (game.getPreviousCall().size() == 1 && game.getCall().size() == 1
						&& game.getPreviousCall().get(0).getCallId() == 2 && game.getCall().get(0).getCallId() == 30)
					return false;
			}
		}

		return res;
	}

	private static int getCallValue(List<Call> call) {

		int szum = 0;

		List<CallWithValue> allCalls = CallWithValue.getAllCalls();

		for (int i = 0; i < call.size(); i++) {
			for (CallWithValue allCall : allCalls) {
				if (allCall.getId() == call.get(i).getCallId())
					szum += allCall.getValue();
			}
		}

		return szum;
	}

	public static List<Call> getFinalCall(Game game, List<Player> players) {
		List<Call> finalCall = new ArrayList<Call>();

		for (int i = 0; i < game.getPreviousCall().size(); i++) {
			List<Kontra> kontraList = new ArrayList<Kontra>();
			for (int j = 0; j < players.size(); j++) {
				if (game.getLastCallerId() != players.get(j).getId()) {
					kontraList.add(new Kontra(players.get(j).getId(), new KontraAck(), new KontraAck(), new KontraAck(),
							new KontraAck(), new KontraAck()));
				}
			}

			finalCall.add(new Call(game.getPreviousCall().get(i).getCallId(), kontraList));
		}

		return finalCall;
	}
}