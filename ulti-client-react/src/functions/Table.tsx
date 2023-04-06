import { Button } from "@blueprintjs/core";
import { getCard90Source, getCardSource, getHalfCard90Source, getHalfCardSource, getOrderedHand } from "../helper/cardHandler";
import { Card } from "../model/card";
import { Game } from "../model/game";

interface IGame {
    game: Game;
}

export default function Table({ game }: IGame) {

    if (!game.isRoundStarted && game.players) {
        return (<div>
            <div>Várakozó játékosok:</div>
            <div>
                {
                    game.players.map(player =>
                        <div>{player.playerName}</div>)
                }
            </div>
        </div>
        )
    }

    if (game.isRoundStarted) {
        return (

            <div className="table-container">
                <div className="menu">
                    menü
                </div>
                <div className="table">

                    <div className={"top-hand"}>
                        {renderTopPlayerHand(game)}
                    </div>

                    <div className={"mid"}>
                        <div className={"left-hand"}>
                            {renderLeftPlayerHand(game)}
                        </div>

                        <div className="mid-side">
                            <></>
                        </div>

                        <div className={"main"}>
                            <div>kezdőérték</div>

                            <div>
                                hivás + talon
                            </div>

                            <div>
                                üzenet + tér + mondás
                            </div>
                        </div>

                        <div className="mid-side">
                            <></>
                        </div>

                        <div className={"right-hand"}>
                            {renderRightPlayerHand(game)}
                        </div>
                    </div>

                    <div className={"my-hand"}>
                        {renderMyHand(game)}
                    </div>
                </div>
            </div>

        )
    }

    return (<>asztal</>)
}

function renderMyHand(game: Game) {

    if (game.player.hand === null) {
        return (
            <div></div>
        )
    }

    let cardsInHand = getOrderedHand(getCards(game.player.hand).sort((a, b) => a - b), game.player.isColorOrder);
    let cardsImg = [];
    for (let i = 0; i < cardsInHand.length; i++) {
        cardsImg.push(<Button key={"idh" + i} ><img alt="card" src={getCardSource(cardsInHand[i])} className="my-card" id={cardsInHand[i].toString()} /></Button>);
    }

    return (
        <div>{cardsImg}</div>
    )
}

function getCards(hand: Card[]) {
    let cards: number[] = [];

    for (let i = 0; i < hand.length; i++) {
        cards.push(hand[i].id);
    }

    return cards;
}

function renderRightPlayerHand(game: Game) {
    let hand = getHand(1, game, "right");

    return (
        <div>
            <div>{getPlayerName(1, game)}</div>
            <div >{hand}</div>
        </div>
    )
}

function renderTopPlayerHand(game: Game) {
    let hand = getHand(2, game, "top");

    return (
        <div>
            <div>{getPlayerName(2, game)}</div>
            <div>{hand}</div>
        </div>
    )
}

function renderLeftPlayerHand(game: Game) {
    let hand = getHand(3, game, "left");

    return (
        <div>
            <div>{getPlayerName(3, game)}</div>
            <div >{hand}</div>
        </div>
    )
}

function getHand(index: number, game: Game, poz: string): any[] {

    let hand: any = [];

    if (index === 3 && game.hands.length === 3)
        return hand;

    let handLength: number = game.hands.length;

    for (let j = 0; j < game.hands.length; j++) {
        if (game.player.playerId === game.hands[j].id) {

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

function getPlayerName(index: number, game: Game): string {

    if (index === 3 && game.hands.length === 3)
        return "";

    for (let j = 0; j < game.hands.length; j++) {
        if (game.player.playerId === game.hands[j].id) {
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
        else if (index === 4)
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
