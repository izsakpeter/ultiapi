import * as React from "react";
import { GetCard90Source, GetCardSource, GetHalfCard90Source, GetHalfCardSource, GetOrderedHand } from "../helper/cardHandler";
import { GameOld } from "../model/gameOld";
import { CallComponent } from "./CallComponent";
import { StartingValue } from "./StartingValueComponent";
import { Button } from "@blueprintjs/core";
import { RequestModel } from "../model/requestModel";
import { PlaygroundComponent } from "./PlaygroundComponent";
import { SayComponent } from "./SayComponent";
import { OperationsComponent } from "./OperationsComponent";
import { MessageComponent } from "./MessageComponent";
import { ResultComponent } from "./ResultComponent";
import { SayListComponent } from "./SayListComponent";

interface iProps {
    gotCards: boolean,
    game: GameOld,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    talon: number[],
    hand: number[],
    isGameOver: boolean
}

export class TableComponent extends React.Component<iProps, iState> {

    cards: number[] = [];

    constructor(props: any) {
        super(props);

        this.state = {
            talon: [],
            hand: [],
            isGameOver: false
        };

        this.cardAction = this.cardAction.bind(this);
        this.backToHand = this.backToHand.bind(this);
        this.clearTalon = this.clearTalon.bind(this);
    }

    static getDerivedStateFromProps(props: iProps, state: iState) {

        if (props.game != null)
            state.isGameOver = props.game.gameOver;

        let cards: number[] = [];
        if (props.gotCards === true && props.game.player && state.talon.length === 0) {
            for (let i = 0; i < props.game.player.hand.length; i++) {
                cards.push(props.game.player.hand[i].id);
            }

            state = {
                ...state,
                hand: cards
            };
        }
        return state;
    }

    render() {

        if (!this.props.gotCards)
            return <></>;

        if (this.state.isGameOver) {
            return (
                <div>
                    <div><ResultComponent game={this.props.game} postReq={this.props.postReq} /></div>
                </div>
            )
        } else {
            return (
                <div className="table-container">
                    <div className="menu">
                        <div><OperationsComponent game={this.props.game} postReq={this.props.postReq} /></div>
                    </div>
                    <div className="table">

                        <div className={"top-hand"}>
                            <div>{this.renderTopPlayerHand(this.props.game)}</div>
                        </div>

                        <div className={"mid"}>
                            <div className={"left-hand"}>
                                <div>{this.renderLeftPlayerHand(this.props.game)}</div>
                            </div>

                            <div className="mid-side">
                                <div className="align-left"><SayListComponent game={this.props.game} /></div>
                            </div>

                            <div className={"main"}>
                                <div><StartingValue game={this.props.game} postReq={this.props.postReq} /></div>

                                <div>
                                    <div><CallComponent talon={this.state.talon} game={this.props.game} hand={this.state.hand} postReq={this.props.postReq} clearTalon={this.clearTalon} /></div>
                                    <div>{this.renderTalon()}</div>
                                </div>

                                <div>
                                    <div><MessageComponent game={this.props.game} postReq={this.props.postReq} /></div>
                                    <div><PlaygroundComponent game={this.props.game} /></div>
                                    <div><SayComponent game={this.props.game} postReq={this.props.postReq} /></div>
                                </div>
                            </div>

                            <div className="mid-side">
                                <></>
                            </div>

                            <div className={"right-hand"}>
                                <div>{this.renderRightPlayerHand(this.props.game)}</div>
                            </div>
                        </div>

                        <div className={"my-hand"}>
                            <div>{this.renderMyHand()}</div>
                        </div>
                    </div>
                </div>
            )
        }
    }

    cardAction(event: any) {

        if (this.props.game.kontraPartFinished || this.props.game.firstTurn)
            if (this.props.game.playReadyToStart) {

                let reqObj: RequestModel = {
                    dest: "play",
                    id: this.props.game.player.id,
                    cardid: event.target.id
                }

                this.props.postReq(reqObj);

            } else if (this.state.hand.length + this.state.talon.length === 12 && this.state.talon.length < 2) { //<2 = 2 ????????????????????????????????????
                const index = this.state.hand.indexOf(parseInt(event.target.id));
                this.state.hand.splice(index, 1);
                this.setState({ talon: [...this.state.talon, parseInt(event.target.id)] });
            }
    }

    backToHand(event: any) {
        const index = this.state.talon.indexOf(parseInt(event.target.id));
        this.state.talon.splice(index, 1);
        this.setState({ hand: [...this.state.hand, parseInt(event.target.id)] });
    }

    clearTalon() {
        this.setState({ talon: [] });
    }

    renderMyHand() {
        let cardsInHand = GetOrderedHand(this.state.hand.sort((a, b) => a - b), this.props.game.player.colorOrder);
        let cardsImg = [];
        for (let i = 0; i < cardsInHand.length; i++) {
            cardsImg.push(<Button key={"idh" + i} ><img alt="card" src={GetCardSource(cardsInHand[i])} className="my-card" onClick={this.cardAction} id={cardsInHand[i].toString()} /></Button>);
        }

        return (
            <div>{cardsImg}</div>
        )
    }

    renderRightPlayerHand(game: GameOld) {
        let hand = getHand(1, game, "right");

        return (
            <div>
                <div>{getPlayerName(1, game)}</div>
                <div >{hand}</div>
            </div>
        )

    }

    renderTopPlayerHand(game: GameOld) {
        let hand = getHand(2, game, "top");

        return (
            <div>
                <div>{getPlayerName(2, game)}</div>
                <div>{hand}</div>
            </div>
        )
    }

    renderLeftPlayerHand(game: GameOld) {
        let hand = getHand(3, game, "left");

        return (
            <div>
                <div>{getPlayerName(3, game)}</div>
                <div >{hand}</div>
            </div>
        )
    }

    renderTalon() {
        let talonImg = [];
        for (let i = 0; i < this.state.talon.length; i++) {
            talonImg.push(<Button key={"idt" + i}><img alt="card" src={GetCardSource(this.state.talon[i])} className="talon-card" onClick={this.backToHand} id={this.state.talon[i].toString()} /></Button>);
        }

        return (
            <div>{talonImg} </div>
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
                            hand.push(<img alt="card" key={listItem.uuid} src={GetHalfCardSource(listItem.cardId)} className={listItem.cardId === -2 ? "" : "otherhand-halfcard"} />);
                        else
                            hand.push(<div key={listItem.uuid}><img alt="card" src={GetHalfCard90Source(listItem.cardId)} className={listItem.cardId === -2 ? "" : "otherhand-halfcard-90"} /></div>);
                    } else {
                        if (poz === "top")
                            hand.push(<img alt="card" key={listItem.uuid} src={GetCardSource(listItem.cardId)} className={listItem.cardId === -2 ? "" : "otherhand-card"} />);
                        else
                            hand.push(<div key={listItem.uuid}><img alt="card" key={listItem.uuid} src={GetCard90Source(listItem.cardId)} className={listItem.cardId === -2 ? "" : "otherhand-card-90"} /></div>);
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