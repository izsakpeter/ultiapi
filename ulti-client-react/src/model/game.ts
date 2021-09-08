import { Player } from "./player";
import { Strike } from "./strike";


export class Game {

    public startingValue: number;
	public player: Player;
	public isRoundStarted: boolean = false;
	public playReadyToStart: boolean = false;
	public lastCallerId: number;
	public activePlayer: number;
	public round: Strike;
	public previousCall: Array<number>;
	public call: Array<number>;
	public gameOver: boolean = false;
}