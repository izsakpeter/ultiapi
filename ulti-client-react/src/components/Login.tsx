import * as React from 'react';
import { Game } from '../model/game';
import axios, { AxiosRequestConfig } from "axios";

export default class Login extends React.Component<{}, { username: string, gotCards: boolean, game: Game, isWrongLogin:boolean }> {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            gotCards: false,
            game: null,
            isWrongLogin: false
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({ username: event.target.value });
    }

    handleSubmit(event) {

        let configuration: AxiosRequestConfig = {
            timeout: 10000
        };

        configuration.baseURL = "http://localhost:8888";

        const target = `/start?id=` + this.state.username;

        axios.get<Game>(target, configuration)
            .then(respone => {
                const gameRes = respone.data;
                this.setState({ game: gameRes });

                console.log(this.state.game.player.id + " idididiidididiididididididididiididididididi")

                this.setState({ gotCards: true, isWrongLogin: false });

            }).catch(error => {
                this.setState({ gotCards: false, isWrongLogin: true });
                console.log(error);
            });

        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    ID:
                    <input type="text" value={this.state.username} onChange={this.handleChange} />
                </label>
                <input type="submit" value="Submit" />

                <ErrorComp isWrongLogin={this.state.isWrongLogin}/>

                <ShowTable gotCards={this.state.gotCards} game={this.state.game} />
               

            </form>
        );
    }
}

function ErrorComp(props) {
    if (props.isWrongLogin) {
        return (
            <div>
               ERROR
            </div>
        )
    } else {
        return null;
    }
}

function ShowTable(props) {
    if (props.gotCards) {
        return (
            
            <div>
                AMADEUSZ
            </div>
        )
    } else {
        return null;
    }
}