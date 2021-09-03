import * as React from 'react';
import { Game } from '../model/game';
import axios, { AxiosRequestConfig } from "axios";
import { Table } from './TableComponent';
import { PostRequest, StatusPostRequest } from '../helper/request';
import { LoginComponent } from './LoginComponent';
import { ErrorComponent } from './ErrorComponent';
import { RequestModel } from '../model/requestModel';

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
                <div className={"align-right"}><LoginComponent postReq={this.postRequest}/></div>
                <div><Table gotCards={this.state.gotCards} game={this.state.game} onSetGame={this.doHtttpReq} postReq={this.postRequest}/></div>
            </div>
        );
    }

    //ez kuka lesz
    doHtttpReq = (target: string): Promise<void> => {
        return null;
    }

    async keepAlive(id: number){
        const res = await StatusPostRequest(id, this.state.lastTimeStamp);

        if (res != null) {

            let gotCardsState = true;

            if (res.player.hand === null || res.player.hand.length === 0)
                gotCardsState = false;

            this.setState({ game: res, gotCards: gotCardsState, isWrongLogin: false, isLoggedIn: true });
            this.setState({lastTimeStamp: Date.now()});

        } else {
            this.setState({ gotCards: false, isWrongLogin: true, isLoggedIn: true });
        }

        await this.keepAlive(id);
    }

    postRequest = (reqObj: RequestModel): Promise<void> => {
        return this.postRequestImpl(reqObj);
    }

    async postRequestImpl(reqObj: RequestModel){
        await PostRequest(reqObj);

        if (reqObj.dest === "start")
            this.keepAlive(reqObj.id);
    }
}