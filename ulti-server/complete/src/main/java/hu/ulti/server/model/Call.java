package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

public class Call {
	
	private static final String MAKK = "makk";
	private static final String ZOLD = "zold";
	private static final String TOK = "tok";
	private static final String PIROS = "piros";
	
	private static final String PASSZ = "passz";
	private static final String SZAZ40 = "40szaz";
	private static final String ULTI = "ulti";
	private static final String BETLI = "betli";
	private static final String DURI = "duri";
	private static final String SZAZ20 = "20szaz";
	private static final String BETLI_TERITETT = "teritettbetli";
	private static final String DURI_TERITETT = "teritettduri";
	
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
	
	public Call(int id, String color, String call, int value) {
		this.id = id;
		this.color = color;
		this.call = call;
		this.value = value;
	}
	
	public static List<Call> getAllCalls(){
		List<Call> calls = new ArrayList<Call>();
		
		calls.add(new Call(0, MAKK, PASSZ, PASSZ_VALUE));
		calls.add(new Call(1, MAKK, SZAZ40, SZAZ40_VALUE));
		calls.add(new Call(2, MAKK, ULTI, ULTI_VALUE));
		calls.add(new Call(3, MAKK, BETLI, BETLI_VALUE));
		calls.add(new Call(4, MAKK, DURI, DURI_VALUE));
		calls.add(new Call(5, MAKK, SZAZ20, SZAZ20_VALUE));
		calls.add(new Call(6, MAKK, BETLI_TERITETT, BETLI_TERITETT_VALUE));
		calls.add(new Call(7, MAKK, DURI_TERITETT, DURI_TERITETT_VALUE));
		
		calls.add(new Call(8, ZOLD, PASSZ, PASSZ_VALUE * 2));
		calls.add(new Call(9, ZOLD, SZAZ40, SZAZ40_VALUE * 2));
		calls.add(new Call(10, ZOLD, ULTI, ULTI_VALUE * 2));
		calls.add(new Call(11, ZOLD, BETLI, BETLI_VALUE * 2));
		calls.add(new Call(12, ZOLD, DURI, DURI_VALUE * 2 ));
		calls.add(new Call(13, ZOLD, SZAZ20, SZAZ20_VALUE * 1));
		calls.add(new Call(14, ZOLD, BETLI_TERITETT, BETLI_TERITETT_VALUE * 2));
		calls.add(new Call(15, ZOLD, DURI_TERITETT, DURI_TERITETT_VALUE * 2));
		
		calls.add(new Call(16, TOK, PASSZ, PASSZ_VALUE * 3));
		calls.add(new Call(17, TOK, SZAZ40, SZAZ40_VALUE * 3));
		calls.add(new Call(18, TOK, ULTI, ULTI_VALUE * 3));
		calls.add(new Call(19, TOK, BETLI, BETLI_VALUE * 3));
		calls.add(new Call(20, TOK, DURI, DURI_VALUE * 3));
		calls.add(new Call(21, TOK, SZAZ20, SZAZ20_VALUE * 3));
		calls.add(new Call(22, TOK, BETLI_TERITETT, BETLI_TERITETT_VALUE * 3));
		calls.add(new Call(23, TOK, DURI_TERITETT, DURI_TERITETT_VALUE * 3));
		
		calls.add(new Call(24, PIROS, PASSZ, PASSZ_VALUE * 4));
		calls.add(new Call(25, PIROS, SZAZ40, SZAZ40_VALUE * 4));
		calls.add(new Call(26, PIROS, ULTI, ULTI_VALUE * 4));
		calls.add(new Call(27, PIROS, BETLI, BETLI_VALUE * 4));
		calls.add(new Call(28, PIROS, DURI, DURI_VALUE * 4));
		calls.add(new Call(29, PIROS, SZAZ20, SZAZ20_VALUE * 4));
		calls.add(new Call(30, PIROS, BETLI_TERITETT, BETLI_TERITETT_VALUE * 4));
		calls.add(new Call(31, PIROS, DURI_TERITETT, DURI_TERITETT_VALUE * 4));
		
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
}
