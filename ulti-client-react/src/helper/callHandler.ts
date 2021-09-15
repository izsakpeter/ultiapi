import { Game } from "../model/game";
import { Constants } from "./constants";

export function getColorByCallList(list: Array<number>): number {
	if (list[0] < 10)
		return Constants.MAKK_ID;
	else if (list[0] < 20)
		return Constants.ZOLD_ID;
	else if (list[0] < 30)
		return Constants.TOK_ID;
	else
		return Constants.PIROS_ID;
}

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
	let call: string = getColorByCallList(callList).toString();

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
	let colorId: number = getColorByCallList(list);

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

export function isBluff4020(callList: Array<number>, colorId: number, game: Game): boolean {

	let call40Id: number;
	let call20Id: number;

	if (colorId == 1) {
		call40Id = 1;
		call20Id = 6;
	} else if (colorId == 2) {
		call40Id = 11;
		call20Id = 16;
	} else if (colorId == 3) {
		call40Id = 21;
		call20Id = 26;
	} else if (colorId == 4) {
		call40Id = 31;
		call20Id = 36;
	}

	if (callList.includes(call40Id)) {
		return have40(colorId, game);
	} else if (callList.includes(call20Id)) {
		return (have20(colorId, game) > 0);
	}

	return false;
}

export function have40(colorId: number, game: Game): boolean {
	let aduFelsoId: number;
	let aduKiralyId: number;

	if (colorId == 1) {
		aduFelsoId = 5;
		aduKiralyId = 6;
	} else if (colorId == 2) {
		aduFelsoId = 13;
		aduKiralyId = 14;
	} else if (colorId == 3) {
		aduFelsoId = 21;
		aduKiralyId = 22;
	} else if (colorId == 4) {
		aduFelsoId = 29;
		aduKiralyId = 30;
	}

	let haveFelso: boolean = false;
	let haveKiraly: boolean = false;

	for (let i = 0; i < game.player.hand.length; i++) {
		if (game.player.hand[i].id === aduFelsoId)
			haveFelso = true;
		else if (game.player.hand[i].id === aduKiralyId)
			haveKiraly = true;
	}

	if (haveFelso && haveKiraly)
		return false;

	return true
}

export function have20(colorId: number, game: Game): number {

	let result = 0;

	let fel1: number;
	let kir1: number;
	let fel2: number;
	let kir2: number;
	let fel3: number;
	let kir3: number;

	if (colorId == 1) {
		fel1 = 13;
		kir1 = 14;
		fel2 = 21;
		kir2 = 22;
		fel3 = 29;
		kir3 = 30;
	} else if (colorId == 2) {
		fel1 = 5;
		kir1 = 6;
		fel2 = 21;
		kir2 = 22;
		fel3 = 29;
		kir3 = 30;
	} else if (colorId == 3) {
		fel1 = 5;
		kir1 = 6;
		fel2 = 13;
		kir2 = 14;
		fel3 = 29;
		kir3 = 30;
	} else if (colorId == 4) {
		fel1 = 5;
		kir1 = 6;
		fel2 = 13;
		kir2 = 14;
		fel3 = 21;
		kir3 = 22;
	}

	let haveFel1: boolean = false;
	let haveKir1: boolean = false;
	let haveFel2: boolean = false;
	let haveKir2: boolean = false;
	let haveFel3: boolean = false;
	let haveKir3: boolean = false;

	for (let i = 0; i < game.player.hand.length; i++) {
		if (game.player.hand[i].id === fel1)
			haveFel1 = true;
		else if (game.player.hand[i].id === kir1)
			haveKir1 = true;
		else if (game.player.hand[i].id === fel2)
			haveFel2 = true;
		else if (game.player.hand[i].id === kir2)
			haveKir2 = true;
		else if (game.player.hand[i].id === fel3)
			haveFel3 = true;
		else if (game.player.hand[i].id === kir3)
			haveKir3 = true;
	}

	if (haveFel1 && haveKir1)
		result += 1;

	if (haveFel2 && haveKir2)
		result += 1;

	if (haveFel3 && haveKir3)
		result += 1;

	return result;
}