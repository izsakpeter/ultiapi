import React = require("react");
import { getCallName, getCallValue, getCallValueSum } from "../helper/callHandler";
import { Game } from "../model/game";

export class MessageComponent extends React.Component<{ game: Game, gotCards: boolean, isLoggedIn: boolean }, { id: number, activePlayerId: number, lastCallerId: number, callList: Array<number>, gotCards: boolean, isLoggedIn: boolean }> {

    constructor(props) {
        super(props)

        this.state = {
            id: 0,
            activePlayerId: 0,
            lastCallerId: 0,
            callList: [],
            gotCards: true,
            isLoggedIn: false
        }
    }

    static getDerivedStateFromProps(props: { game: Game, gotCards: boolean, isLoggedIn: boolean }, state: { id: number, activePlayerId: number, lastCallerId: number, callList: Array<number>, gotCards: boolean, isLoggedIn: boolean }) {
        if (props.game != null) {
            state.id = props.game.player.id;
            state.activePlayerId = props.game.activePlayer;
            state.lastCallerId = props.game.lastCallerId;
            state.callList = props.game.previousCall;
            state.gotCards = props.gotCards;
            state.isLoggedIn = props.isLoggedIn;
        }

        return state;
    }

    render() {

        if (!this.state.gotCards && this.state.isLoggedIn) {
            return (
                <div className={"border"}>
                    A leosztás még nem történt meg!
                </div>
            )
        } else if (this.state.activePlayerId != this.state.id) {
            if (this.state.callList.length === 0) {
                return (
                    <div className={"border"}>
                        <div>Aktiv játékos: {this.state.activePlayerId}</div>
                    </div>
                )
            } else {
                return (
                    <div className={"border"}>
                        <div>Aktiv játékos: {this.state.activePlayerId}</div>
                        <div>Előző mondás: {getCallName(this.state.callList)}, értéke: {getCallValueSum(this.state.callList)} {this.state.lastCallerId} által.</div>
                    </div>
                )
            }
        } else {
            return (
                <></>
            )
        }
    }
}