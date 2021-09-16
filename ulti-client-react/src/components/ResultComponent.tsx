import React = require("react");
import { getCallName } from "../helper/callHandler";
import { Game } from "../model/game";

export class ResultComponent extends React.Component<{ game: Game }, {}> {

    render() {

        let result = [];
        for (let i = 0; i < this.props.game.resultList.length; i++) {
            result.push(<div key={this.props.game.resultList[i].id}>{getCallName(this.props.game.resultList[i].callId)}: {this.props.game.resultList[i].success ? "sikeres" : "bukta"} {this.props.game.resultList[i].comment === "" ? "" : "(" + this.props.game.resultList[i].comment + ")"}</div>);
        }

        return (
            <div>
                <div>Eredmény</div>
                <div>{result}</div>
            </div>

        )
    }
}