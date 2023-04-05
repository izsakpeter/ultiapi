import React from "react";
import { GameOld } from "../model/gameOld";

interface iProps {
    game: GameOld
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