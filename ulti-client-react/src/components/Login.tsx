import * as React from 'react';
import { Game } from '../model/game';
import axios, { AxiosRequestConfig } from "axios";

export default class Login extends React.Component<{}, { username: string, gotCards: boolean, game: Game }> {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            gotCards: false,
            game: null
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({ username: event.target.value });
    }

    handleSubmit(event) {

        if (this.state.username === "admin") {

            let configuration: AxiosRequestConfig = {
                timeout: 10000
            };

            configuration.baseURL = "http://localhost:8888";

            const target = `/start?id=1`;

            axios.get<Game>(target, configuration)
                .then(respone => {
                    const gameRes = respone.data;
                    this.setState({ game: gameRes });

                    console.log(this.state.game.player.id + " idididiidididiididididididididiididididididi")

                }).catch(error => {
                    console.log(error);
                });


            this.setState({ gotCards: true });
        }

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

                <ShowTable gotCards={this.state.gotCards} />

            </form>
        );
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