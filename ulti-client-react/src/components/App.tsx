import * as React from 'react';
import { Game } from '../model/game';
import axios, { AxiosRequestConfig } from "axios";
import { Table } from './TableComponent';
import { Request } from '../helper/request';
import { LoginComponent } from './LoginComponent';

export default class App extends React.Component<{}, { gotCards: boolean, game: Game, isWrongLogin: boolean }> {

    constructor(props) {
        super(props);

        this.state = {
            gotCards: false,
            game: null,
            isWrongLogin: false
        }
    }

    render() {
        return (
            <div>
                <div className={"align-right"}><LoginComponent onSetGame={this.doHtttpReq}/></div>
                <ErrorComp isWrongLogin={this.state.isWrongLogin} />
                <Table gotCards={this.state.gotCards} game={this.state.game} onSetGame={this.doHtttpReq}/>
            </div>
        );
    }

    doHtttpReq = (target: string): Promise<void> => {
        return this.setStateFromRequest(target);
    }

    async setStateFromRequest(target: string) {
        console.log("targetURL: " + target);
        const res = await Request(target);
        if (res != null) {
            this.setState({ game: res, gotCards: true, isWrongLogin: false });
        } else {
            this.setState({ gotCards: false, isWrongLogin: true });
        }
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