import { Button } from "@blueprintjs/core";
import React = require("react");
import { getCallNameListString, getCallValueSum } from "../helper/callHandler";
import { GetCardSource } from "../helper/cardHandler";
import { getUsernameById } from "../helper/loginHandler";
import { Call } from "../model/call";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";

interface iProps {
    game: Game,
     postReq: (reqObj: RequestModel) => void
}

interface iState {
    lastCallerId: number,
     callList: Array<Call>
}

export class PassOrJoin extends React.Component<iProps, iState> {

    constructor(props) {
        super(props)

        this.state = {
            lastCallerId: 0,
            callList: []
        }

        this.onPass = this.onPass.bind(this);
        this.onJoin = this.onJoin.bind(this);
    }

    static getDerivedStateFromProps(props: iProps, state: iState) {
        state.lastCallerId = props.game.lastCallerId;
        state.callList = props.game.previousCall;

        return state;
    }

    render() {

        let talon = <div><img src={GetCardSource(this.props.game.talon[0].id)} className="talon-card" /><img src={GetCardSource(this.props.game.talon[1].id)} className="talon-card" /></div>

        return (
            <div>
                <div className={"p-o-j-panel"}>
                    <div>Előző mondás: {getCallNameListString(this.state.callList)}, értéke: {getCallValueSum(this.state.callList)} {getUsernameById(this.state.lastCallerId)} által.</div>
                    <Button className={"p-o-j-button"} onClick={this.onPass}>passz</Button>
                    <Button className={"p-o-j-button"} onClick={this.onJoin}>felvesz</Button>
                </div>
                <div className={"talon-pos"}>{talon}</div>
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