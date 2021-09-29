import React = require("react");
import { GetCardSource } from "../helper/cardHandler";
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

function getIncreasedIndex(index: number) {
    if (index == 3)
        return 0;
    else if (index == 4)
        return 1;

    return index;
}

function getHand(index: number, game: Game): any[] {

    let hand = [];

    if (index === 3 && game.hands.length === 3)
        return hand;

    for (let j = 0; j < game.hands.length; j++) {
        if (game.player.id === game.hands[j].id) {
            for (let i = 0; i < game.hands[getIncreasedIndex(j + index)].list.length; i++) {
                hand.push(<img key={game.hands[getIncreasedIndex(j + index)].list[i].uuid} src={GetCardSource(game.hands[getIncreasedIndex(j + index)].list[i].cardId)} className="otherhand-card" />);
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

            return getUsernameById(game.hands[getIncreasedIndex(j + index)].id);

        }
    }

    return "";
}