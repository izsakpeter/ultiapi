import { Button } from "@blueprintjs/core";
import React = require("react");
import { getColorByCallList, have20, have40 } from "../helper/callHandler";
import { Game } from "../model/game";


export class SayComponent extends React.Component<{ game: Game }, { showPanel: boolean, is40Checked: boolean, colorId: number, isFirstTurn: boolean }>{

    constructor(props) {
        super(props);

        this.state = {
            showPanel: false,
            is40Checked: false,
            colorId: getColorByCallList(this.props.game.previousCall),
            isFirstTurn: false
        }

        this.onChoose40 = this.onChoose40.bind(this);
    }

    static getDerivedStateFromProps(props: { game: Game, }, state: { isFirstTurn: boolean }) {
        if (props.game != null) {
            state.isFirstTurn = props.game.firstTurn;
        }

        return state;
    }

    render() {
        if (this.state.isFirstTurn && this.props.game.player.hand.length === 10) {
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
                    <div>
                        <div><input type="checkbox" name="40" disabled={have40(this.state.colorId, this.props.game)} /> 40 </div>
                        <div><input type="checkbox" name="120" disabled={this.disable20(1)} /> 20 </div>
                        <div><input type="checkbox" name="220" disabled={this.disable20(2)} /> 2x20</div>
                        <div><input type="checkbox" name="320" disabled={this.disable20(3)} /> 3x20</div>
                        <div><Button text="ok" onClick={this.okButtonHandler} /></div>
                    </div>
                )
            } else {
                return (
                    <div>
                        <div><input type="checkbox" name="40" disabled={have40(this.state.colorId, this.props.game)} /> 40 </div>
                        <div><input type="checkbox" name="120" disabled={this.disable20(1)} /> 20 </div>
                        <div><input type="checkbox" name="220" disabled={this.disable20(2)} /> 2x20</div>
                        <div><input type="checkbox" name="320" disabled={this.disable20(3)} /> 3x20</div>
                        <div><Button text="ok" /></div>
                    </div>
                )
            }
        } else {
            return (
                <></>
            )
        }
    }

    disable20(counter: number): boolean {
        return !(have20(this.state.colorId, this.props.game) === counter);
    }

    okButtonHandler(event) {

    }

    onChoose40(event) {

        //console.log(this.state.colorId + " color+value " + event.target.value + "have40 " + have40(this.state.colorId, this.props.game));


        //this.setState({ is40Checked: (event.target.value && have40(this.state.colorId, this.props.game)) })
    }
}

