import { Call } from "../model/call";
import { Card } from "../model/card";
import { Game } from "../model/game";
import { Constants } from "./constants";

export function getAllCall(): Array<Call> {

	let calls: Array<Call> = [];

	calls.push(new Call(0, Constants.PASSZ_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.PASSZ));
	calls.push(new Call(1, Constants.CSENDES_SZAZ_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.CSENDES_SZAZ));
	calls.push(new Call(2, Constants.CSENDES_ULTI_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.CSENDES_ULTI));
	calls.push(new Call(3, Constants.SZAZ40_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.SZAZ40));
	calls.push(new Call(4, Constants.ULTI_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.ULTI));
	calls.push(new Call(5, Constants.BETLI_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.BETLI));
	calls.push(new Call(6, Constants.DURI_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.DURI_SZINES));
	calls.push(new Call(7, Constants.DURI_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.DURI_SZINTELEN));
	calls.push(new Call(8, Constants.SZAZ20_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.SZAZ20));
	calls.push(new Call(9, Constants.BETLI_TERITETT_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.BETLI_TERITETT));
	calls.push(new Call(10, Constants.DURI_TERITETT_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.DURI_SZINES_TERITETT));
	calls.push(new Call(11, Constants.DURI_TERITETT_VALUE * Constants.MAKK_ID, Constants.MAKK_ID, Constants.DURI_SZINTELEN_TERITETT));

	calls.push(new Call(12, Constants.PASSZ_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.PASSZ));
	calls.push(new Call(13, Constants.CSENDES_SZAZ_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.CSENDES_SZAZ));
	calls.push(new Call(14, Constants.CSENDES_ULTI_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.CSENDES_ULTI));
	calls.push(new Call(15, Constants.SZAZ40_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.SZAZ40));
	calls.push(new Call(16, Constants.ULTI_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.ULTI));
	calls.push(new Call(17, Constants.BETLI_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.BETLI));
	calls.push(new Call(18, Constants.DURI_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.DURI_SZINES));
	calls.push(new Call(19, Constants.DURI_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.DURI_SZINTELEN));
	calls.push(new Call(20, Constants.SZAZ20_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.SZAZ20));
	calls.push(new Call(21, Constants.BETLI_TERITETT_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.BETLI_TERITETT));
	calls.push(new Call(22, Constants.DURI_TERITETT_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.DURI_SZINES_TERITETT));
	calls.push(new Call(23, Constants.DURI_TERITETT_VALUE * Constants.ZOLD_ID, Constants.ZOLD_ID, Constants.DURI_SZINTELEN_TERITETT));

	calls.push(new Call(24, Constants.PASSZ_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.PASSZ));
	calls.push(new Call(25, Constants.CSENDES_SZAZ_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.CSENDES_SZAZ));
	calls.push(new Call(26, Constants.CSENDES_ULTI_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.CSENDES_ULTI));
	calls.push(new Call(27, Constants.SZAZ40_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.SZAZ40));
	calls.push(new Call(28, Constants.ULTI_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.ULTI));
	calls.push(new Call(29, Constants.BETLI_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.BETLI));
	calls.push(new Call(30, Constants.DURI_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.DURI_SZINES));
	calls.push(new Call(31, Constants.DURI_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.DURI_SZINTELEN));
	calls.push(new Call(32, Constants.SZAZ20_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.SZAZ20));
	calls.push(new Call(33, Constants.BETLI_TERITETT_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.BETLI_TERITETT));
	calls.push(new Call(34, Constants.DURI_TERITETT_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.DURI_SZINES_TERITETT));
	calls.push(new Call(35, Constants.DURI_TERITETT_VALUE * Constants.TOK_ID, Constants.TOK_ID, Constants.DURI_SZINTELEN_TERITETT));

	calls.push(new Call(36, Constants.PASSZ_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.PASSZ));
	calls.push(new Call(37, Constants.CSENDES_SZAZ_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.CSENDES_SZAZ));
	calls.push(new Call(38, Constants.CSENDES_ULTI_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.CSENDES_ULTI));
	calls.push(new Call(39, Constants.SZAZ40_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.SZAZ40));
	calls.push(new Call(40, Constants.ULTI_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.ULTI));
	calls.push(new Call(41, Constants.BETLI_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.BETLI));
	calls.push(new Call(42, Constants.DURI_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.DURI_SZINES));
	calls.push(new Call(43, Constants.DURI_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.DURI_SZINTELEN));
	calls.push(new Call(44, Constants.SZAZ20_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.SZAZ20));
	calls.push(new Call(45, Constants.BETLI_TERITETT_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.BETLI_TERITETT));
	calls.push(new Call(46, Constants.DURI_TERITETT_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.DURI_SZINES_TERITETT));
	calls.push(new Call(47, Constants.DURI_TERITETT_VALUE * Constants.PIROS_ID, Constants.PIROS_ID, Constants.DURI_SZINTELEN_TERITETT));

	return calls;
}

export function getCallValueSum(callList: Array<number>): number {
	let sum = 0;
	let allCalls: Array<Call> = getAllCall();

	for (let i = 0; i < allCalls.length; i++) {
		for (let j = 0; j < callList.length; j++) {
			if (allCalls[i].id === callList[j])
				sum += allCalls[i].value;
		}
	}

	return sum;
}

export function getCallNameListString(callList: Array<number>): string {
	let call: string = getColorByCallItem(callList[0]);

	let allCalls: Array<Call> = getAllCall();

	for (let i = 0; i < allCalls.length; i++) {
		for (let j = 0; j < callList.length; j++) {
			if (allCalls[i].id === callList[j])
				call += " " + allCalls[i].name;
		}
	}

	return call;
}

export function getColorByCallItem(item: number): string {
	let color = "";
	let colorId: number = getColorIdByCallItem(item);

	if (colorId === Constants.MAKK_ID)
		return Constants.MAKK;
	else if (colorId === Constants.ZOLD_ID)
		return Constants.ZOLD;
	else if (colorId === Constants.TOK_ID)
		return Constants.TOK;
	else if (colorId === Constants.PIROS_ID)
		return Constants.PIROS;

	return color;
}

export function getColorIdByCallItem(item: number): number {
	let colorId: number = 0;
	let allCalls: Array<Call> = getAllCall();

	for (let i = 0; i < allCalls.length; i++) {
		if (allCalls[i].id === item) {
			return allCalls[i].color;
		}
	}

	return colorId;
}

export function getCallName(callId: number): string {
	let allCalls: Array<Call> = getAllCall();

	for (let i = 0; i < allCalls.length; i++) {
		if (allCalls[i].id === callId) {
			return allCalls[i].color + " " + allCalls[i].name;
		}
	}

	return "";
}

export function getCallList(colorNum: number, list: Array<number>): Array<number> {

	let colorValue: number = 0;
	let res: Array<number> = [];

	if (colorNum == Constants.ZOLD_ID)
		colorValue = 12;
	else if (colorNum == Constants.TOK_ID)
		colorValue = 24;
	else if (colorNum == Constants.PIROS_ID)
		colorValue = 36;

	for (let i = 0; i < list.length; i++) {
		res.push(list[i] + colorValue);
	}

	return res;
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