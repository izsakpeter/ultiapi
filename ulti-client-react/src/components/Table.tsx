import * as React from "react";
import { GetCardSource, GetOrderedHand } from "../helper/cardHandler";
import { Game } from "../model/game";
import { Call } from "./Call";
import { StartingValue } from "./StartingValue";

export class Table extends React.Component<{ gotCards: boolean, game: Game }, { talon: number[], hand: number[] }> {

    cards: number[] = [];

    needReload: boolean = true;

    constructor(props) {
        super(props);

        this.state = {
            talon: [],
            hand: []
        };

        this.addToTalon = this.addToTalon.bind(this);
        this.backToHand = this.backToHand.bind(this);
    }

    static getDerivedStateFromProps(props: { gotCards: boolean, game: Game }, state: { talon: number[], hand: number[] }) {
        let cards: number[] = [];
        if (props.gotCards === true && state.hand.length === 0) {
            for (let i = 0; i < props.game.player.hand.length; i++) {
                cards.push(props.game.player.hand[i].id);
            }

            state.hand = cards;
        }
        return state;
    }

    render() {

        if (!this.props.gotCards)
            return null;

        let cardsInHand = GetOrderedHand(this.state.hand.sort((a, b) => a - b), this.props.game.player.colorOrder);
        let cardsImg = [];
        for (let i = 0; i < cardsInHand.length; i++) {
            cardsImg.push(<button key={"idh" + i}><img src={GetCardSource(cardsInHand[i])} onClick={this.addToTalon} id={cardsInHand[i].toString()} /></button>);
        }

        let talonImg = [];
        for (let i = 0; i < this.state.talon.length; i++) {
            talonImg.push(<button key={"idt" + i}><img src={GetCardSource(this.state.talon[i])} onClick={this.backToHand} id={this.state.talon[i].toString()} /></button>);
        }

        return (
            <div>
                <div> {cardsImg}</div>
                <div><StartingValue game={this.props.game}/></div>
                <div> {talonImg} </div>
                <div><Call talon={this.state.talon} game={this.props.game} hand={this.state.hand}/></div>

            </div>
        )
    }

    addToTalon(event) {
        if (this.state.talon.length < 2) { //<2 = 2 ?????????????????????????????????????????????????????????????????????????????????????????????????????????
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
}
