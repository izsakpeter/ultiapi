
export class Call {
	public static MAKK: string = "makk";
	public static ZOLD: string = "zold";
	public static TOK: string = "tok";
	public static PIROS: string = "piros";

	public static MAKK_COLOR_ID: number = 1;
	public static ZOLD_COLOR_ID: number = 2;
	public static TOK_COLOR_ID: number = 3;
	public static PIROS_COLOR_ID: number = 4;

	public static PASSZ: string = "passz";
	public static SZAZ40: string = "40szaz";
	public static ULTI: string = "ulti";
	public static BETLI: string = "betli";
	public static DURI_SZINES: string = "szinesduri";
	public static DURI_SZINTELEN: string = "szintelenduri";
	public static SZAZ20: string = "20szaz";
	public static BETLI_TERITETT: string = "teritettbetli";
	public static DURI_SZINES_TERITETT: string = "teritettdurisz";
	public static DURI_SZINTELEN_TERITETT: string = "teritettduri";

}

export function getCallList(colorNum: number, list: Array<number>): Array<number> {

	let colorValue: number = 0;
	let res: Array<number> = [];
	
	if (colorNum == Call.ZOLD_COLOR_ID)
		colorValue = 10;
	else if (colorNum == Call.TOK_COLOR_ID)
		colorValue = 20;
	else if (colorNum == Call.PIROS_COLOR_ID)
		colorValue = 30;

	for (let i = 0; i < list.length; i++) {
		res.push(list[i] + colorValue);
	}

	return res;
}

