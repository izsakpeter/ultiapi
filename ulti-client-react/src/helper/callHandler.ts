import { Constants } from "./constants";

export function getCallList(colorNum: number, list: Array<number>): Array<number> {

	let colorValue: number = 0;
	let res: Array<number> = [];

	if (colorNum == Constants.ZOLD_ID)
		colorValue = 10;
	else if (colorNum == Constants.TOK_ID)
		colorValue = 20;
	else if (colorNum == Constants.PIROS_ID)
		colorValue = 30;

	for (let i = 0; i < list.length; i++) {
		res.push(list[i] + colorValue);
	}

	return res;
}

export function getCallNameList(callList: Array<number>): string {

	let max: number = Math.max(...callList);
	let call: string = "";

	if (max < 10) {
		call = Constants.MAKK
	} else if (max < 20) {
		call = Constants.ZOLD;
	} else if (max < 30) {
		call = Constants.TOK;
	} else {
		call = Constants.PIROS;
	}

	for (let i = 0; i < callList.length; i++) {
		call += " " + getCallName(callList[i], false);
	}

	return call;
}

export function getCallName(callId: number, inclColor: boolean): string {

	let call: string = "";
	let color: number = 0;

	if (callId < 10) {
		if (inclColor)
			call = Constants.MAKK
		color = 0;
	} else if (callId < 20) {
		if (inclColor)
			call = Constants.ZOLD;
		color = 10
	} else if (callId < 30) {
		if (inclColor)
			call = Constants.TOK;
		color = 20
	} else {
		if (inclColor)
			call = Constants.PIROS;
		color = 30
	}

	switch (callId - color) {
		case Constants.PASSZ_ID:
			call += " " + Constants.PASSZ;
			break;
		case Constants.SZAZ40_ID:
			call += " " + Constants.SZAZ40;
			break;
		case Constants.ULTI_ID:
			call += " " + Constants.ULTI;
			break;
		case Constants.BETLI_ID:
			call += " " + Constants.BETLI;
			break;
		case Constants.DURI_SZINES_ID:
			call += " " + Constants.DURI_SZINES;
			break;
		case Constants.DURI_SZINTELEN_ID:
			call += " " + Constants.DURI_SZINTELEN;
			break;
		case Constants.SZAZ20_ID:
			call += " " + Constants.SZAZ20;
			break;
		case Constants.BETLI_TERITETT_ID:
			call += " " + Constants.BETLI_TERITETT;
			break;
		case Constants.DURI_SZINES_TERITETT_ID:
			call += " " + Constants.DURI_SZINES_TERITETT;
			break;
		case Constants.DURI_SZINTELEN_TERITETT_ID:
			call += " " + Constants.DURI_SZINTELEN_TERITETT;
			break;
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

	for (let index = 0; index < list.length; index++) {
		res += getCallValue(list[index] - ((colorId - 1) * 10));
	}

	return (res * colorId);
}

export function getCallValue(callId: number): number {

	switch (callId) {
		case Constants.PASSZ_ID:
			return Constants.PASSZ_VALUE;
		case Constants.SZAZ40_ID:
			return Constants.SZAZ40_VALUE;
		case Constants.ULTI_ID:
			return Constants.ULTI_VALUE;
		case Constants.BETLI_ID:
			return Constants.BETLI_VALUE;
		case Constants.DURI_SZINES_ID:
			return Constants.DURI_VALUE;
		case Constants.DURI_SZINTELEN_ID:
			return Constants.DURI_VALUE;
		case Constants.SZAZ20_ID:
			return Constants.SZAZ20_VALUE;
		case Constants.BETLI_TERITETT_ID:
			return Constants.BETLI_TERITETT_VALUE;
		case Constants.DURI_SZINES_TERITETT_ID:
			return Constants.DURI_TERITETT_VALUE;
		case Constants.DURI_SZINTELEN_TERITETT_ID:
			return Constants.DURI_TERITETT_VALUE;
	}
}