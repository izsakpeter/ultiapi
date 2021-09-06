import React = require("react");
import { Call, getCallName } from "../model/call";
import { Game } from "../model/game";

export class MessageComponent extends React.Component<{ game: Game }, {id: number, activePlayerId: number, lastCallerId: number, callList: Array<number> }> {

    constructor(props) {
        super(props)

        this.state = {
            id: 0,
            activePlayerId: 0,
            lastCallerId: 0,
            callList: []
        }
    }

    static getDerivedStateFromProps(props: { game: Game }, state: {id: number, activePlayerId: number, lastCallerId: number, callList: Array<number> }) {
        if (props.game != null) {
            state.id = props.game.player.id;
            state.activePlayerId = props.game.activePlayer;
            state.lastCallerId = props.game.lastCallerId;
            state.callList = props.game.previousCall;
        }

        return state;
    }

    render() {

        if (this.state.activePlayerId != this.state.id) {
            return (
                <div>
                    <div>Aktiv játékos: {this.state.activePlayerId}</div>
                    <div>Előző mondás {getCallName(this.state.callList)} {this.state.lastCallerId} által.</div>
                </div>
            )
        } else {
            return (
                <></>
            )
        }
    }
}