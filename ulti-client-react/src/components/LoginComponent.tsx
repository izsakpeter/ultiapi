import { H1 } from "@blueprintjs/core";
import React = require("react");
import { getSessionId, getUsernameById } from "../helper/loginHandler";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";

interface iProps {
    postReq: (reqObj: RequestModel) => void,
    game: Game
}

interface iState {
    username: string,
    password: string
}

export class LoginComponent extends React.Component<iProps, iState> {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: ''
        }

        this.usernameHandleChange = this.usernameHandleChange.bind(this);
        this.passwordHandleChange = this.passwordHandleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    render() {

        if (this.props.game === null) {
            return (
                <div className={"login"}>
                    <div className={"login-panel"}>
                        <div><H1>Bejelentkezés</H1></div>
                        <form onSubmit={this.handleSubmit}>
                            <div>Név</div>
                            <div><input type="text" value={this.state.username} onChange={this.usernameHandleChange} /></div>
                            <div>Jelszó</div>
                            <div><input type="password" value={this.state.password} onChange={this.passwordHandleChange} /></div>
                            <div> <input type="submit" value="Bejelenkezés" /></div>
                            <div> <input type="button" value="Regisztráció" /></div>
                        </form>
                    </div>
                </div>
            )
        } else {
            return (
                <></>
            )
        }
    }

    usernameHandleChange(event) {
        this.setState({ username: event.target.value });
    }

    passwordHandleChange(event) {
        this.setState({ password: event.target.value });
    }

    async handleSubmit(event) {

        let sessionId = -1;

        sessionId = getSessionId(this.state.username, this.state.password);

        if (sessionId != -1) {
            event.preventDefault();

            let reqObj: RequestModel = {
                dest: "start",
                id: sessionId
            }

            this.props.postReq(reqObj);
        }
    }
}