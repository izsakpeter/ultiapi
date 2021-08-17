import { Card } from "./card";
import { Strike } from "./strike";


export class Player {

    public id: number;
	public isColorOrder: boolean;
	public hand: Array<Card>;
	public isCallOk: boolean;
	public strikes: Array<Strike>;
	public isColorForced: boolean = false;
}