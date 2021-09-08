import React = require("react");
import { Game } from "../model/game";


export class StrikeComponent extends React.Component <{game: Game},{}> {

    constructor(props){
        super(props);
    }

    static getDerivedStateFromProps(props: { game: Game }, state: {}) {
        return state;
    }

    render(){
        if (this.props.game.player.strikes.length > 0){
            return(
                <>Ütések</>
            )
        } else {
            return(
                <></>
            )
        }
    }
}

