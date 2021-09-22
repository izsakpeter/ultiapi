import { ids } from "webpack";

export class RequestModel {
    public dest: string;
    public id: number;

    //order
    public colorOrder?: boolean;

    //startingvalue
    public value?: number;

    //call
    public call?: Array<number>;
    public talonid?: Array<number>;
    public bluff4020?: boolean;

    //passz/join
    public isjoin?: boolean;

    //play
    public cardid?: number;

    //say
    public have40?: boolean;
    public have120?: boolean;
    public have220?: boolean;
    public have320?: boolean;
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
    public ackKontra?: boolean;

    //rekontra
    public rekontraPassz?: boolean;
    public rekontra40100?: boolean;
    public rekontraUlti?: boolean;
    public rekontraBetli?: boolean;
    public rekontraDuri?: boolean;
    public rekontraDuriSz?: boolean;
    public rekontra20100?: boolean;
    public rekontraBetliTer?: boolean;
    public rekontraDuriTer?: boolean;
    public rekontraDuriTerSz?: boolean
}

export function getRequestJson(model: RequestModel): any {

    switch (model.dest) {
        case "start":
        case "newgame":
            return {
                id: model.id
            };
        case "order":
            return {
                id: model.id,
                colorOrder: model.colorOrder
            };
        case "startingvalue":
            return {
                id: model.id,
                value: model.value
            };
        case "call":
            return {
                id: model.id,
                call: model.call,
                talonid: model.talonid,
                bluff4020: model.bluff4020
            };
        case "join":
            return {
                id: model.id,
                isjoin: model.isjoin
            };
        case "play":
            return {
                id: model.id,
                cardid: model.cardid
            };

        case "say":
            return {
                id: model.id,
                have40: model.have40,
                have120: model.have120,
                have220: model.have220,
                have320: model.have320,
                kontraPassz: model.kontraPassz,
                kontra40100: model.kontra40100,
                kontraUlti: model.kontraUlti,
                kontraBetli: model.kontraBetli,
                kontraDuri: model.kontraDuri,
                kontraDuriSz: model.kontraDuriSz,
                kontra20100: model.kontra20100,
                kontraBetliTer: model.kontraBetliTer,
                kontraDuriTer: model.kontraDuriTer,
                kontraDuriTerSz: model.kontraDuriTerSz,
                ackKontra: model.ackKontra
            };
        case "rekontra":
            return {
                id: model.id,
                rekontraPassz: model.rekontraPassz,
                rekontra40100: model.rekontra40100,
                rekontraUlti: model.rekontraUlti,
                rekontraBetli: model.rekontraBetli,
                rekontraDuri: model.rekontraDuri,
                rekontraDuriSz: model.rekontraDuriSz,
                rekontra20100: model.rekontra20100,
                rekontraBetliTer: model.rekontraBetliTer,
                rekontraDuriTer: model.rekontraDuriTer,
                rekontraDuriTerSz: model.rekontraDuriTerSz
            };
        default:
            break;
    }
}