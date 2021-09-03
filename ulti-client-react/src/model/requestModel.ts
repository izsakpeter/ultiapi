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

    //passz/join
    public isjoin?: boolean;
}

export function getRequestJson(model: RequestModel): any {

    switch (model.dest) {
        case "start":
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
                talonid: model.talonid
            };
        case "join":
            return {
                id: model.id,
                isjoin: model.isjoin
            };

        default:
            break;
    }
}