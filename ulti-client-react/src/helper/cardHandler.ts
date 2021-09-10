

export function GetCardSource(id: number): string {

	switch (id) {
		case 0: return "/resources/img/cards/m7.png";
		case 1: return "/resources/img/cards/m8.png";
		case 2: return "/resources/img/cards/m9.png";
		case 3: return "/resources/img/cards/m10.png";
		case 4: return "/resources/img/cards/mal.png";
		case 5: return "/resources/img/cards/mfel.png";
		case 6: return "/resources/img/cards/mk.png";
		case 7: return "/resources/img/cards/masz.png";
		case 8: return "/resources/img/cards/z7.png";
		case 9: return "/resources/img/cards/z8.png";
		case 10: return "/resources/img/cards/z9.png";
		case 11: return "/resources/img/cards/z10.png";
		case 12: return "/resources/img/cards/zal.png";
		case 13: return "/resources/img/cards/zfel.png";
		case 14: return "/resources/img/cards/zk.png";
		case 15: return "/resources/img/cards/zasz.png";
		case 16: return "/resources/img/cards/t7.png";
		case 17: return "/resources/img/cards/t8.png";
		case 18: return "/resources/img/cards/t9.png";
		case 19: return "/resources/img/cards/t10.png";
		case 20: return "/resources/img/cards/tal.png";
		case 21: return "/resources/img/cards/tfel.png";
		case 22: return "/resources/img/cards/tk.png";
		case 23: return "/resources/img/cards/tasz.png";
		case 24: return "/resources/img/cards/p7.png";
		case 25: return "/resources/img/cards/p8.png";
		case 26: return "/resources/img/cards/p9.png";
		case 27: return "/resources/img/cards/p10.png";
		case 28: return "/resources/img/cards/pal.png";
		case 29: return "/resources/img/cards/pfel.png";
		case 30: return "/resources/img/cards/pk.png";
		case 31: return "/resources/img/cards/pasz.png";
	}

	return "/resources/img/cards/hatlap.png";
}

export function GetOrderedHand(cards: Array<number>, isColorOrder: boolean): Array<number> {

	var orderedCards = cards;

	if (!isColorOrder) {

		for (let i = 0; i < cards.length; i++) {

			if (cards[i] == 3 || cards[i] == 11 || cards[i] == 19 || cards[i] == 27) {
				orderedCards = OrderCards(orderedCards, i, cards[i]);
			}
		}
	}

	return orderedCards;
}

function OrderCards(cards: Array<number>, index: number, checkIndex: number): Array<number> {
	if ((index + 1 < cards.length) && (cards[index + 1] == checkIndex + 1
		|| cards[index + 1] == checkIndex + 2
		|| cards[index + 1] == checkIndex + 3)) {

		var tmp = cards[index];
		cards.splice(index, 1);
		cards.splice(index + 1, 0, tmp);
	}

	if ((index + 2 < cards.length) && (cards[index + 2] == checkIndex + 2
		|| cards[index + 2] == checkIndex + 3)) {

		var tmp = cards[index + 1];
		cards.splice(index + 1, 1);
		cards.splice(index + 2, 0, tmp);
	}

	if ((index + 3 < cards.length) && cards[index + 3] == checkIndex + 3) {

		var tmp = cards[index + 2];
		cards.splice(index + 2, 1);
		cards.splice(index + 3, 0, tmp);
	}

	return cards;
}
