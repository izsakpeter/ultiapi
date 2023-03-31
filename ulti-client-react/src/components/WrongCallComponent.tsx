import React from "react";
import { Game } from "../model/game";

interface iProps {
    game: Game
}

interface iState {
}

export class WronCallComponent extends React.Component<iProps, iState> {
    
    render() {

        if (!this.props.game.player.callOk) {
            return (<>Rossz hivás</>)
        } else {
            return (<></>);
        }
    }
}