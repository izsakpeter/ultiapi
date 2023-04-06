import { Call } from "./call";

export class RequestModel {
    public dest?: string;

    public username?: string;
    public password?: string;

    public playerId?: number;

    //order
    public isColorOrder?: boolean;

    //startingvalue
    public value?: number;

    //call
    public call?: Array<Call>;
    public talonid?: Array<number>;
    public bluff4020?: boolean;

    //passz/join
    public isjoin?: boolean;

    //play
    public cardid?: number;

    //sayparti
    public have40?: boolean;
    public have120?: boolean;
    public have220?: boolean;
    public have320?: boolean;

    //saykontra
    public kontraId?: number;
    public kontraPassz?: boolean;
    public kontra40100?: boolean;
    public kontraUlti?: boolean;
    public kontraBetli?: boolean;
    public kontraDuri?: boolean;
    public kontraDuriSz?: boolean;
    public kontra20100?: boolean;
    public kontraBetliTer?: boolean;
    public kontraDuriTer?: boolean;
    public kontraDuriTerSz?: boolean;
}

export function getRequestJson(model: RequestModel): any {

    switch (model.dest) {
        case "login":
            return {
                username: model.username,
                password: model.password
            };
        case "start":
        case "newgame":
            return {
                id: model.playerId
            };
        case "order":
            return {
                playerId: model.playerId,
                isColorOrder: model.isColorOrder
            };
        case "startingvalue":
            return {
                id: model.playerId,
                value: model.value
            };
        case "call":
            return {
                id: model.playerId,
                call: model.call,
                talonid: model.talonid,
                bluff4020: model.bluff4020
            };
        case "join":
            return {
                id: model.playerId,
                isjoin: model.isjoin
            };
        case "play":
            return {
                id: model.playerId,
                cardid: model.cardid
            };

        case "sayparti":
            return {
                id: model.playerId,
                have40: model.have40,
                have120: model.have120,
                have220: model.have220,
                have320: model.have320
            };
        case "saykontra":
            return {
                id: model.playerId,
                kontraId: model.kontraId,
                kontraPassz: model.kontraPassz,
                kontra40100: model.kontra40100,
                kontraUlti: model.kontraUlti,
                kontraBetli: model.kontraBetli,
                kontraDuri: model.kontraDuri,
                kontraDuriSz: model.kontraDuriSz,
                kontra20100: model.kontra20100,
                kontraBetliTer: model.kontraBetliTer,
                kontraDuriTer: model.kontraDuriTer,
                kontraDuriTerSz: model.kontraDuriTerSz
            };
        default:
            break;
    }
}