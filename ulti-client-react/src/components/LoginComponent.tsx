import { H1 } from "@blueprintjs/core";
import React from "react";
import { getSessionId } from "../helper/loginHandler";
import { RequestModel } from "../model/requestModel";

interface iProps {
    postReq: (reqObj: RequestModel) => void,
}

interface iState {
    username: string,
    password: string
}

export class LoginComponent extends React.Component<iProps, iState> {

    constructor(props: any) {
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
                    </form>
                </div>

                <img alt="card" src={require('../resources/img/cards/hatlap.png')}/>
            </div>
        )
    }

    usernameHandleChange(event: { target: { value: any; }; }) {
        this.setState({ username: event.target.value });
    }

    passwordHandleChange(event: { target: { value: any; }; }) {
        this.setState({ password: event.target.value });
    }

    async handleSubmit(event: { preventDefault: () => void; }) {

        let sessionId = -1;

        sessionId = getSessionId(this.state.username, this.state.password);

        if (sessionId !== -1) {
            event.preventDefault();

            let reqObj: RequestModel = {
                dest: "start",
                id: sessionId
            }

            this.props.postReq(reqObj);
        }
    }
}