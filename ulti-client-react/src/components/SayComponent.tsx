import { Button } from "@blueprintjs/core";
import React = require("react");
import { getColorIdByCallItem, have20, have40 } from "../helper/callHandler";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";

interface iProps {
    game: Game,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    showPanel: boolean,
    is40Checked: boolean,
    is120Checked: boolean,
    is220Checked: boolean,
    is320Checked: boolean,
    colorId: number,
    isFirstTurn: boolean,
    isSaid: boolean
}

export class SayComponent extends React.Component<iProps, iState>{

    constructor(props) {
        super(props);

        this.state = {
            showPanel: false,
            is40Checked: false,
            is120Checked: false,
            is220Checked: false,
            is320Checked: false,
            colorId: 0,
            isFirstTurn: false,
            isSaid: true
        }

        this.onChoose40 = this.onChoose40.bind(this);
        this.onChoose120 = this.onChoose120.bind(this);
        this.onChoose220 = this.onChoose220.bind(this);
        this.onChoose320 = this.onChoose320.bind(this);
        this.okButtonHandler = this.okButtonHandler.bind(this);
    }

    static getDerivedStateFromProps(props: iProps, state: iState) {
        if (props.game != null && props.game.playReadyToStart) {
            state = {
                ...state,
                isFirstTurn: props.game.firstTurn,
                colorId: getColorIdByCallItem(props.game.previousCall[0].callId),
                isSaid: props.game.player.said
            };
        }

        return state;
    }

    render() {
        if (this.state.isFirstTurn && this.props.game.player.hand.length === 10 && this.props.game.activePlayer == this.props.game.player.id  && !this.state.isSaid) {
            return (
                <div>
                    <div><Button text="mondás" onClick={() => this.clickHandler(this.state.showPanel)} /></div>
                    <div>{this.sayList(this.state.showPanel, this.props.game)}</div>
                </div>
            )
        } else {
            return (
                <></>
            )
        }
    }

    clickHandler(showPanel: boolean) {
        this.setState({ showPanel: !showPanel })
    }

    sayList(showPanel: boolean, game: Game) {
        if (showPanel) {

            if (game.lastCallerId === game.activePlayer) {
                return (
                    <div className={"say-border"}>
                        <div><input type="checkbox" name="40" disabled={this.disable40()} onChange={this.onChoose40} /> 40 </div>
                        <div><input type="checkbox" name="120" disabled={this.disable20(1)} onChange={this.onChoose120} /> 20 </div>
                        <div><input type="checkbox" name="220" disabled={this.disable20(2)} onChange={this.onChoose220} /> 2x20</div>
                        <div><input type="checkbox" name="320" disabled={this.disable20(3)} onChange={this.onChoose320} /> 3x20</div>
                        <div><Button text="ok" onClick={this.okButtonHandler} /></div>
                    </div>
                )
            } else {
                return (
                    <div className={"say-border"}>
                        <div><input type="checkbox" name="40" disabled={this.disable40()} onChange={this.onChoose40} /> 40 </div>
                        <div><input type="checkbox" name="120" disabled={this.disable20(1)} onChange={this.onChoose120} /> 20 </div>
                        <div><input type="checkbox" name="220" disabled={this.disable20(2)} onChange={this.onChoose220} /> 2x20</div>
                        <div><input type="checkbox" name="320" disabled={this.disable20(3)} onChange={this.onChoose320} /> 3x20</div>
                        <div><Button text="ok" onClick={this.okButtonHandler} /></div>
                    </div>
                )
            }
        } else {
            return (
                <></>
            )
        }
    }

    disable40(): boolean {
        return have40(this.state.colorId, this.props.game)
    }

    disable20(counter: number): boolean {
        return !(have20(this.state.colorId, this.props.game) === counter);
    }

    okButtonHandler(event) {
        if (this.state.is40Checked || this.state.is120Checked || this.state.is220Checked || this.state.is320Checked) {

            let reqObj: RequestModel = {
                dest: "say",
                id: this.props.game.player.id,
                have40: this.state.is40Checked,
                have120: this.state.is120Checked,
                have220: this.state.is220Checked,
                have320: this.state.is320Checked
            }

            this.props.postReq(reqObj);
        }
    }

    onChoose40(event) {
        this.setState({ is40Checked: event.target.checked });
    }

    onChoose120(event) {
        this.setState({ is120Checked: event.target.checked });
    }

    onChoose220(event) {
        this.setState({ is220Checked: event.target.checked });
    }

    onChoose320(event) {
        this.setState({ is320Checked: event.target.checked });
    }
}

