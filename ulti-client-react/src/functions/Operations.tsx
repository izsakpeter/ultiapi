import { Button } from "@blueprintjs/core";
import { RequestModel } from "../model/requestModel";
import { Game } from "../model/game";

interface IOperations {
    game: Game,
    postReq: (reqObj: RequestModel) => void
}

export default function Operations({ game, postReq }: IOperations) {

    async function changeOrderHandler() {

        let reqObj: RequestModel = {
            dest: "order",
            playerId: game.player.playerId,
            isColorOrder: !game.player.isColorOrder
        }

        postReq(reqObj);
    }

    if (game.player.isPlaying) {
        return (
            <div>
                <div><Button onClick={changeOrderHandler} text="rendez" /></div>
            </div>
        )
    } else {
        return <></>;
    }
}