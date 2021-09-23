import { KeyboardEventHandler } from "react";
import { Kontra } from "./kontra";

export class Call {
    public callId: number;
    public kontra: Kontra;

    public constructor(callId: number, kontra: Kontra) {
        this.callId = callId;
        this.kontra = kontra;
    }
}