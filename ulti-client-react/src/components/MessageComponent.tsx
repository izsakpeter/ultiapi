import React from "react";
import { getCallNameListString, getCallValueSum } from "../helper/callHandler";
import { getUsernameById } from "../helper/loginHandler";
import { Call } from "../model/call";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";

interface iProps {
    game: Game,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    playerId: number,
    activePlayerId: number,
    lastCallerId: number,
    callList: Array<Call>,
    isRoundRun: boolean,
    isPlayRun: boolean
}

export class MessageComponent extends React.Component<iProps, iState> {

    constructor(props: any) {
        super(props)

        this.state = {
            playerId: 0,
            activePlayerId: 0,
            lastCallerId: 0,
            callList: [],
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
            state.isRoundRun = props.game.roundStarted;
            state.isPlayRun = props.game.playReadyToStart;
        }

        return state;
    }

    render() {
        if (this.state.isRoundRun) {
            if (this.state.isPlayRun) {
                return (
                    <div className={"msg-border"}>
                        <div className="msg-text-middle">
                            <div>Aktiv játékos: {getUsernameById(this.state.activePlayerId)}</div>
                            <div>Mondás: {getCallNameListString(this.state.callList)} {getUsernameById(this.state.lastCallerId)} által.</div>
                        </div>
                    </div >
                )
            } else {
                if (this.state.activePlayerId !== this.state.playerId) {
                    return (
                        <div className={"msg-border"}>
                            <div className="msg-text-middle">
                                <div>Aktiv játékos: {getUsernameById(this.state.activePlayerId)}</div>
                                {getCallValueSum(
                                    this.props.game.previousCall) === 0 ? "" : <div>Előző mondás: {getCallNameListString(this.props.game.previousCall)},
                                        értéke: {getCallValueSum(this.props.game.previousCall)} {getUsernameById(this.props.game.lastCallerId)} által.</div>}
                            </div>
                        </div>
                    )
                } else {
                    return (
                        <></>
                    )
                }
            }
        } else {
            return (
                <></>
            )
        }
    }
}