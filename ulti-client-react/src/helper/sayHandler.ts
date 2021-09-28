import { SayMsg } from "../model/sayMsg";
import { getCallName } from "./callHandler";
import { Constants } from "./constants";

export function getSayFromMsgList(say: SayMsg): string {

    if (say.kontraId === -1 && say.callId === -1) {
        return (say.playerId + ": " + say.otherSay + "!");
    } else if (say.otherSay === "-1") {
        return (say.playerId + ": " + getKontraName(say.kontraId) + " " + getCallName(say.callId) + "!");
    } else {
        return (say.playerId + ": " + getKontraName(say.kontraId) + " " + getCallName(say.callId) + " " + say.otherSay + "!");
    }
}

function getKontraName(kontraId: number): string {

    if (kontraId == Constants.KONTRA_ID)
        return Constants.KONTRA;
    else if (kontraId == Constants.REKONTRA_ID)
        return Constants.REKONTRA;
    else if (kontraId == Constants.SZUPKONTRA_ID)
        return Constants.SZUPKONTRA;
    else if (kontraId == Constants.SZUPREKONTRA_ID)
        return Constants.SZUPREKONTRA;
    else if (kontraId == Constants.MAXKONTRA_ID)
        return Constants.MAXKONTRA;

    return "";
}