package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

public class Call {

	public static final String MAKK = "makk";
	public static final String ZOLD = "zold";
	public static final String TOK = "tok";
	public static final String PIROS = "piros";

	public static final int MAKK_COLOR_ID = 1;
	public static final int ZOLD_COLOR_ID = 2;
	public static final int TOK_COLOR_ID = 3;
	public static final int PIROS_COLOR_ID = 4;

	public static final String PASSZ = "passz";
	public static final String SZAZ40 = "40szaz";
	public static final String ULTI = "ulti";
	public static final String BETLI = "betli";
	public static final String DURI_SZINES = "szinesduri";
	public static final String DURI_SZINTELEN = "szintelenduri";
	public static final String SZAZ20 = "20szaz";
	public static final String BETLI_TERITETT = "teritettbetli";
	public static final String DURI_SZINES_TERITETT = "teritettduri";
	public static final String DURI_SZINELEN_TERITETT = "teritettduri";

	public static final int PASSZ_VALUE = 1;
	public static final int SZAZ40_VALUE = 4;
	public static final int ULTI_VALUE = 4;
	public static final int BETLI_VALUE = 5;
	public static final int DURI_VALUE = 6;
	public static final int SZAZ20_VALUE = 8;
	public static final int BETLI_TERITETT_VALUE = 10;
	public static final int DURI_TERITETT_VALUE = 12;

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
