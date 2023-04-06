import { Button, Radio } from "@blueprintjs/core";
import React from "react";
import { getCardSource } from "../helper/cardHandler";
import { Constants } from "../helper/constants";
import { GameOld } from "../model/gameOld";
import { RequestModel } from "../model/requestModel";

interface iProps {
    game: GameOld,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    value: number,
    id: number
}

export class StartingValue extends React.Component<iProps, iState> {

    constructor(props: any) {
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

        if (this.props.game.activePlayer !== this.props.game.player.id || this.props.game.startingValue !== 0 || this.props.game.gameOver)
            return <></>;

        let talon = <div><img alt="card" src={getCardSource(this.props.game.talon[0].id)} className="talon-card" /><img alt="card" src={getCardSource(this.props.game.talon[1].id)} className="talon-card" /></div>

        return (
            <div>
                <div className={"sv-border"}>
                    <div>
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
                <div className={"talon-pos"}>{talon}</div>
            </div>
        )
    }

    onChangeValue(event: any) {
        this.setState({ value: event.target.value });
    }

    setStartingValue() {
        if (this.state.value > 0) {

            let reqObj: RequestModel = {
                dest: "startingvalue",
                playerId: this.props.game.player.id,
                value: this.state.value
            }

            this.props.postReq(reqObj);

        } else {
            console.log("Válassz szint!");
        }
    }
}