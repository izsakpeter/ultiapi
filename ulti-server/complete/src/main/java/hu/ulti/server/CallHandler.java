package hu.ulti.server;

import java.util.List;

import hu.ulti.server.model.CallWithValue;
import hu.ulti.server.model.Game;

public class CallHandler {

	public static boolean callChecker(Game game, boolean isColorForced) {
		boolean res = true;

		if (isColorForced) {
			int forcedColorId = game.getStartingValue();

			for (Integer callId : game.getCall()) {
				if ((forcedColorId == Constants.MAKK_ID && callId > 11)
						|| (forcedColorId == Constants.ZOLD_ID && (callId > 23 || callId < 12))
						|| (forcedColorId == Constants.TOK_ID && (callId > 35 || callId < 24))
						|| (forcedColorId == Constants.PIROS_ID && callId < 36))
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
						&& game.getPreviousCall().get(0) == 2 && game.getCall().get(0) == 30)
					return false;
			}
		}

		return res;
	}

	private static int getCallValue(List<Integer> call) {

		int szum = 0;

		List<CallWithValue> allCalls = CallWithValue.getAllCalls();

		for (Integer callId : call) {
			for (CallWithValue allCall : allCalls) {
				if (allCall.getId() == callId)
					szum += allCall.getValue();
			}
		}

		return szum;
	}
}
