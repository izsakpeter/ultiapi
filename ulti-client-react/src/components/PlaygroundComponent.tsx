import React = require("react");
import { Game } from "../model/game";


export class PlaygroundComponent extends React.Component<{game: Game},{}> {

    constructor(props){
        super(props);
    }

    render(){

        if(!this.props.game.playReadyToStart){
            return(
                <></>
            )
        } else {
            return(
                <div>harctér</div>
            )
        }

        
    }

}