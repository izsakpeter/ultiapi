import { Button } from "@blueprintjs/core";
import React = require("react");
import { getCallNameListString, getCallValueSum } from "../helper/callHandler";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";

export class PassOrJoin extends React.Component<{ game: Game, postReq: (reqObj: RequestModel) => void }, {lastCallerId: number, callList: Array<number> }> {

    constructor(props) {
        super(props)

        this.state = {
            lastCallerId: 0,
            callList: []
        }

        this.onPass = this.onPass.bind(this);
        this.onJoin = this.onJoin.bind(this);
    }

    static getDerivedStateFromProps(props: { game: Game }, state: {lastCallerId: number, callList: Array<number> }) {
        state.lastCallerId = props.game.lastCallerId;
        state.callList = props.game.previousCall;

        return state;
    }

    render() {
        return (
            <div className={"p-o-j-frame"}>
                <div>Előző mondás: {getCallNameListString(this.state.callList)}, értéke: {getCallValueSum(this.state.callList)} {this.state.lastCallerId} által.</div>
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
            isjoin: true
        }

        this.props.postReq(reqObj);
    }
}