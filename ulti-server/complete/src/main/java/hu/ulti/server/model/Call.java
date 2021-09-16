package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

public class Call {
	
	public static final int MAKK_ID = 1;
	public static final int ZOLD_ID = 2;
	public static final int TOK_ID = 3;
	public static final int PIROS_ID = 4;

	private static final int PASSZ_VALUE = 1;
	private static final int CSENDES_SZAZ_VALUE = 2;
	private static final int CSENDES_ULTI_VALUE = 2;
	private static final int SZAZ40_VALUE = 4;
	private static final int ULTI_VALUE = 4;
	private static final int BETLI_VALUE = 5;
	private static final int DURI_VALUE = 6;
	private static final int SZAZ20_VALUE = 8;
	private static final int BETLI_TERITETT_VALUE = 10;
	private static final int DURI_TERITETT_VALUE = 12;

	private int id;
	private int value;

	public Call(int id, int value) {
		this.id = id;
		this.value = value;
	}

	public static List<Call> getAllCalls() {
		List<Call> calls = new ArrayList<Call>();

		calls.add(new Call(0, PASSZ_VALUE * MAKK_ID));
		calls.add(new Call(1, CSENDES_SZAZ_VALUE * MAKK_ID));
		calls.add(new Call(2, CSENDES_ULTI_VALUE * MAKK_ID));
		calls.add(new Call(3, SZAZ40_VALUE * MAKK_ID));
		calls.add(new Call(4, ULTI_VALUE * MAKK_ID));
		calls.add(new Call(5, BETLI_VALUE * MAKK_ID));
		calls.add(new Call(6, DURI_VALUE * MAKK_ID));
		calls.add(new Call(7, DURI_VALUE * MAKK_ID));
		calls.add(new Call(8, SZAZ20_VALUE * MAKK_ID));
		calls.add(new Call(9, BETLI_TERITETT_VALUE * MAKK_ID));
		calls.add(new Call(10, DURI_TERITETT_VALUE * MAKK_ID));
		calls.add(new Call(11, DURI_TERITETT_VALUE * MAKK_ID));

		calls.add(new Call(12, PASSZ_VALUE * ZOLD_ID));
		calls.add(new Call(13, CSENDES_SZAZ_VALUE * ZOLD_ID));
		calls.add(new Call(14, CSENDES_ULTI_VALUE * ZOLD_ID));
		calls.add(new Call(15, SZAZ40_VALUE * ZOLD_ID));
		calls.add(new Call(16, ULTI_VALUE * ZOLD_ID));
		calls.add(new Call(17, BETLI_VALUE * ZOLD_ID));
		calls.add(new Call(18, DURI_VALUE * ZOLD_ID));
		calls.add(new Call(19, DURI_VALUE * ZOLD_ID));
		calls.add(new Call(20, SZAZ20_VALUE * ZOLD_ID));
		calls.add(new Call(21, BETLI_TERITETT_VALUE * ZOLD_ID));
		calls.add(new Call(22, DURI_TERITETT_VALUE * ZOLD_ID));
		calls.add(new Call(23, DURI_TERITETT_VALUE * ZOLD_ID));

		calls.add(new Call(24, PASSZ_VALUE * TOK_ID));
		calls.add(new Call(25, CSENDES_SZAZ_VALUE * TOK_ID));
		calls.add(new Call(26, CSENDES_ULTI_VALUE * TOK_ID));
		calls.add(new Call(27, SZAZ40_VALUE * TOK_ID));
		calls.add(new Call(28, ULTI_VALUE * TOK_ID));
		calls.add(new Call(29, BETLI_VALUE * TOK_ID));
		calls.add(new Call(30, DURI_VALUE * TOK_ID));
		calls.add(new Call(31, DURI_VALUE * TOK_ID));
		calls.add(new Call(32, SZAZ20_VALUE * TOK_ID));
		calls.add(new Call(33, BETLI_TERITETT_VALUE * TOK_ID));
		calls.add(new Call(34, DURI_TERITETT_VALUE * TOK_ID));
		calls.add(new Call(35, DURI_TERITETT_VALUE * TOK_ID));

		calls.add(new Call(36, PASSZ_VALUE * PIROS_ID));
		calls.add(new Call(37, CSENDES_SZAZ_VALUE * PIROS_ID));
		calls.add(new Call(38, CSENDES_ULTI_VALUE * PIROS_ID));
		calls.add(new Call(39, SZAZ40_VALUE * PIROS_ID));
		calls.add(new Call(40, ULTI_VALUE * PIROS_ID));
		calls.add(new Call(41, BETLI_VALUE * PIROS_ID));
		calls.add(new Call(42, DURI_VALUE * PIROS_ID));
		calls.add(new Call(43, DURI_VALUE * PIROS_ID));
		calls.add(new Call(44, SZAZ20_VALUE * PIROS_ID));
		calls.add(new Call(45, BETLI_TERITETT_VALUE * PIROS_ID));
		calls.add(new Call(46, DURI_TERITETT_VALUE * PIROS_ID));
		calls.add(new Call(47, DURI_TERITETT_VALUE * PIROS_ID));

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
	
	public static boolean isTeritett(List<Integer> previousCall) {
		List<Integer> teritettIds = new ArrayList<>();
		teritettIds.add(7);
		teritettIds.add(8);
		teritettIds.add(9);
		teritettIds.add(17);
		teritettIds.add(18);
		teritettIds.add(19);
		teritettIds.add(27);
		teritettIds.add(28);
		teritettIds.add(29);
		teritettIds.add(37);
		teritettIds.add(38);
		teritettIds.add(39);
		
		for (Integer call : previousCall) {
			for (Integer teritettId : teritettIds) {
				if (call == teritettId)
					return true;
			}
		}
		return false;
	}
}
