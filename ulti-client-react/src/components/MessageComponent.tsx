import { Button } from "@blueprintjs/core";
import React = require("react");
import { isThisTypeNode } from "typescript";
import { getCallNameList, getCallValue, getCallValueSum } from "../helper/callHandler";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";
import { ResultComponent } from "./ResultComponent";

export class MessageComponent extends React.Component<{ game: Game, gotCards: boolean, isLoggedIn: boolean, postReq: (reqObj: RequestModel) => void }, { playerId: number, activePlayerId: number, lastCallerId: number, callList: Array<number>, gotCards: boolean, isLoggedIn: boolean, isGameOver: boolean, isRoundRun: boolean, isPlayRun: boolean }> {

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

    static getDerivedStateFromProps(props: { game: Game, gotCards: boolean, isLoggedIn: boolean }, state: { playerId: number, activePlayerId: number, lastCallerId: number, callList: Array<number>, gotCards: boolean, isLoggedIn: boolean, isGameOver: boolean, isRoundRun: boolean, isPlayRun: boolean }) {
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
                <div className={"msg-border"}>
                    <div>Gameover</div>
                    <div><ResultComponent game={this.props.game} /></div>
                    <div><Button text="kész a következő játékra" onClick={() => this.readyButtonAction((this.state.playerId))} /></div>
                </div>
            )
        } else {
            if (this.state.isRoundRun) {
                if (this.state.isPlayRun) {
                    return (
                        <div className={"msg-border"}>
                            <div>Aktiv játékos: {this.state.activePlayerId}</div>
                            <div>Mondás: {getCallNameList(this.state.callList)} {this.state.lastCallerId} által.</div>
                        </div>
                    )
                } else {
                    if (this.state.activePlayerId != this.state.playerId) {
                        return (
                            <div className={"msg-border"}>
                                <div>Aktiv játékos: {this.state.activePlayerId}</div>
                                <div>Előző mondás: {getCallNameList(this.state.callList)}, értéke: {getCallValueSum(this.state.callList)} {this.state.lastCallerId} által.</div>
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

    readyButtonAction(playerId: number) {
        let reqObj: RequestModel = {
            dest: "newgame",
            id: playerId
        }

        this.props.postReq(reqObj);
    }
}