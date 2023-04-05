import { Button } from "@blueprintjs/core";
import { getCardSource, getOrderedHand } from "../helper/cardHandler";
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

    if (game.isRoundStarted && game.player.hand) {
        return (

            <div className="table-container">
                <div className="menu">
                    menü
                </div>
                <div className="table">

                    <div className={"top-hand"}>
                        top
                    </div>

                    <div className={"mid"}>
                        <div className={"left-hand"}>
                            bal
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
                            jobb
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