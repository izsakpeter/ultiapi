import { Button } from "@blueprintjs/core";
import React from "react";
import { getCallName } from "../helper/callHandler";
import { getCardSource } from "../helper/cardHandler";
import { GameOld } from "../model/gameOld";
import { RequestModel } from "../model/requestModel";

interface iProps {
    game: GameOld,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    playerId: number
}

export class ResultComponent extends React.Component<iProps, iState> {

    constructor(props: any) {
        super(props)

        this.state = {
            playerId: 0
        }
    }

    static getDerivedStateFromProps(props: iProps, state: iState) {
        state.playerId = props.game.player.id;

        return state;
    }

    render() {
        return (
            <div className="result-container">
                <div className="result-firstcolumn">
                    <div className="result-table-border">
                        <div>Eredmény</div>
                        <div>{this.renderResult()}</div>
                    </div>

                    <div className="result-table-border">
                        <div>Pontszámok</div>
                        <div>{this.renderScores()}</div>
                    </div>

                    <div>
                        <div>Talon</div>
                        <div>{this.renderTalon()}</div>
                    </div>

                    <div>
                        <div><Button text="következő" onClick={() => this.readyButtonAction((this.state.playerId))} /></div>
                    </div>

                </div>

                <div className="align-center">
                    <div>Ütések</div>
                    <div>{this.renderStrikes()}</div>
                </div>
            </div>
        )
    }

    renderResult() {

        let result = [];
        for (let i = 0; i < this.props.game.resultList.length; i++) {
            result.push(<div key={this.props.game.resultList[i].id}>{this.props.game.resultList[i].playerId} - {getCallName(this.props.game.resultList[i].callId)}: {this.props.game.resultList[i].success ? "sikeres" : "bukott"} {this.props.game.resultList[i].comment === "" ? "" : "(" + this.props.game.resultList[i].comment + ")"}</div>);
        }

        return (
            <div>{result}</div>
        )
    }

    renderScores() {

        let scores = [];
        for (let i = 0; i < this.props.game.scores.length; i++) {
            scores.push(<tr key={this.props.game.scores[i].id}><td>{this.props.game.scores[i].id}</td><td>{this.props.game.scores[i].sumScore}</td><td>{this.props.game.scores[i].lastPartyScore}</td></tr>);
        }

        return (
            <div>
                <table>
                    <tbody>
                        <tr>
                            <td>Név</td>
                            <td>Összesen</td>
                            <td>Mostani</td>
                        </tr>
                        {scores}
                    </tbody>
                </table>
            </div>
        )
    }

    renderTalon() {

        let talon = <div><img alt="card" src={getCardSource(this.props.game.talon[0].id)} className="strike-button-card" /><img alt="card" src={getCardSource(this.props.game.talon[1].id)} className="strike-button-card" /></div>

        return (
            <div>
                <div>{talon}</div>
            </div>
        )
    }

    renderStrikes() {

        let strikes1Col = [];
        let strikes2Col = [];
        let colLength: number = 5;

        for (let i = 0; i < this.props.game.strikeList.length; i++) {

            if (this.props.game.strikeList[i].strikeList.length > colLength) {

                let tmp = [];

                for (let j = 0; j < colLength; j++) {
                    tmp.push(
                        <div key={this.props.game.strikeList[i].strikeList[j].round}>
                            kör:{this.props.game.strikeList[i].strikeList[j].round}
                            <img alt="card" src={getCardSource(this.props.game.strikeList[i].strikeList[j].card1Id)} className="strike-button-card" />
                            <img alt="card" src={getCardSource(this.props.game.strikeList[i].strikeList[j].card2Id)} className="strike-button-card" />
                            <img alt="card" src={getCardSource(this.props.game.strikeList[i].strikeList[j].card3Id)} className="strike-button-card" />
                        </div>);
                }

                strikes1Col.push(<div key={i}>{tmp}</div>);

                tmp = [];

                for (let j = colLength; j < this.props.game.strikeList[i].strikeList.length; j++) {
                    tmp.push(
                        <div key={this.props.game.strikeList[i].strikeList[j].round}>
                            kör:{this.props.game.strikeList[i].strikeList[j].round}
                            <img alt="card" src={getCardSource(this.props.game.strikeList[i].strikeList[j].card1Id)} className="strike-button-card" />
                            <img alt="card" src={getCardSource(this.props.game.strikeList[i].strikeList[j].card2Id)} className="strike-button-card" />
                            <img alt="card" src={getCardSource(this.props.game.strikeList[i].strikeList[j].card3Id)} className="strike-button-card" />
                        </div>);
                }

                strikes2Col.push(<div key={i}>{tmp}</div>);

            } else {

                let tmp = [];

                for (let j = 0; j < this.props.game.strikeList[i].strikeList.length; j++) {
                    tmp.push(
                        <div key={this.props.game.strikeList[i].strikeList[j].round}>
                            kör:{this.props.game.strikeList[i].strikeList[j].round}
                            <img alt="card" src={getCardSource(this.props.game.strikeList[i].strikeList[j].card1Id)} className="strike-button-card" />
                            <img alt="card" src={getCardSource(this.props.game.strikeList[i].strikeList[j].card2Id)} className="strike-button-card" />
                            <img alt="card" src={getCardSource(this.props.game.strikeList[i].strikeList[j].card3Id)} className="strike-button-card" />
                        </div>);
                }

                strikes1Col.push(<div key={i}>{tmp}</div>);

            }

        }

        return (
            <div className="flex-row">
                <div className="align-center">
                    <div>{this.props.game.strikeList[0].playerId} ütése</div>
                    <div className="flex-row">
                        <div>{strikes1Col[0]}</div>
                        <div>{strikes2Col[0]}</div>
                    </div>
                </div>

                <div className="align-center">
                    <div>{this.props.game.strikeList[1].playerId} ütése</div>
                    <div className="flex-row">
                        <div>{strikes1Col[1]}</div>
                        <div>{strikes2Col[1]}</div>
                    </div>
                </div>

                <div className="align-center">
                    <div>{this.props.game.strikeList[2].playerId} ütése</div>
                    <div className="flex-row">
                        <div>{strikes1Col[2]}</div>
                        <div>{strikes2Col[2]}</div>
                    </div>
                </div>
            </div>
        )
    }

    readyButtonAction(playerId: number) {
        let reqObj: RequestModel = {
            dest: "newgame",
            id: playerId
        }

        this.props.postReq(reqObj);
    }
}