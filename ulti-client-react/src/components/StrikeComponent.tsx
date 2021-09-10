import { Button } from "@blueprintjs/core";
import React = require("react");
import { GetCardSource } from "../helper/cardHandler";
import { Game } from "../model/game";
import { Strike } from "../model/strike";


export class StrikeComponent extends React.Component<{ game: Game }, { strikes: Array<Strike>, showStrikes: boolean }> {

    constructor(props) {
        super(props);

        this.state = {
            strikes: [],
            showStrikes: false
        }
    }

    static getDerivedStateFromProps(props: { game: Game }, state: { strikes: Array<Strike> }) {
        state.strikes = props.game.player.strikes;
        return state;
    }

    render() {
        if (this.props.game.player.strikes.length > 0) {

            return (
                <div>
                    <div><Button onClick={() => this.clickHandler(this.state.showStrikes, this.state.strikes)} text="ütések" /></div>
                    <div>{StrikeList(this.state.strikes, this.state.showStrikes)}</div>
                </div>
            )
        } else {
            return (
                <></>
            )
        }
    }

    clickHandler(showStrikes: boolean, strikes: Array<Strike>) {
        this.setState({ showStrikes: !showStrikes });
    }
}

function StrikeList(strikes: Array<Strike>, showStrikes: boolean) {

    if (showStrikes) {
        const strikeList = strikes.map((strike) => <div key={strike.id}>kör:{strike.id}<img src={GetCardSource(strike.card1Id)} className="strike-button-card" /><img src={GetCardSource(strike.card2Id)} className="strike-button-card" /><img src={GetCardSource(strike.card3Id)} className="strike-button-card" /></div>)

        return (
            <div>
                {strikeList}
            </div>
        )
    } else {
        return (
            <></>
        )
    }
}