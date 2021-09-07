
export class Call {

	public static MAKK: string = "makk";
	public static ZOLD: string = "zöld";
	public static TOK: string = "tök";
	public static PIROS: string = "piros";

	public static MAKK_ID: number = 1;
	public static ZOLD_ID: number = 2;
	public static TOK_ID: number = 3;
	public static PIROS_ID: number = 4;

	public static PASSZ: string = "passz";
	public static SZAZ40: string = "40-100";
	public static ULTI: string = "ulti";
	public static BETLI: string = "betli";
	public static DURI_SZINES: string = "durchmars";
	public static DURI_SZINTELEN: string = "szintelen durchmars";
	public static SZAZ20: string = "20-100";
	public static BETLI_TERITETT: string = "teritett betli";
	public static DURI_SZINES_TERITETT: string = "teritett durchmars";
	public static DURI_SZINTELEN_TERITETT: string = "szintelen teritett durchmars";

	public static PASSZ_ID: number = 0;
	public static SZAZ40_ID: number = 1;
	public static ULTI_ID: number = 2;
	public static BETLI_ID: number = 3;
	public static DURI_SZINES_ID: number = 4;
	public static DURI_SZINTELEN_ID: number = 5;
	public static SZAZ20_ID: number = 6;
	public static BETLI_TERITETT_ID: number = 7;
	public static DURI_SZINES_TERITETT_ID: number = 8;
	public static DURI_SZINTELEN_TERITETT_ID: number = 9;

	public static PASSZ_VALUE: number = 1;
	public static SZAZ40_VALUE: number = 4;
	public static ULTI_VALUE: number = 4;
	public static BETLI_VALUE: number = 5;
	public static DURI_VALUE: number = 6;
	public static SZAZ20_VALUE: number = 8;
	public static BETLI_TERITETT_VALUE: number = 10;
	public static DURI_TERITETT_VALUE: number = 12;

}

export function getCallList(colorNum: number, list: Array<number>): Array<number> {

	let colorValue: number = 0;
	let res: Array<number> = [];

	if (colorNum == Call.ZOLD_ID)
		colorValue = 10;
	else if (colorNum == Call.TOK_ID)
		colorValue = 20;
	else if (colorNum == Call.PIROS_ID)
		colorValue = 30;

	for (let i = 0; i < list.length; i++) {
		res.push(list[i] + colorValue);
	}

	return res;
}

export function getCallName(callList: Array<number>): string {

	let max: number = Math.max(...callList);
	let color: number = 0;
	let call: string = "";

	if (max < 10) {
		call = Call.MAKK
		color = 0;
	} else if (max < 20) {
		call = Call.ZOLD;
		color = 10
	} else if (max < 30) {
		call = Call.TOK;
		color = 20
	} else {
		call = Call.PIROS;
		color = 30
	}

	for (let i = 0; i < callList.length; i++) {
		switch (callList[i] - color) {
			case Call.PASSZ_ID:
				call += " " + Call.PASSZ;
				break;
			case Call.SZAZ40_ID:
				call += " " + Call.SZAZ40;
				break;
			case Call.ULTI_ID:
				call += " " + Call.ULTI;
				break;
			case Call.BETLI_ID:
				call += " " + Call.BETLI;
				break;
			case Call.DURI_SZINES_ID:
				call += " " + Call.DURI_SZINES;
				break;
			case Call.DURI_SZINTELEN_ID:
				call += " " + Call.DURI_SZINTELEN;
				break;
			case Call.SZAZ20_ID:
				call += " " + Call.SZAZ20;
				break;
			case Call.BETLI_TERITETT_ID:
				call += " " + Call.BETLI_TERITETT;
				break;
			case Call.DURI_SZINES_TERITETT_ID:
				call += " " + Call.DURI_SZINES_TERITETT;
				break;
			case Call.DURI_SZINTELEN_TERITETT_ID:
				call += " " + Call.DURI_SZINTELEN_TERITETT;
				break;
		}
	}

	return call;
}


export function getCallValueSum(list: Array<number>): number {
	let res = 0;
	let colorId: number = 0;

	if (list[0] < 10)
		colorId = 1;
	else if (list[0] > 9 && list[0] < 20)
		colorId = 2;
	else if (list[0] > 19 && list[0] < 30)
		colorId = 3;
	else
		colorId = 4;

	list.forEach(element => {
		res += getCallValue(element);
	});

	return (res * colorId);
}

export function getCallValue(callId: number): number {

	switch (callId) {
		case Call.PASSZ_ID:
			return Call.PASSZ_VALUE;
		case Call.SZAZ40_ID:
			return Call.SZAZ40_VALUE;
		case Call.ULTI_ID:
			return Call.ULTI_VALUE;
		case Call.BETLI_ID:
			return Call.BETLI_VALUE;
		case Call.DURI_SZINES_ID:
			return Call.DURI_VALUE;
		case Call.DURI_SZINTELEN_ID:
			return Call.DURI_VALUE;
		case Call.SZAZ20_ID:
			return Call.SZAZ20_VALUE;
		case Call.BETLI_TERITETT_ID:
			return Call.BETLI_TERITETT_VALUE;
		case Call.DURI_SZINES_TERITETT_ID:
			return Call.DURI_TERITETT_VALUE;
		case Call.DURI_SZINTELEN_TERITETT_ID:
			return Call.DURI_TERITETT_VALUE;
	}
}

