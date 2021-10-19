import { Button } from "@blueprintjs/core";
import React = require("react");
import { GetCardSource } from "../helper/cardHandler";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";
import { Strike } from "../model/strike";

interface iProps {
    game: Game,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    strikes: Array<Strike>,
    showStrikes: boolean
}

export class OperationsComponent extends React.Component<iProps, iState> {

    constructor(props) {
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
                    <Button onClick={this.changeOrderHandler} text="rendez" />
                    <Button onClick={() => this.showStrikesHandler(this.state.showStrikes)} text="ütések" disabled={this.isShowStrikeButtonDisabled()} />
                    <Button text="terít" />
                    <Button text="bedob" />
                    <Button text="feladás" />
                    <Button text="mondás számoló" />

                    <div>{StrikeList(this.state.strikes, this.state.showStrikes)}</div>

                </div>
            )
        } else {
            return <></>;
        }
    }

    async changeOrderHandler(event) {
        event.preventDefault();

        let reqObj: RequestModel = {
            dest: "order",
            id: this.props.game.player.id,
            colorOrder: !this.props.game.player.colorOrder
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

function StrikeList(strikes: Array<Strike>, showStrikes: boolean) {

    if (showStrikes) {
        const strikeList = strikes.map((strike) => <div key={strike.round}>kör:{strike.round}<img src={GetCardSource(strike.card1Id)} className="strike-button-card" /><img src={GetCardSource(strike.card2Id)} className="strike-button-card" /><img src={GetCardSource(strike.card3Id)} className="strike-button-card" /></div>)

        return (
            <div>
                {strikeList}
            </div>
        )
    } else {
        return (
            <></>
        )
    }
}