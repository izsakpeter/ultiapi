import { Button } from "@blueprintjs/core";
import React from "react";
import { GameOld } from "../model/gameOld";
import { RequestModel } from "../model/requestModel";
import { Strike } from "../model/strike";
import { StrikesComponent } from "./StrikesComponent";

interface iProps {
    game: GameOld,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    strikes: Array<Strike>,
    showStrikes: boolean
}

export class OperationsComponent extends React.Component<iProps, iState> {

    constructor(props: any) {
        super(props);

        this.state = {
            strikes: [],
            showStrikes: false
        };

        this.changeOrderHandler = this.changeOrderHandler.bind(this);
        this.showStrikesHandler = this.showStrikesHandler.bind(this);
    }

    static getDerivedStateFromProps(props: iProps, state: iState) {

        state = {
            ...state,
            strikes: props.game.player.strikes
        };

        return state;
    }

    render() {

        if (this.props.game.player.playing) {
            return (
                <div>
                    <div><Button onClick={this.changeOrderHandler} text="rendez" /></div>
                    <div><StrikesComponent strikes={this.state.strikes} /></div>
                    <div><Button text="terít" disabled={true} /></div>
                    <div><Button text="bedob" disabled={true} /></div>
                    <div><Button text="feladás" disabled={true} /></div>
                    <div><Button text="mondás számoló" disabled={true} /></div>
                </div>
            )
        } else {
            return <></>;
        }
    }

    async changeOrderHandler(event: { preventDefault: () => void; }) {
        event.preventDefault();

        let reqObj: RequestModel = {
            dest: "order",
            playerId: this.props.game.player.id,
            isColorOrder: !this.props.game.player.colorOrder
        }

        this.props.postReq(reqObj);
    }

    showStrikesHandler(showStrikes: boolean) {
        this.setState({ showStrikes: !showStrikes });
    }

    isShowStrikeButtonDisabled() {
        if (this.props.game.player.strikes.length > 0 && !this.props.game.gameOver)
            return false;

        return true;
    }
}