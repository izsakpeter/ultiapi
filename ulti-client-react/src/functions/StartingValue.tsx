import { Button, Radio } from "@blueprintjs/core";
import { getCardSource } from "../helper/cardHandler";
import { Constants } from "../helper/constants";
import { RequestModel } from "../model/requestModel";
import { Game } from "../model/game";

interface IStartingValue {
    game: Game,
    postReq: (reqObj: RequestModel) => void
}

export default function StartingValue({ game, postReq }: IStartingValue) {

    if (game.activePlayer !== game.player.playerId || game.startingValue !== 0 || game.gameOver)
        return <></>;

    let talon = <div><img alt="card" src={getCardSource(game.talon[0].id)} className="talon-card" /><img alt="card" src={getCardSource(game.talon[1].id)} className="talon-card" /></div>

    return (
        <div>
            <div className={"sv-border"}>
                <div>
                    <div> Válassz kezdő szint!</div>
                    <div>
                        <Radio name="sv" label={Constants.MAKK} value={Constants.MAKK_ID} onClick={this.onChangeValue} defaultChecked={true} />
                        <Radio name="sv" label={Constants.ZOLD} value={Constants.ZOLD_ID} onClick={this.onChangeValue} />
                        <Radio name="sv" label={Constants.TOK} value={Constants.TOK_ID} onClick={this.onChangeValue} />
                        <Radio name="sv" label={Constants.PIROS} value={Constants.PIROS_ID} onClick={this.onChangeValue} />
                    </div>
                </div>
                <div><Button className={"button-ok"} onClick={this.setStartingValue}>ok</Button></div>
            </div>
            <div className={"talon-pos"}>{talon}</div>
        </div>
    )


}