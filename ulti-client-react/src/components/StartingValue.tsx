import { Button, Radio } from "@blueprintjs/core";
import React = require("react");
import { Request } from "../helper/request";
import { Call } from "../model/call";
import { Game } from "../model/game";

export class StartingValue extends React.Component<{ game: Game, onSetGame: (target: string) => void }, { value: number, id: number }> {

    constructor(props) {
        super(props);

        this.state = {
            value: 0,
            id: -1
        }

        this.onChangeValue = this.onChangeValue.bind(this);
        this.setStartingValue = this.setStartingValue.bind(this);
    }

    static getDerivedStateFromProps(props: { game: Game }, state: { id: number }) {
        state.id = props.game.player.id;
        return state;
    }

    render() {

        if (this.props.game.activePlayer != this.props.game.player.id || this.props.game.startingValue != 0)
            return <></>;

        return (
            <div className={"border"}>
                <div>
                    <Radio name="cv" label="MAKK" value={Call.MAKK_ID} onClick={this.onChangeValue} />
                    <Radio name="cv" label="ZOLD" value={Call.ZOLD_ID} onClick={this.onChangeValue} />
                    <Radio name="cv" label="TOK" value={Call.TOK_ID} onClick={this.onChangeValue} />
                    <Radio name="cv" label="PIROS" value={Call.PIROS_ID} onClick={this.onChangeValue} />
                </div>
                <div><Button className={"ok-button"} onClick={this.setStartingValue}>ok</Button></div>
            </div>
        )
    }

    onChangeValue(event) {
        this.setState({ value: event.target.value });
    }

    setStartingValue(event) {
        if (this.state.value > 0){
            const target = `/startingvalue?id=` + this.state.id + `&value=` + this.state.value;
            this.props.onSetGame(target);
        } else {
            console.log("Válasz szint!");
        }
    }
}