

const img_path: string = "/resources/img/";

export function GetCardSource(id: number): string {

	console.log(id)

	switch (id) {
		case -1: return img_path + "cards/hatlap.png";
		case 0: return img_path + "cards/m7.png";
		case 1: return img_path + "cards/m8.png";
		case 2: return img_path + "cards/m9.png";
		case 3: return img_path + "cards/m10.png";
		case 4: return img_path + "cards/mal.png";
		case 5: return img_path + "cards/mfel.png";
		case 6: return img_path + "cards/mk.png";
		case 7: return img_path + "cards/masz.png";
		case 8: return img_path + "cards/z7.png";
		case 9: return img_path + "cards/z8.png";
		case 10: return img_path + "cards/z9.png";
		case 11: return img_path + "cards/z10.png";
		case 12: return img_path + "cards/zal.png";
		case 13: return img_path + "cards/zfel.png";
		case 14: return img_path + "cards/zk.png";
		case 15: return img_path + "cards/zasz.png";
		case 16: return img_path + "cards/t7.png";
		case 17: return img_path + "cards/t8.png";
		case 18: return img_path + "cards/t9.png";
		case 19: return img_path + "cards/t10.png";
		case 20: return img_path + "cards/tal.png";
		case 21: return img_path + "cards/tfel.png";
		case 22: return img_path + "cards/tk.png";
		case 23: return img_path + "cards/tasz.png";
		case 24: return img_path + "cards/p7.png";
		case 25: return img_path + "cards/p8.png";
		case 26: return img_path + "cards/p9.png";
		case 27: return img_path + "cards/p10.png";
		case 28: return img_path + "cards/pal.png";
		case 29: return img_path + "cards/pfel.png";
		case 30: return img_path + "cards/pk.png";
		case 31: return img_path + "cards/pasz.png";
	}

	return "";
}

export function GetHalfCardSource(id: number): string {

	/*switch (id) {
		case -1: return img_path + "half_cards/hatlap.png";
		case 0: return img_path + "half_cards/m7.png";
		case 1: return img_path + "half_cards/m8.png";
		case 2: return img_path + "half_cards/m9.png";
		case 3: return img_path + "half_cards/m10.png";
		case 4: return img_path + "half_cards/mal.png";
		case 5: return img_path + "half_cards/mfel.png";
		case 6: return img_path + "half_cards/mk.png";
		case 7: return img_path + "half_cards/masz.png";
		case 8: return img_path + "half_cards/z7.png";
		case 9: return img_path + "half_cards/z8.png";
		case 10: return img_path + "half_cards/z9.png";
		case 11: return img_path + "half_cards/z10.png";
		case 12: return img_path + "half_cards/zal.png";
		case 13: return img_path + "half_cards/zfel.png";
		case 14: return img_path + "half_cards/zk.png";
		case 15: return img_path + "half_cards/zasz.png";
		case 16: return img_path + "half_cards/t7.png";
		case 17: return img_path + "half_cards/t8.png";
		case 18: return img_path + "half_cards/t9.png";
		case 19: return img_path + "half_cards/t10.png";
		case 20: return img_path + "half_cards/tal.png";
		case 21: return img_path + "half_cards/tfel.png";
		case 22: return img_path + "half_cards/tk.png";
		case 23: return img_path + "half_cards/tasz.png";
		case 24: return img_path + "half_cards/p7.png";
		case 25: return img_path + "half_cards/p8.png";
		case 26: return img_path + "half_cards/p9.png";
		case 27: return img_path + "half_cards/p10.png";
		case 28: return img_path + "half_cards/pal.png";
		case 29: return img_path + "half_cards/pfel.png";
		case 30: return img_path + "half_cards/pk.png";
		case 31: return img_path + "half_cards/pasz.png";
	}*/

	return "";
}

export function GetCard90Source(id: number): string {
/*
	switch (id) {
		case -1: return img_path + "cards_90/hatlap.png";
		case 0: return img_path + "cards_90/m7.png";
		case 1: return img_path + "cards_90/m8.png";
		case 2: return img_path + "cards_90/m9.png";
		case 3: return img_path + "cards_90/m10.png";
		case 4: return img_path + "cards_90/mal.png";
		case 5: return img_path + "cards_90/mfel.png";
		case 6: return img_path + "cards_90/mk.png";
		case 7: return img_path + "cards_90/masz.png";
		case 8: return img_path + "cards_90/z7.png";
		case 9: return img_path + "cards_90/z8.png";
		case 10: return img_path + "cards_90/z9.png";
		case 11: return img_path + "cards_90/z10.png";
		case 12: return img_path + "cards_90/zal.png";
		case 13: return img_path + "cards_90/zfel.png";
		case 14: return img_path + "cards_90/zk.png";
		case 15: return img_path + "cards_90/zasz.png";
		case 16: return img_path + "cards_90/t7.png";
		case 17: return img_path + "cards_90/t8.png";
		case 18: return img_path + "cards_90/t9.png";
		case 19: return img_path + "cards_90/t10.png";
		case 20: return img_path + "cards_90/tal.png";
		case 21: return img_path + "cards_90/tfel.png";
		case 22: return img_path + "cards_90/tk.png";
		case 23: return img_path + "cards_90/tasz.png";
		case 24: return img_path + "cards_90/p7.png";
		case 25: return img_path + "cards_90/p8.png";
		case 26: return img_path + "cards_90/p9.png";
		case 27: return img_path + "cards_90/p10.png";
		case 28: return img_path + "cards_90/pal.png";
		case 29: return img_path + "cards_90/pfel.png";
		case 30: return img_path + "cards_90/pk.png";
		case 31: return img_path + "cards_90/pasz.png";
	}*/

	return "";
}

export function GetHalfCard90Source(id: number): string {
/*
	switch (id) {
		case -1: return img_path + "half_cards_90/hatlap.png";
		case 0: return img_path + "half_cards_90/m7.png";
		case 1: return img_path + "half_cards_90/m8.png";
		case 2: return img_path + "half_cards_90/m9.png";
		case 3: return img_path + "half_cards_90/m10.png";
		case 4: return img_path + "half_cards_90/mal.png";
		case 5: return img_path + "half_cards_90/mfel.png";
		case 6: return img_path + "img/half_cards_90/mk.png";
		case 7: return img_path + "half_cards_90/masz.png";
		case 8: return img_path + "half_cards_90/z7.png";
		case 9: return img_path + "half_cards_90/z8.png";
		case 10: return img_path + "half_cards_90/z9.png";
		case 11: return img_path + "half_cards_90/z10.png";
		case 12: return img_path + "half_cards_90/zal.png";
		case 13: return img_path + "half_cards_90/zfel.png";
		case 14: return img_path + "half_cards_90/zk.png";
		case 15: return img_path + "half_cards_90/zasz.png";
		case 16: return img_path + "half_cards_90/t7.png";
		case 17: return img_path + "half_cards_90/t8.png";
		case 18: return img_path + "half_cards_90/t9.png";
		case 19: return img_path + "half_cards_90/t10.png";
		case 20: return img_path + "half_cards_90/tal.png";
		case 21: return img_path + "half_cards_90/tfel.png";
		case 22: return img_path + "half_cards_90/tk.png";
		case 23: return img_path + "half_cards_90/tasz.png";
		case 24: return img_path + "half_cards_90/p7.png";
		case 25: return img_path + "half_cards_90/p8.png";
		case 26: return img_path + "half_cards_90/p9.png";
		case 27: return img_path + "half_cards_90/p10.png";
		case 28: return img_path + "half_cards_90/pal.png";
		case 29: return img_path + "half_cards_90/pfel.png";
		case 30: return img_path + "half_cards_90/pk.png";
		case 31: return img_path + "half_cards_90/pasz.png";
	}*/

	return "";
}

export function GetOrderedHand(cards: Array<number>, isColorOrder: boolean): Array<number> {

	var orderedCards = cards;

	if (!isColorOrder) {

		for (let i = 0; i < cards.length; i++) {

			if (cards[i] === 3 || cards[i] === 11 || cards[i] === 19 || cards[i] === 27) {
				orderedCards = OrderCards(orderedCards, i, cards[i]);
			}
		}
	}

	return orderedCards;
}

function OrderCards(cards: Array<number>, index: number, checkIndex: number): Array<number> {
	if ((index + 1 < cards.length) && (cards[index + 1] === checkIndex + 1
		|| cards[index + 1] === checkIndex + 2
		|| cards[index + 1] === checkIndex + 3)) {

		var tmp = cards[index];
		cards.splice(index, 1);
		cards.splice(index + 1, 0, tmp);
	}

	if ((index + 2 < cards.length) && (cards[index + 2] === checkIndex + 2
		|| cards[index + 2] === checkIndex + 3)) {

		cards.splice(index + 1, 1);
		cards.splice(index + 2, 0, cards[index + 1]);
	}

	if ((index + 3 < cards.length) && cards[index + 3] === checkIndex + 3) {

		cards.splice(index + 2, 1);
		cards.splice(index + 3, 0, cards[index + 2]);
	}

	return cards;
}
