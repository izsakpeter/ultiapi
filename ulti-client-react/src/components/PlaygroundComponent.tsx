import React = require("react");
import { GetCardSource } from "../helper/cardHandler";
import { Game } from "../model/game";

interface iProps {
    game: Game
}

interface iState {
}


export class PlaygroundComponent extends React.Component<iProps, iState> {

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
                    <img src={GetCardSource(this.props.game.round.card1Id)} className="playcard" />
                    <img src={GetCardSource(this.props.game.round.card2Id)} className="playcard" />
                    <img src={GetCardSource(this.props.game.round.card3Id)} className="playcard" />
                </div>
            )
        }
    }
}