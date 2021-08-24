import React = require("react");
import { Request } from "../helper/request";
import { Game } from "../model/game";

export class StartingValue extends React.Component<{ game: Game }, { value: number, id: number, game: Game }> {

    constructor(props) {
        super(props);

        this.state = {
            value: 0,
            id: -1,
            game: null
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
            <div onChange={this.onChangeValue}>
                <input type="radio" value="1" name="sv" /> MAKK
                <input type="radio" value="2" name="sv" /> ZOLD
                <input type="radio" value="3" name="sv" /> TOK
                <input type="radio" value="4" name="sv" /> PIROS
                <button onClick={this.setStartingValue}>ok</button>
            </div>
        )
    }

    onChangeValue(event) {
        this.setState({ value: event.target.value });
    }

    async setStartingValue(event) {
        event.preventDefault();

        const target = `/startingvalue?id=` + this.state.id + `&value=` + this.state.value;
        await this.setStateFromRequest(target);

    }

    async setStateFromRequest(target: string) {
        const res = await Request(target);

        if (res != null) {
            this.setState({ game: res });
        }
    }
}