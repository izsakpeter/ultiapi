import { Button } from "@blueprintjs/core";
import React = require("react");
import { getCallName } from "../helper/callHandler";
import { GetCardSource } from "../helper/cardHandler";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";

export class ResultComponent extends React.Component<{ game: Game, postReq: (reqObj: RequestModel) => void }, { playerId: number }> {

    constructor(props) {
        super(props)

        this.state = {
            playerId: 0
        }
    }

    static getDerivedStateFromProps(props: { game: Game }, state: { playerId: number }) {

        state.playerId = props.game.player.id;


        return state;
    }


    render() {
        let result = [];
        for (let i = 0; i < this.props.game.resultList.length; i++) {
            result.push(<div key={this.props.game.resultList[i].id}>{getCallName(this.props.game.resultList[i].callId)}: {this.props.game.resultList[i].success ? "sikeres" : "bukta"} {this.props.game.resultList[i].comment === "" ? "" : "(" + this.props.game.resultList[i].comment + ")"}</div>);
        }

        let strikes = [];
        for (let i = 0; i < this.props.game.strikeList.length; i++) {
            let tmp = [];
            for (let j = 0; j < this.props.game.strikeList[i].strikeList.length; j++) {
                tmp.push(
                    <div key={this.props.game.strikeList[i].strikeList[j].round}>
                        kör:{this.props.game.strikeList[i].strikeList[j].round}
                        <img src={GetCardSource(this.props.game.strikeList[i].strikeList[j].card1Id)} className="strike-button-card" />
                        <img src={GetCardSource(this.props.game.strikeList[i].strikeList[j].card2Id)} className="strike-button-card" />
                        <img src={GetCardSource(this.props.game.strikeList[i].strikeList[j].card3Id)} className="strike-button-card" />
                    </div>);
            }
            strikes.push(<div key={i}>{tmp}</div>);
        }

        return (
            <div>
                <div className={"result-border"}>
                    <div>Eredmény</div>
                    <div>{result}</div>
                    <div><Button text="kész" onClick={() => this.readyButtonAction((this.state.playerId))} /></div>
                </div>
                <div className={"first-player-strikes"}>{strikes[0]}</div>
                <div className={"second-player-strikes"}>{strikes[1]}</div>
                <div className={"third-player-strikes"}>{strikes[2]}</div>
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