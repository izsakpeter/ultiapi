package hu.ulti.server;

import java.util.ArrayList;
import java.util.List;

import hu.ulti.server.model.Call;
import hu.ulti.server.model.Game;

public class CallHandler {
	
	public static boolean callChecker(Game game, boolean isColorForced) {

		if (isColorForced) {
			int forcedColorId = game.getStartingValue();

			for (Integer callId : game.getCall()) {
				if (forcedColorId == Call.MAKK_COLOR_ID && callId > 9)
					return false;
				else if (forcedColorId == Call.ZOLD_COLOR_ID && (callId > 19 || callId < 10))
					return false;
				else if (forcedColorId == Call.TOK_COLOR_ID && (callId > 29 || callId < 20))
					return false;
				else if (forcedColorId == Call.PIROS_COLOR_ID && callId < 30)
					return false;
			}
		}

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

		return true;
	}

	private static int getCallValue(List<Integer> call) {
		int value = 0;

		List<Call> calls = getCallsById(call);

		for (Call call2 : calls) {
			value += call2.getValue();
		}

		return value;
	}

	public static List<Call> getCallsById(List<Integer> ids) {

		List<Call> calls = new ArrayList<Call>();
		List<Call> allCalls = Call.getAllCalls();

		for (Call call : allCalls) {
			for (Integer id : ids) {
				if (call.getValue() == id)
					calls.add(call);
			}
		}

		return calls;
	}


}
