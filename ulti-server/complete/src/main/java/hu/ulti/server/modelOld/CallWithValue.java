package hu.ulti.server.modelOld;

import java.util.ArrayList;
import java.util.List;

import hu.ulti.server.Constants;

public class CallWithValue {
	
	private int id;
	private int value;

	public CallWithValue(int id, int value) {
		this.id = id;
		this.value = value;
	}

	public static List<CallWithValue> getAllCalls() {
		List<CallWithValue> calls = new ArrayList<CallWithValue>();

		calls.add(new CallWithValue(0, Constants.PASSZ_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(1, Constants.CSENDES_SZAZ_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(2, Constants.CSENDES_ULTI_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(3, Constants.SZAZ40_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(4, Constants.ULTI_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(5, Constants.BETLI_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(6, Constants.DURI_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(7, Constants.DURI_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(8, Constants.SZAZ20_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(9, Constants.BETLI_TERITETT_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(10, Constants.DURI_TERITETT_VALUE * Constants.MAKK_ID));
		calls.add(new CallWithValue(11, Constants.DURI_TERITETT_VALUE * Constants.MAKK_ID));

		calls.add(new CallWithValue(12, Constants.PASSZ_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(13, Constants.CSENDES_SZAZ_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(14, Constants.CSENDES_ULTI_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(15, Constants.SZAZ40_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(16, Constants.ULTI_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(17, Constants.BETLI_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(18, Constants.DURI_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(19, Constants.DURI_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(20, Constants.SZAZ20_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(21, Constants.BETLI_TERITETT_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(22, Constants.DURI_TERITETT_VALUE * Constants.ZOLD_ID));
		calls.add(new CallWithValue(23, Constants.DURI_TERITETT_VALUE * Constants.ZOLD_ID));

		calls.add(new CallWithValue(24, Constants.PASSZ_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(25, Constants.CSENDES_SZAZ_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(26, Constants.CSENDES_ULTI_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(27, Constants.SZAZ40_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(28, Constants.ULTI_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(29, Constants.BETLI_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(30, Constants.DURI_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(31, Constants.DURI_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(32, Constants.SZAZ20_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(33, Constants.BETLI_TERITETT_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(34, Constants.DURI_TERITETT_VALUE * Constants.TOK_ID));
		calls.add(new CallWithValue(35, Constants.DURI_TERITETT_VALUE * Constants.TOK_ID));

		calls.add(new CallWithValue(36, Constants.PASSZ_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(37, Constants.CSENDES_SZAZ_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(38, Constants.CSENDES_ULTI_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(39, Constants.SZAZ40_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(40, Constants.ULTI_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(41, Constants.BETLI_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(42, Constants.DURI_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(43, Constants.DURI_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(44, Constants.SZAZ20_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(45, Constants.BETLI_TERITETT_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(46, Constants.DURI_TERITETT_VALUE * Constants.PIROS_ID));
		calls.add(new CallWithValue(47, Constants.DURI_TERITETT_VALUE * Constants.PIROS_ID));

		return calls;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
