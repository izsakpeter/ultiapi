import { ids } from "webpack";

export class RequestModel{
    public dest: string;

    public id: number;

    //order
    public colorOrder: boolean;

   
}

export function getRequestJson(model: RequestModel): any {

    switch (model.dest) {
        case "order":
            return {
                id: model.id,
                colorOrder: model.colorOrder
            };
    
        default:
            break;
    }
}