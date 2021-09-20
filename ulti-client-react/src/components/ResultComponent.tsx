import React = require("react");
import { getCallName } from "../helper/callHandler";
import { GetCardSource } from "../helper/cardHandler";
import { Game } from "../model/game";

export class ResultComponent extends React.Component<{ game: Game }, {}> {

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
            strikes.push(<div key={i}>tmp</div>);
        }

        return (
            <div>
                <div>Eredmény</div>
                <div>{result}</div>
                <div>{strikes}</div>
            </div>

        )
    }
}