import { Button } from "@blueprintjs/core";
import React = require("react");
import { Game } from "../model/game";


export class SayComponent extends React.Component<{ game: Game }, { showPanel: boolean }>{

    constructor(props) {
        super(props);

        this.state = {
            showPanel: false
        }
    }

    render() {
        return (
            <div>
                <div><Button text="mondás" onClick={() => this.clickHandler(this.state.showPanel)} /></div>
                <div>{this.sayList(this.state.showPanel, this.props.game)}</div>
            </div>
        )
    }

    clickHandler(showPanel: boolean) {
        this.setState({ showPanel: !showPanel })
    }

    sayList(showPanel: boolean, game: Game) {
        if (showPanel) {

            if (game.lastCallerId === game.activePlayer) {
                return (
                    <div>
                        <div><input type="checkbox" disabled={this.isCheckBoxDisable("40")} onChange={this.onChoose40} /> 40 </div>
                        <div><input type="checkbox" /> 20 </div>
                        <div><input type="checkbox" /> 2x20</div>
                        <div><input type="checkbox" /> 3x20</div>
                        <div><Button text="ok" onClick={this.okButtonHandler}/></div>
                    </div>
                )
            } else {
                return (
                    <div>
                        <div><input type="checkbox" /> 40 </div>
                        <div><input type="checkbox" /> 20 </div>
                        <div><input type="checkbox" /> 2x20</div>
                        <div><input type="checkbox" /> 3x20</div>
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

    okButtonHandler(event){
        
    }
    
    isCheckBoxDisable(arg0: string): boolean {
        throw new Error("Method not implemented.");
    }

    onChoose40(event) {
        
    }
}

