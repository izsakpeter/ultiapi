import { KeyboardEventHandler } from "react";
import { Kontra } from "./kontra";

export class Call {
    public callId: number;
    public kontra: Array<Kontra>;

    public constructor(callId: number, kontra: Array<Kontra>) {
        this.callId = callId;
        this.kontra = kontra;
    }
}