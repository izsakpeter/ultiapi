import * as React from 'react';
import { Game } from '../model/game';
import axios, { AxiosRequestConfig } from "axios";
import { Table } from './Table';
import { Request } from '../helper/request';

export default class App extends React.Component<{}, { username: string, gotCards: boolean, game: Game, isWrongLogin: boolean }> {

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

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        ID:
                        <input type="text" value={this.state.username} onChange={this.handleChange} />
                    </label>
                    <input type="submit" value="Submit" />
                </form>
                <ErrorComp isWrongLogin={this.state.isWrongLogin} />
                <Table gotCards={this.state.gotCards} game={this.state.game} onSetGame={this.setStartingValue}/>
            </div>
        );
    }

    handleChange(event) {
        this.setState({ username: event.target.value });
    }

    async handleSubmit(event) {

        event.preventDefault();

        const target = `/start?id=` + this.state.username;
        await this.setStateFromRequest(target);
    }

    async setStateFromRequest(target: string) {
        const res = await Request(target);
        if (res != null) {
            this.setState({ game: res, gotCards: true, isWrongLogin: false });
        } else {
            this.setState({ gotCards: false, isWrongLogin: true });
        }
    }

    setStartingValue = (target: string): Promise<void> => {
        console.log("targetURL: " + target);
        return this.setStateFromRequest(target);
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