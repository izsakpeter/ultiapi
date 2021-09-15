import { ids } from "webpack";

export class Call {
    public id: number;
    public value: number;
    public color: number;

    public constructor(id: number, value: number, color:number){
        this.id = id;
        this.value = value;
        this.color = color;
    }
}