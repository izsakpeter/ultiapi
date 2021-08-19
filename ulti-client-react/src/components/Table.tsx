import * as React from "react";
import { GetCardSource, GetOrderedHand } from "../helper/cardHandler";
import { Game } from "../model/game";

export class Table extends React.Component <{ gotCards: boolean, game: Game}, {} > {

    constructor(props) {
        super(props);
    }

    render() {
        if (!this.props.gotCards)
            return null;

        let cards = [];

        for (let i = 0; i < this.props.game.player.hand.length; i++) {
            cards.push(this.props.game.player.hand[i].id);
        }

        cards = GetOrderedHand(cards, this.props.game.player.colorOrder);

        let cardsImg = [];
        for (let i = 0; i < cards.length; i++) {
            cardsImg.push(<img key={"id" + i} src={GetCardSource(cards[i])} />);
        }

        return (
            <div>
                {cardsImg}
            </div>
        )
        
    }
}