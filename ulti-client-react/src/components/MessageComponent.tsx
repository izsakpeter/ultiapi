import { Button } from "@blueprintjs/core";
import React = require("react");
import { getCallNameListString, getCallValueSum } from "../helper/callHandler";
import { Call } from "../model/call";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";
import { ResultComponent } from "./ResultComponent";

interface iProps {
    game: Game,
    gotCards: boolean,
    isLoggedIn: boolean,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    playerId: number,
    activePlayerId: number,
    lastCallerId: number,
    callList: Array<Call>,
    gotCards: boolean,
    isLoggedIn: boolean,
    isGameOver: boolean,
    isRoundRun: boolean,
    isPlayRun: boolean
}

export class MessageComponent extends React.Component<iProps, iState> {

    constructor(props) {
        super(props)

        this.state = {
            playerId: 0,
            activePlayerId: 0,
            lastCallerId: 0,
            callList: [],
            gotCards: true,
            isLoggedIn: false,
            isGameOver: false,
            isRoundRun: false,
            isPlayRun: false
        }
    }

    static getDerivedStateFromProps(props: iProps, state: iState) {
        if (props.game != null) {
            state.playerId = props.game.player.id;
            state.activePlayerId = props.game.activePlayer;
            state.lastCallerId = props.game.lastCallerId;
            state.callList = props.game.previousCall;
            state.gotCards = props.gotCards;
            state.isLoggedIn = props.isLoggedIn;
            state.isGameOver = props.game.gameOver;
            state.isRoundRun = props.game.roundStarted;
            state.isPlayRun = props.game.playReadyToStart;
        }

        return state;
    }

    render() {

        if (this.state.isGameOver) {
            return (
                <div>
                    <div><ResultComponent game={this.props.game} postReq={this.props.postReq} /></div>
                </div>
            )
        } else {
            if (this.state.isRoundRun) {
                if (this.state.isPlayRun) {
                    return (
                        <div className={"msg-border"}>
                            <div>Aktiv játékos: {this.state.activePlayerId}</div>
                            <div>Mondás: {getCallNameListString(this.state.callList)} {this.state.lastCallerId} által.</div>
                        </div>
                    )
                } else {
                    if (this.state.activePlayerId != this.state.playerId) {
                        return (
                            <div className={"msg-border"}>
                                <div>Aktiv játékos: {this.state.activePlayerId}</div>
                                {getCallValueSum(this.props.game.previousCall) === 0 ? "" : <div>Előző mondás: {getCallNameListString(this.props.game.previousCall)}, értéke: {getCallValueSum(this.props.game.previousCall)} {this.props.game.lastCallerId} által.</div>}
                            </div>
                        )
                    } else {
                        return (
                            <></>
                        )
                    }
                }
            } else {
                if (!this.state.gotCards && this.state.isLoggedIn) {
                    return (
                        <div className={"msg-border"}>
                            A leosztás még nem történt meg!
                        </div>
                    )
                } else {
                    return (
                        <></>
                    )
                }
            }
        }
    }
}