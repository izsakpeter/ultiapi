
export class Call {
	
	public static MAKK: string = "makk";
	public static ZOLD: string = "zold";
	public static TOK: string = "tok";
	public static PIROS: string = "piros";

	public static MAKK_ID: number = 1;
	public static ZOLD_ID: number = 2;
	public static TOK_ID: number = 3;
	public static PIROS_ID: number = 4;

	
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

