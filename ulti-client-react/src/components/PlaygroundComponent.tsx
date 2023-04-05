import React from "react";
import { GetCardSource } from "../helper/cardHandler";
import { GameOld } from "../model/gameOld";

interface iProps {
    game: GameOld
}

interface iState {
}


export class PlaygroundComponent extends React.Component<iProps, iState> {

    render() {

        if (!this.props.game.playReadyToStart) {
            return (
                <></>
            )
        } else {
            return (
                <div>
                    <img alt="card" src={GetCardSource(this.props.game.round.card1Id)} className="playcard" />
                    <img alt="card" src={GetCardSource(this.props.game.round.card2Id)} className="playcard" />
                    <img alt="card" src={GetCardSource(this.props.game.round.card3Id)} className="playcard" />
                </div>
            )
        }
    }
}