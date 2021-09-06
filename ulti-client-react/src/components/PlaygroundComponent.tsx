import React = require("react");
import { GetCardSource } from "../helper/cardHandler";
import { Game } from "../model/game";


export class PlaygroundComponent extends React.Component<{ game: Game }, {}> {

    constructor(props) {
        super(props);
    }

    render() {

        if (!this.props.game.playReadyToStart) {
            return (
                <></>
            )
        } else {
            return (
                <div>
                    <img src={GetCardSource(this.props.game.round.card1Id)} className="button-card" />
                    <img src={GetCardSource(this.props.game.round.card2Id)} className="button-card" />
                    <img src={GetCardSource(this.props.game.round.card3Id)} className="button-card" />
                </div>
            )
        }


    }

}