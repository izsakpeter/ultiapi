import { Card } from "./card";
import { Strike } from "./strike";


export class Player {

    public id!: number;
	public colorOrder!: boolean;
	public hand!: Array<Card>;
	public callOk!: boolean;
	public strikes!: Array<Strike>;
	public isColorForced: boolean = false;
	public bluff4020!: boolean;
	public said!: boolean;
	public playing!: boolean;
}