package hu.ulti.server;

import java.util.List;

import hu.ulti.server.model.Call;
import hu.ulti.server.model.Game;

public class CallHandler {

	public static boolean callChecker(Game game, boolean isColorForced) {
		boolean res = true;

		if (isColorForced) {
			int forcedColorId = game.getStartingValue();

			for (Integer callId : game.getCall()) {
				if ((forcedColorId == Call.MAKK_COLOR_ID && callId > 9)
						|| (forcedColorId == Call.ZOLD_COLOR_ID && (callId > 19 || callId < 10))
						|| (forcedColorId == Call.TOK_COLOR_ID && (callId > 29 || callId < 20))
						|| (forcedColorId == Call.PIROS_COLOR_ID && callId < 30))
					res = false;
			}
		} else {
			int prevCallValue = getCallValue(game.getPreviousCall());
			int callValue = getCallValue(game.getCall());

			System.out.println(prevCallValue + " prev ---------- call " + callValue);
			if (prevCallValue > callValue)
				return false;

			if (prevCallValue == callValue) {

				if (game.getPreviousCall().size() > game.getCall().size())
					return false;

				if (game.getPreviousCall().size() == 1 && game.getCall().size() == 1
						&& game.getPreviousCall().get(0) == 2 && game.getCall().get(0) == 30)
					return false;
			}
		}

		return res;
	}

	private static int getCallValue(List<Integer> call) {

		int szum = 0;

		List<Call> allCalls = Call.getAllCalls();

		for (Integer callId : call) {
			for (Call allCall : allCalls) {
				if (allCall.getId() == callId)
					szum += allCall.getValue();
			}
		}

		return szum;
	}
}