import { Button } from "@blueprintjs/core";
import React = require("react");
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";

export class PassOrJoin extends React.Component<{ game: Game, postReq: (reqObj: RequestModel) => void}, {}> {

    constructor(props) {
        super(props)

        this.onPass = this.onPass.bind(this);
        this.onJoin = this.onJoin.bind(this);
    }

    render() {
        return (
            <div className={"p-o-j-frame"}>
                <Button className={"p-o-j-button"} onClick={this.onPass}>passz</Button>
                <Button className={"p-o-j-button"} onClick={this.onJoin}>felvesz</Button>
            </div>
        )
    }

    async onPass(event) {

        let reqObj: RequestModel = {
            dest: "join",
            id: this.props.game.player.id,
            isjoin: false
        }

        this.props.postReq(reqObj);
    }

    async onJoin(event) {

        let reqObj: RequestModel = {
            dest: "join",
            id: this.props.game.player.id,
            isjoin: false
        }

        this.props.postReq(reqObj);
    }
}