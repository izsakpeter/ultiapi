package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

public class Call {

	private static final String MAKK = "makk";
	private static final String ZOLD = "zold";
	private static final String TOK = "tok";
	private static final String PIROS = "piros";

	private static final int MAKK_COLOR_ID = 1;
	private static final int ZOLD_COLOR_ID = 2;
	private static final int TOK_COLOR_ID = 3;
	private static final int PIROS_COLOR_ID = 4;

	private static final String PASSZ = "passz";
	private static final String SZAZ40 = "40szaz";
	private static final String ULTI = "ulti";
	private static final String BETLI = "betli";
	private static final String DURI_SZINES = "szinesduri";
	private static final String DURI_SZINTELEN = "szintelenduri";
	private static final String SZAZ20 = "20szaz";
	private static final String BETLI_TERITETT = "teritettbetli";
	private static final String DURI_SZINES_TERITETT = "teritettduri";
	private static final String DURI_SZINELEN_TERITETT = "teritettduri";

	private static final int PASSZ_VALUE = 1;
	private static final int SZAZ40_VALUE = 4;
	private static final int ULTI_VALUE = 4;
	private static final int BETLI_VALUE = 5;
	private static final int DURI_VALUE = 6;
	private static final int SZAZ20_VALUE = 8;
	private static final int BETLI_TERITETT_VALUE = 10;
	private static final int DURI_TERITETT_VALUE = 12;

	private int id;
	private String color;
	private String call;
	private int value;
	private int colorId;

	public Call(int id, int colorId, String color, String call, int value) {
		this.id = id;
		this.colorId = colorId;
		this.color = color;
		this.call = call;
		this.value = value;
	}

	public static List<Call> getAllCalls() {
		List<Call> calls = new ArrayList<Call>();

		calls.add(new Call(0, MAKK_COLOR_ID, MAKK, PASSZ, PASSZ_VALUE));
		calls.add(new Call(1, MAKK_COLOR_ID, MAKK, SZAZ40, SZAZ40_VALUE));
		calls.add(new Call(2, MAKK_COLOR_ID, MAKK, ULTI, ULTI_VALUE));
		calls.add(new Call(3, MAKK_COLOR_ID, MAKK, BETLI, BETLI_VALUE));
		calls.add(new Call(4, MAKK_COLOR_ID, MAKK, DURI_SZINES, DURI_VALUE));
		calls.add(new Call(5, MAKK_COLOR_ID, MAKK, DURI_SZINTELEN, DURI_VALUE));
		calls.add(new Call(6, MAKK_COLOR_ID, MAKK, SZAZ20, SZAZ20_VALUE));
		calls.add(new Call(7, MAKK_COLOR_ID, MAKK, BETLI_TERITETT, BETLI_TERITETT_VALUE));
		calls.add(new Call(8, MAKK_COLOR_ID, MAKK, DURI_SZINES_TERITETT, DURI_TERITETT_VALUE));
		calls.add(new Call(9, MAKK_COLOR_ID, MAKK, DURI_SZINELEN_TERITETT, DURI_TERITETT_VALUE));

		calls.add(new Call(10, ZOLD_COLOR_ID, ZOLD, PASSZ, PASSZ_VALUE * 2));
		calls.add(new Call(11, ZOLD_COLOR_ID, ZOLD, SZAZ40, SZAZ40_VALUE * 2));
		calls.add(new Call(12, ZOLD_COLOR_ID, ZOLD, ULTI, ULTI_VALUE * 2));
		calls.add(new Call(13, ZOLD_COLOR_ID, ZOLD, BETLI, BETLI_VALUE * 2));
		calls.add(new Call(14, ZOLD_COLOR_ID, ZOLD, DURI_SZINES, DURI_VALUE * 2));
		calls.add(new Call(15, ZOLD_COLOR_ID, ZOLD, DURI_SZINTELEN, DURI_VALUE * 2));
		calls.add(new Call(16, ZOLD_COLOR_ID, ZOLD, SZAZ20, SZAZ20_VALUE * 1));
		calls.add(new Call(17, ZOLD_COLOR_ID, ZOLD, BETLI_TERITETT, BETLI_TERITETT_VALUE * 2));
		calls.add(new Call(18, ZOLD_COLOR_ID, ZOLD, DURI_SZINES_TERITETT, DURI_TERITETT_VALUE * 2));
		calls.add(new Call(19, ZOLD_COLOR_ID, ZOLD, DURI_SZINELEN_TERITETT, DURI_TERITETT_VALUE * 2));

		calls.add(new Call(20, TOK_COLOR_ID, TOK, PASSZ, PASSZ_VALUE * 3));
		calls.add(new Call(21, TOK_COLOR_ID, TOK, SZAZ40, SZAZ40_VALUE * 3));
		calls.add(new Call(22, TOK_COLOR_ID, TOK, ULTI, ULTI_VALUE * 3));
		calls.add(new Call(23, TOK_COLOR_ID, TOK, BETLI, BETLI_VALUE * 3));
		calls.add(new Call(24, TOK_COLOR_ID, TOK, DURI_SZINES, DURI_VALUE * 3));
		calls.add(new Call(25, TOK_COLOR_ID, TOK, DURI_SZINTELEN, DURI_VALUE * 3));
		calls.add(new Call(26, TOK_COLOR_ID, TOK, SZAZ20, SZAZ20_VALUE * 3));
		calls.add(new Call(27, TOK_COLOR_ID, TOK, BETLI_TERITETT, BETLI_TERITETT_VALUE * 3));
		calls.add(new Call(28, TOK_COLOR_ID, TOK, DURI_SZINES_TERITETT, DURI_TERITETT_VALUE * 3));
		calls.add(new Call(29, TOK_COLOR_ID, TOK, DURI_SZINELEN_TERITETT, DURI_TERITETT_VALUE * 3));

		calls.add(new Call(30, PIROS_COLOR_ID, PIROS, PASSZ, PASSZ_VALUE * 4));
		calls.add(new Call(31, PIROS_COLOR_ID, PIROS, SZAZ40, SZAZ40_VALUE * 4));
		calls.add(new Call(32, PIROS_COLOR_ID, PIROS, ULTI, ULTI_VALUE * 4));
		calls.add(new Call(33, PIROS_COLOR_ID, PIROS, BETLI, BETLI_VALUE * 4));
		calls.add(new Call(34, PIROS_COLOR_ID, PIROS, DURI_SZINES, DURI_VALUE * 4));
		calls.add(new Call(35, PIROS_COLOR_ID, PIROS, DURI_SZINTELEN, DURI_VALUE * 4));
		calls.add(new Call(36, PIROS_COLOR_ID, PIROS, SZAZ20, SZAZ20_VALUE * 4));
		calls.add(new Call(37, PIROS_COLOR_ID, PIROS, BETLI_TERITETT, BETLI_TERITETT_VALUE * 4));
		calls.add(new Call(38, PIROS_COLOR_ID, PIROS, DURI_SZINES_TERITETT, DURI_TERITETT_VALUE * 4));
		calls.add(new Call(39, PIROS_COLOR_ID, PIROS, DURI_SZINELEN_TERITETT, DURI_TERITETT_VALUE * 4));

		return calls;
	}

	public static boolean callChecker(Game game, List<Integer> calls, boolean isColorForced) {

		if (isColorForced) {
			int forcedColorId = game.getStartingValue();

			for (Integer callId : calls) {
				if (forcedColorId == MAKK_COLOR_ID && callId > 9)
					return false;
				else if (forcedColorId == ZOLD_COLOR_ID && (callId > 19 || callId < 10))
					return false;
				else if (forcedColorId == TOK_COLOR_ID && (callId > 29 || callId < 20))
					return false;
				else if (forcedColorId == PIROS_COLOR_ID && callId < 30)
					return false;
			}
		}

		int prevCallValue = getCallValue(game.getPreviousCall());
		int callValue = getCallValue(calls);

		if (prevCallValue > callValue)
			return false;

		if (prevCallValue == callValue) {

			if (game.getPreviousCall().size() > calls.size())
				return false;

			if (game.getPreviousCall().size() == 1 && calls.size() == 1 
					&& game.getPreviousCall().get(0) == 2 && calls.get(0) == 30)
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
		List<Call> allCalls = getAllCalls();

		for (Call call : allCalls) {
			for (Integer id : ids) {
				if (call.getValue() == id)
					calls.add(call);
			}
		}

		return calls;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
}
