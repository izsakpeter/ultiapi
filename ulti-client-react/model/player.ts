import { Card } from "./card";
import { Strike } from "./strike";


export class Player {

    public id: number;
	private isColorOrder: boolean;
	private hand: Array<Card>;
	private isCallOk: boolean;
	private strikes: Array<Strike>;
	private isColorForced: boolean = false;



}