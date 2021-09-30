import React = require("react");
import { GetCardSource, GetHalfCardSource } from "../helper/cardHandler";
import { getUsernameById } from "../helper/loginHandler";
import { Game } from "../model/game";
import { Hand } from "../model/hand";

interface iProps {
    game: Game
}

interface iState {
}

export default class OtherHandComponent extends React.Component<iProps, iState> {

    render() {
        return (
            <div>
                <div>{this.renderRightPlayerHand(this.props.game)}</div>
                <div>{this.renderTopPlayerHand(this.props.game)}</div>
                <div>{this.renderLeftPlayerHand(this.props.game)}</div>
            </div>
        )
    }

    renderRightPlayerHand(game: Game) {
        let hand = getHand(1, game);

        return (
            <div className="rightplayer">
                <div>{getPlayerName(1, game)}</div>
                <div >{hand}</div>
            </div>
        )

    }

    renderTopPlayerHand(game: Game) {
        let hand = getHand(2, game);

        return (
            <div className="topplayer">
                <div>{getPlayerName(2, game)}</div>
                <div>{hand}</div>
            </div>
        )
    }

    renderLeftPlayerHand(game: Game) {
        let hand = getHand(3, game);

        return (
            <div className="leftplayer">
                <div>{getPlayerName(3, game)}</div>
                <div >{hand}</div>
            </div>
        )
    }
}

function getHand(index: number, game: Game): any[] {

    let hand = [];

    if (index === 3 && game.hands.length === 3)
        return hand;

    let handLength: number = game.hands.length;

    for (let j = 0; j < game.hands.length; j++) {
        if (game.player.id === game.hands[j].id) {
            for (let i = 0; i < game.hands[getIncreasedIndex(j + index, handLength)].list.length; i++) {

                if (i !== game.hands[getIncreasedIndex(j + index, handLength)].list.length - 1)
                    hand.push(<img key={game.hands[getIncreasedIndex(j + index, handLength)].list[i].uuid} src={GetHalfCardSource(game.hands[getIncreasedIndex(j + index, handLength)].list[i].cardId)} className="otherhand-halfcard" />);
                else
                    hand.push(<img key={game.hands[getIncreasedIndex(j + index, handLength)].list[i].uuid} src={GetCardSource(game.hands[getIncreasedIndex(j + index, handLength)].list[i].cardId)} className="otherhand-card" />);
            }
        }
    }

    return hand;
}

function getPlayerName(index: number, game: Game): string {

    if (index === 3 && game.hands.length === 3)
        return "";

    for (let j = 0; j < game.hands.length; j++) {
        if (game.player.id === game.hands[j].id) {
            let handLength: number = game.hands.length;
            return getUsernameById(game.hands[getIncreasedIndex(j + index, handLength)].id);
        }
    }

    return "";
}


function getIncreasedIndex(index: number, length: number) {

    if (length === 3) {
        if (index == 3)
            return 0;
        else if (index == 4)
            return 1;
    } else if (length === 4) {
        if (index == 4)
            return 0;
        else if (index == 5)
            return 1;
    }

    return index;
}