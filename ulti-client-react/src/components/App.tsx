import * as React from 'react';
import { Game } from '../model/game';
import { Table } from './TableComponent';
import { PostRequest, StatusPostRequest } from '../helper/request';
import { RequestModel } from '../model/requestModel';
import { WarningComponent } from './WarningComponent';
import { LoginComponent } from './LoginComponent';

interface iState {
    gotCards: boolean,
    game: Game,
    isWrongLogin: boolean,
    isLoggedIn: boolean,
    lastTimeStamp: number
}

export default class App extends React.Component<{}, iState> {

    constructor(props: any) {
        super(props);

        this.state = {
            gotCards: false,
            game: new Game(),
            isWrongLogin: false,
            isLoggedIn: false,
            lastTimeStamp: 0
        }
    }

    render() {
        return (
            <div className="ulti-container">
                {!this.state.isLoggedIn && <div><LoginComponent postReq={this.postRequest} /></div>}
                <div><WarningComponent gotCards={this.state.gotCards} isLoggedIn={this.state.isLoggedIn} /></div>

                <div>
                    <div><Table gotCards={this.state.gotCards} game={this.state.game} postReq={this.postRequest} /></div>
                </div>
            </div>
        );
    }

    async status(id: number) {
        const res = await StatusPostRequest(id, this.state.lastTimeStamp);

        if (res != null) {

            let gotCardsState = true;

            if (res.player.hand === null)
                gotCardsState = false;

            this.setState({ game: res, gotCards: gotCardsState, isWrongLogin: false, isLoggedIn: true, lastTimeStamp: Date.now() });

        } else {
            this.setState({ gotCards: false, isWrongLogin: true, isLoggedIn: true });
        }

        await this.status(id);
    }

    postRequest = (reqObj: RequestModel): Promise<void> => {
        return this.postRequestImpl(reqObj);
    }

    async postRequestImpl(reqObj: RequestModel) {
        await PostRequest(reqObj);

        if (reqObj.dest === "start")
            this.status(reqObj.id);
    }
}