import React = require("react");
import { Game } from "../model/game";

export class WronCallComponent extends React.Component<{ game: Game }, {}> {

    constructor(props) {
        super(props)
    }

    render() {

        if (!this.props.game.player.callOk) {
            return (<>Rossz hivás</>)
        } else {
            return (<></>);
        }
    }
}