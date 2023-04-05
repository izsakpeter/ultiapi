import { Game } from "../model/game";

interface IGame {
    game: Game;
}

export default function Table({ game }: IGame) {

    if (game.players) {
        return (<div>
            <div>Várakozó játékosok:</div>
            <div>
                {
                    game.players.map(player =>
                        <div>{player.playerName}</div>)
                }
            </div>
        </div>
        )
    }

    return (<>asztal</>)
}