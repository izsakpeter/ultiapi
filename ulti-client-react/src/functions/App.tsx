import { RequestModel } from '../model/requestModel';
import { useState } from 'react';
import { loginRequest } from '../request/loginRequest';
import Login from './Login';
import { StatusPostRequest } from '../request/statusRequest';
import Table from './Table';
import { Game } from '../model/game';

let lastTimeStamp: number = 0;

export default function App() {

    const [isLoggedIn, setLoggedIn] = useState<boolean>(false);
    const [playerId, setPlayerId] = useState<number>(-1);
    const [game, setGame] = useState<Game>(new Game());
    
    async function status(id: number) {

        const result = await StatusPostRequest(id, lastTimeStamp);
        setGame(result);

        lastTimeStamp = Date.now();

        await status(playerId);
    }

    const login = async (reqObj: RequestModel) => {
        const result = await loginRequest(reqObj);

        if (result.isSuccess){
            setLoggedIn(true);
            setPlayerId(result.playerId);
            status(result.playerId);
        }  
    }

    return (
        <div className="ulti-container">
            {!isLoggedIn && <div><Login loginRequest={login} /></div>}

            {isLoggedIn && <div><Table game={game} /></div>}
          
        </div>
    );
}