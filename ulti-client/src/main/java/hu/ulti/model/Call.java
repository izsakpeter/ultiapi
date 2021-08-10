package hu.ulti.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import hu.ulti.Helper;

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
	public static final String DURI_SZINES_TERITETT = "teritettdurisz";
	public static final String DURI_SZINTELEN_TERITETT = "teritettduri";

	public static List<Integer> getCallList(String colorString, Set<String> calls) {

		int colorId = Helper.getSelectedId(colorString);

		List<Integer> callList = new ArrayList<Integer>();

		for (String call : calls) {
			
			int colorValue = 0;
			
			if(colorId == ZOLD_COLOR_ID)
				colorValue = 10;
			else if(colorId == TOK_COLOR_ID)
				colorValue = 20;
			else if(colorId == PIROS_COLOR_ID)
				colorValue = 30;
			
			if (call.equals(PASSZ))
				callList.add(0 + colorValue);
			else if (call.equals(SZAZ40))
				callList.add(1 + colorValue);
			else if (call.equals(ULTI))
				callList.add(2 + colorValue);
			else if (call.equals(BETLI))
				callList.add(3 + colorValue);
			else if (call.equals(DURI_SZINES))
				callList.add(4 + colorValue);
			else if (call.equals(DURI_SZINTELEN))
				callList.add(5 + colorValue);
			else if (call.equals(SZAZ20))
				callList.add(6 + colorValue);
			else if (call.equals(BETLI_TERITETT))
				callList.add(7 + colorValue);
			else if (call.equals(DURI_SZINES_TERITETT))
				callList.add(8 + colorValue);
			else if (call.equals(DURI_SZINTELEN_TERITETT))
				callList.add(9 + colorValue);
		}

		return callList;
	}
	
	public static List<String> getColorList(){
		
		List<String> list = new ArrayList<String>();
		list.add(0, MAKK);
		list.add(1, ZOLD);
		list.add(2, TOK);
		list.add(3, PIROS);
		
		return list;
	}
}
