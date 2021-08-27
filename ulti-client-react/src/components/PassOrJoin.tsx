import React = require("react");
import { Request } from "../helper/request";
import { Game } from "../model/game";

export class PassOrJoin extends React.Component<{ game: Game, onSetGame: (target: string) => void }, {}> {

    constructor(props) {
        super(props)

        this.onPass = this.onPass.bind(this);
        this.onJoin = this.onJoin.bind(this);
    }

    render() {
        return (
            <div>
                <button onClick={this.onPass}>pass</button>
                <button onClick={this.onJoin}>felvesz</button>
            </div>
        )
    }

    async onPass(event) {
        const target = `/join?id=` + this.props.game.player.id + `&isjoin=` + false;
        this.props.onSetGame(target);
    }

    async onJoin(event) {
        const target = `/join?id=` + this.props.game.player.id + `&isjoin=` + true;
        this.props.onSetGame(target);
    }
}