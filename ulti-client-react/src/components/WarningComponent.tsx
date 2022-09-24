import React = require("react");


interface iProps {
    gotCards: boolean,
    isLoggedIn: boolean
}

interface iState {
    gotCards: boolean,
    isLoggedIn: boolean,
}

export class WarningComponent extends React.Component<iProps, iState>{

    constructor(props) {
        super(props)

        this.state = {
            gotCards: true,
            isLoggedIn: false
        }
    }


    static getDerivedStateFromProps(props: iProps, state: iState) {

        state.gotCards = props.gotCards;
        state.isLoggedIn = props.isLoggedIn;

        return state;
    }

    render() {

        if (!this.state.gotCards && this.state.isLoggedIn) {
            return (
                <div className="login">
                    <div className="warn-border">
                        <div className="warn-text-middle">A leosztás még nem történt meg!</div>
                    </div>
                </div>
            )
        } else {
            return (
                <></>
            )
        }
    }
}