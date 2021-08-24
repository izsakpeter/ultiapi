import React = require("react");
import { Request } from "../helper/request";
import { Game } from "../model/game";

export class PassOrJoin extends React.Component<{ game: Game }, {}> {

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
        await this.setStateFromRequest(target);
    }

    async onJoin(event) {
        const target = `/join?id=` + this.props.game.player.id + `&isjoin=` + true;
        await this.setStateFromRequest(target);
    }

    async setStateFromRequest(target: string) {
        const res = await Request(target);

        if (res != null) {
            this.setState({ game: res });
        }
    }
}