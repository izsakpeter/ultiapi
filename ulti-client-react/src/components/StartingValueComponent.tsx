import { Button, Radio } from "@blueprintjs/core";
import React = require("react");
import { GetCardSource } from "../helper/cardHandler";
import { Constants } from "../helper/constants";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";

interface iProps {
    game: Game,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    value: number,
    id: number
}

export class StartingValue extends React.Component<iProps, iState> {

    constructor(props) {
        super(props);

        this.state = {
            value: 1,
            id: -1
        }

        this.onChangeValue = this.onChangeValue.bind(this);
        this.setStartingValue = this.setStartingValue.bind(this);
    }

    static getDerivedStateFromProps(props: iProps, state: iState) {

        state = {
            ...state,
            id: props.game.player.id
        };

        return state;
    }

    render() {

        if (this.props.game.activePlayer != this.props.game.player.id || this.props.game.startingValue != 0 || this.props.game.gameOver)
            return <></>;

        let talon = <div><img src={GetCardSource(this.props.game.talon[0].id)} className="button-card" /><img src={GetCardSource(this.props.game.talon[1].id)} className="button-card" /></div>

        return (
            <div>
                <div className={"msg-border"}>
                    <div className={"msg-text"} >
                        <div> Válassz kezdő szint!</div>
                        <div>
                            <Radio name="sv" label={Constants.MAKK} value={Constants.MAKK_ID} onClick={this.onChangeValue} defaultChecked={true} />
                            <Radio name="sv" label={Constants.ZOLD} value={Constants.ZOLD_ID} onClick={this.onChangeValue} />
                            <Radio name="sv" label={Constants.TOK} value={Constants.TOK_ID} onClick={this.onChangeValue} />
                            <Radio name="sv" label={Constants.PIROS} value={Constants.PIROS_ID} onClick={this.onChangeValue} />
                        </div>
                    </div>
                    <div><Button className={"button-ok"} onClick={this.setStartingValue}>ok</Button></div>
                </div>
                <div className={"talon-poz"}>{talon}</div>
            </div>
        )
    }

    onChangeValue(event) {
        this.setState({ value: event.target.value });
    }

    setStartingValue(event) {
        if (this.state.value > 0) {

            let reqObj: RequestModel = {
                dest: "startingvalue",
                id: this.props.game.player.id,
                value: this.state.value
            }

            this.props.postReq(reqObj);

        } else {
            console.log("Válassz szint!");
        }
    }
}