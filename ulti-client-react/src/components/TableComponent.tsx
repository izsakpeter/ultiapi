import * as React from "react";
import { GetCardSource, GetOrderedHand } from "../helper/cardHandler";
import { Game } from "../model/game";
import { CallComponent } from "./CallComponent";
import { StartingValue } from "./StartingValueComponent";
import { Button } from "@blueprintjs/core";
import { RequestModel } from "../model/requestModel";
import { PlaygroundComponent } from "./PlaygroundComponent";
import { SayComponent } from "./SayComponent";
import OtherHandComponent from "./OtherHandComponent";
import { OperationsComponent } from "./OperationsComponent";

interface iProps {
    gotCards: boolean,
    game: Game,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    talon: number[],
    hand: number[]
}

export class Table extends React.Component<iProps, iState> {

    cards: number[] = [];

    constructor(props) {
        super(props);

        this.state = {
            talon: [],
            hand: []
        };

        this.cardAction = this.cardAction.bind(this);
        this.backToHand = this.backToHand.bind(this);
        this.clearTalon = this.clearTalon.bind(this);
    }

    static getDerivedStateFromProps(props: iProps, state: iState) {
        let cards: number[] = [];
        if (props.gotCards === true && props.game.player && state.talon.length == 0) {
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

        return (
            <div>
                <div className={"align-center"}>
                    <div><OtherHandComponent game={this.props.game} /></div>
                </div>

                <div className={"align-center"}>
                    <div><StartingValue game={this.props.game} postReq={this.props.postReq} /></div>
                </div>

                <div className={"my-hand"}>
                    <div>{this.renderMyHand()}</div>
                    <div><OperationsComponent game={this.props.game} postReq={this.props.postReq} /></div>
                </div>



                <div><CallComponent talon={this.state.talon} game={this.props.game} hand={this.state.hand} postReq={this.props.postReq} clearTalon={this.clearTalon} /></div>
                <div className={"playground"}><PlaygroundComponent game={this.props.game} /></div>
                <div>{this.renderTalon()}</div>


                <div className={"saycomp"}><SayComponent game={this.props.game} postReq={this.props.postReq} /></div>
            </div>
        )
    }

    cardAction(event) {

        if (this.props.game.kontraPartFinished || this.props.game.firstTurn)
            if (this.props.game.playReadyToStart) {

                let reqObj: RequestModel = {
                    dest: "play",
                    id: this.props.game.player.id,
                    cardid: event.target.id
                }

                this.props.postReq(reqObj);

            } else if (this.state.hand.length + this.state.talon.length == 12 && this.state.talon.length < 2) { //<2 = 2 ????????????????????????????????????
                const index = this.state.hand.indexOf(parseInt(event.target.id));
                this.state.hand.splice(index, 1);
                this.setState({ talon: [...this.state.talon, parseInt(event.target.id)] });
            }
    }

    backToHand(event) {
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
            cardsImg.push(<Button key={"idh" + i} ><img src={GetCardSource(cardsInHand[i])} className="button-card" onClick={this.cardAction} id={cardsInHand[i].toString()} /></Button>);
        }

        return (
            <div>
                <div>{cardsImg}</div>
            </div>
        )
    }

    renderTalon() {
        let talonImg = [];
        for (let i = 0; i < this.state.talon.length; i++) {
            talonImg.push(<Button key={"idt" + i}><img src={GetCardSource(this.state.talon[i])} className="button-card" onClick={this.backToHand} id={this.state.talon[i].toString()} /></Button>);
        }

        return (
            <div className={"talon"}>{talonImg} </div>
        )
    }
}
