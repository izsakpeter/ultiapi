import React = require("react");
import { Game } from "../model/game";

export class ErrorComponent extends React.Component<{gotCards: boolean, isLoggedIn: boolean}, {gotCards: boolean, isLoggedIn: boolean}> {

    constructor(props){
        super(props)

        this.state = {
            gotCards: true, 
            isLoggedIn: false
        }
    }


    static getDerivedStateFromProps(props: {gotCards: boolean, isLoggedIn: boolean, game: Game }, state: { gotCards: boolean, isLoggedIn: boolean}) {
        state.gotCards = props.gotCards;
        state.isLoggedIn = props.isLoggedIn;
        return state;
    }

    render() {

        if (!this.state.gotCards && this.state.isLoggedIn){
            return (
                <div>
                    A leosztás még nem történt meg!
                </div>
            )
        } else {
            return (
               <></>
            )
        }
    }
}