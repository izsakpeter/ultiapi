import React from "react";
import { getCard90Source, getCardSource, getHalfCard90Source, getHalfCardSource } from "../helper/cardHandler";
import { GameOld } from "../model/gameOld";

interface iProps {
    game: GameOld
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

    renderRightPlayerHand(game: GameOld) {
        let hand = getHand(1, game, "right");

        return (
            <div className="rightPlayer">
                <div>{getPlayerName(1, game)}</div>
                <div >{hand}</div>
            </div>
        )

    }

    renderTopPlayerHand(game: GameOld) {
        let hand = getHand(2, game, "top");

        return (
            <div className="topPlayer">
                <div>{getPlayerName(2, game)}</div>
                <div>{hand}</div>
            </div>
        )
    }

    renderLeftPlayerHand(game: GameOld) {
        let hand = getHand(3, game, "left");

        return (
            <div className="leftPlayer">
                <div>{getPlayerName(3, game)}</div>
                <div >{hand}</div>
            </div>
        )
    }
}

function getHand(index: number, game: GameOld, poz: string): any[] {

    let hand: any = [];

    if (index === 3 && game.hands.length === 3)
        return hand;

    let handLength: number = game.hands.length;

    for (let j = 0; j < game.hands.length; j++) {
        if (game.player.id === game.hands[j].id) {

            for (let i = 0; i < game.hands[getIncreasedIndex(j + index, handLength)].list.length; i++) {

                if (game.hands[getIncreasedIndex(j + index, handLength)].list.length > 0) {

                    let listItem = game.hands[getIncreasedIndex(j + index, handLength)].list[i];

                    if (i !== game.hands[getIncreasedIndex(j + index, handLength)].list.length - 1) {
                        if (poz === "top")
                            hand.push(<img alt="card" key={listItem.uuid} src={getHalfCardSource(listItem.cardId)} className={listItem.cardId === -2 ? "" : "otherhand-halfcard"} />);
                        else
                            hand.push(<div key={listItem.uuid}><img alt="card" src={getHalfCard90Source(listItem.cardId)} className={listItem.cardId === -2 ? "" : "otherhand-halfcard-90"} /></div>);
                    } else {
                        if (poz === "top")
                            hand.push(<img alt="card" key={listItem.uuid} src={getCardSource(listItem.cardId)} className={listItem.cardId === -2 ? "" : "otherhand-card"} />);
                        else
                            hand.push(<div key={listItem.uuid}><img alt="card" key={listItem.uuid} src={getCard90Source(listItem.cardId)} className={listItem.cardId === -2 ? "" : "otherhand-card-90"} /></div>);
                    }

                }
            }
        }
    }

    return hand;
}

function getPlayerName(index: number, game: GameOld): string {

    if (index === 3 && game.hands.length === 3)
        return "";

    for (let j = 0; j < game.hands.length; j++) {
        if (game.player.id === game.hands[j].id) {
            let handLength: number = game.hands.length;
            return game.hands[getIncreasedIndex(j + index, handLength)].id.toString();
        }
    }

    return "";
}


function getIncreasedIndex(index: number, length: number) {

    if (length === 3) {
        if (index === 3)
            return 0;
        else if (index ===4)
            return 1;
    } else if (length === 4) {
        if (index === 4)
            return 0;
        else if (index === 5)
            return 1;
        else if (index === 6)
            return 2;
    }

    return index;
}