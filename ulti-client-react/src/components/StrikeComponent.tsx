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
                    <Button onClick={() => this.clickHandler(this.state.showStrikes, this.state.strikes)} text="ütések" />
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

        if (showStrikes) {

            let strikeArray = [];
            for (let i = 0; i < strikes.length; i++) {
                strikeArray.push(<div key={strikes[i].id}>kör: {strikes[i].id} <img src={GetCardSource(strikes[i].card1Id)} className={"strike-button-card"} /><img src={GetCardSource(strikes[i].card2Id)} className={"strike-button-card"} /><img src={GetCardSource(strikes[i].card3Id)} className={"strike-button-card"} /></div>);
            }

            console.log(strikeArray.length + " anrfgjsdfpogspdiougpiosdhfgiushdfguihsdfiuosduiofguisdfhguiosdfhgiousdfhg");

            return (
                <div>
                    {strikeArray}
                </div>
            )
        }
    }
}

