import * as React from 'react';
import { Game } from '../model/game';
import { Table } from './TableComponent';
import { PostRequest, StatusPostRequest } from '../helper/request';
import { RequestModel } from '../model/requestModel';
import { WarningComponent } from './WarningComponent';
import { LoginComponent } from './LoginComponent';
import { useState } from 'react';

export default function App() {

    const [gotCards, setGotCards] = useState(false);
    const [game, setGame] = useState(new Game());
    const [isLoggedIn, setLoggedIn] = useState(false);
    const [lastTimeStamp, setLastTimeStamp] = useState(0);

    async function status(id: number) {
        const res = await StatusPostRequest(id, lastTimeStamp);

        if (res != null) {

            let gotCardsState = true;

            if (res.player.hand === null)
                gotCardsState = false;

            setGame(res);
            setGotCards(gotCardsState);
            setLoggedIn(true);
            setLastTimeStamp(Date.now());

        } else {
            setGotCards(false);
            setLoggedIn(true)
        }

        await status(id);
    }

    const postRequest = async (reqObj: RequestModel) => {
        await PostRequest(reqObj);

        if (reqObj.dest === "start")
            status(reqObj.id);
    }

    return (
        <div className="ulti-container">
            {!isLoggedIn && <div><LoginComponent postReq={postRequest} /></div>}
            <div><WarningComponent gotCards={gotCards} isLoggedIn={isLoggedIn} /></div>

            <div>
                <div><Table gotCards={gotCards} game={game} postReq={postRequest} /></div>
            </div>
        </div>
    );
}