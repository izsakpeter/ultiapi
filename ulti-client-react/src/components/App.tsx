import * as React from 'react';
import { Game } from '../model/game';
import axios, { AxiosRequestConfig } from "axios";
import { Table } from './TableComponent';
import { Request, StartPostRequest, StatusPostRequest } from '../helper/request';
import { LoginComponent } from './LoginComponent';
import { ErrorComponent } from './ErrorComponent';

export default class App extends React.Component<{}, { gotCards: boolean, game: Game, isWrongLogin: boolean, isLoggedIn: boolean, lastTimeStamp: number}> {

    constructor(props) {
        super(props);

        this.state = {
            gotCards: false,
            game: null,
            isWrongLogin: false,
            isLoggedIn: false,
            lastTimeStamp: 0
        }
    }
   
    render() {
        return (
            <div>
                <div className={"align-left"}><ErrorComponent gotCards={this.state.gotCards} isLoggedIn={this.state.isLoggedIn}/></div>
                <div className={"align-right"}><LoginComponent startID={this.startRequest}/></div>
                <div className={"align-center"}><Table gotCards={this.state.gotCards} game={this.state.game} onSetGame={this.doHtttpReq}/></div>
            </div>
        );
    }

    startRequest = (target: string): Promise<void> => {
        return this.startRequestImpl(target);
    }

    async startRequestImpl(target: string){
        
        await StartPostRequest(parseInt(target));
        this.keepAlive(parseInt(target));
    }

    async keepAlive(id: number){
        const res = await StatusPostRequest(id, this.state.lastTimeStamp);

        if (res != null) {

            let gotCardsState = true;

            if (res.player.hand === null || res.player.hand.length === 0)
                gotCardsState = false;

            this.setState({ game: res, gotCards: gotCardsState, isWrongLogin: false, isLoggedIn: true });
            this.setState({lastTimeStamp: Date.now()});

            await this.keepAlive(id);

        } else {
            this.setState({ gotCards: false, isWrongLogin: true, isLoggedIn: true });
        }
    }

    doHtttpReq = (target: string): Promise<void> => {
        return this.setStateFromRequest(target);
    }

    async setStateFromRequest(target: string) {
        /*console.log("targetURL: " + target);
        const res = await Request(target);
        if (res != null) {
            this.setState({ game: res, gotCards: true, isWrongLogin: false });
        } else {
            this.setState({ gotCards: false, isWrongLogin: true });
        }*/
    }
}