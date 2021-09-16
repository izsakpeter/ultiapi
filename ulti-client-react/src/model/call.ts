import { ids } from "webpack";

export class Call {
    public id: number;
    public value: number;
    public color: number;
    public name: string;

    public constructor(id: number, value: number, color: number, name: string) {
        this.id = id;
        this.value = value;
        this.color = color;
        this.name = name;
    }
}