import { Button } from "@blueprintjs/core";
import React = require("react");
import { getCallName, getCallValue, getCallValueSum } from "../helper/callHandler";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";

export class MessageComponent extends React.Component<{ game: Game, gotCards: boolean, isLoggedIn: boolean, postReq: (reqObj: RequestModel) => void }, { playerId: number, activePlayerId: number, lastCallerId: number, callList: Array<number>, gotCards: boolean, isLoggedIn: boolean, isGameOver: boolean }> {

    constructor(props) {
        super(props)

        this.state = {
            playerId: 0,
            activePlayerId: 0,
            lastCallerId: 0,
            callList: [],
            gotCards: true,
            isLoggedIn: false,
            isGameOver: false
        }
    }

    static getDerivedStateFromProps(props: { game: Game, gotCards: boolean, isLoggedIn: boolean }, state: { playerId: number, activePlayerId: number, lastCallerId: number, callList: Array<number>, gotCards: boolean, isLoggedIn: boolean, isGameOver: boolean}) {
        if (props.game != null) {
            state.playerId = props.game.player.id;
            state.activePlayerId = props.game.activePlayer;
            state.lastCallerId = props.game.lastCallerId;
            state.callList = props.game.previousCall;
            state.gotCards = props.gotCards;
            state.isLoggedIn = props.isLoggedIn;
            state.isGameOver = props.game.gameOver;
        }

        return state;
    }

    render() {

        if (this.state.isGameOver){
            return (
                <div className={"msg-border"}>
                    <div>Gameover</div>
                    <div><Button text="kész a következő játékra" onClick={() => this.readyButtonAction((this.state.playerId))}/></div>
                </div>
            )
        } else {
            if (!this.state.gotCards && this.state.isLoggedIn) {
                return (
                    <div className={"msg-border"}>
                        A leosztás még nem történt meg!
                    </div>
                )
            } else if (this.state.activePlayerId != this.state.playerId) {
                if (this.state.callList.length === 0) {
                    return (
                        <div className={"msg-border"}>
                            <div>Aktiv játékos: {this.state.activePlayerId}</div>
                        </div>
                    )
                } else {
                    return (
                        <div className={"msg-border"}>
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

    readyButtonAction(playerId: number){
         let reqObj: RequestModel = {
            dest: "newgame",
            id: playerId
        }

        this.props.postReq(reqObj);
    }
}