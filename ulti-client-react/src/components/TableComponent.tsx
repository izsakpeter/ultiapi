import * as React from "react";
import { GetCardSource, GetOrderedHand } from "../helper/cardHandler";
import { Game } from "../model/game";
import { CallComponent } from "./CallComponent";
import { StartingValue } from "./StartingValueComponent";
import { Button } from "@blueprintjs/core";
import { RequestModel } from "../model/requestModel";

export class Table extends React.Component<{ gotCards: boolean, game: Game, onSetGame: (target: string) => void, postReq: (reqObj: RequestModel) => void }, { talon: number[], hand: number[] }> {

    cards: number[] = [];

    constructor(props) {
        super(props);

        this.state = {
            talon: [],
            hand: []
        };

        this.addToTalon = this.addToTalon.bind(this);
        this.backToHand = this.backToHand.bind(this);
        this.changeOrder = this.changeOrder.bind(this);
        this.clearTalon = this.clearTalon.bind(this);
    }

    static getDerivedStateFromProps(props: { gotCards: boolean, game: Game }, state: { talon: number[], hand: number[] }) {
        let cards: number[] = [];
        if (props.gotCards === true && props.game.player && state.talon.length == 0) {
            for (let i = 0; i < props.game.player.hand.length; i++) {
                cards.push(props.game.player.hand[i].id);
            }

            state.hand = cards;
        }
        return state;
    }

    render() {

        if (!this.props.gotCards)
            return <></>;

        let cardsInHand = GetOrderedHand(this.state.hand.sort((a, b) => a - b), this.props.game.player.colorOrder);
        let cardsImg = [];
        for (let i = 0; i < cardsInHand.length; i++) {
            cardsImg.push(<Button key={"idh" + i} ><img src={GetCardSource(cardsInHand[i])} className="button-card" onClick={this.addToTalon} id={cardsInHand[i].toString()} /></Button>);
        }

        let talonImg = [];
        for (let i = 0; i < this.state.talon.length; i++) {
            talonImg.push(<Button key={"idt" + i}><img src={GetCardSource(this.state.talon[i])} className="button-card" onClick={this.backToHand} id={this.state.talon[i].toString()} /></Button>);
        }

        return (
            <div >
                <div className={"align-center"}><StartingValue game={this.props.game} onSetGame={this.props.onSetGame} /></div>
                <div><CallComponent talon={this.state.talon} game={this.props.game} hand={this.state.hand} onSetGame={this.props.onSetGame} clearTalon={this.clearTalon} /></div>
                <div className={"talon"}>{talonImg} </div>
                <div className={"align-center-bottom"}>{cardsImg}</div>
                <div className={"button-order"}><Button onClick={this.changeOrder}>rendez</Button></div>
            </div>
        )
    }

    async changeOrder(event) {
        event.preventDefault();

        let reqObj: RequestModel = {
            dest: "order",
            id: this.props.game.player.id,
            colorOrder: !this.props.game.player.colorOrder
        }

        this.props.postReq(reqObj)
    }

    addToTalon(event) {
        if (this.state.hand.length + this.state.talon.length == 12 && this.state.talon.length < 2) { //<2 = 2 ????????????????????????????????????
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
}
