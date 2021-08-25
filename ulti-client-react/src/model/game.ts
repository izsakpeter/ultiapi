import { Player } from "./player";
import { Strike } from "./strike";


export class Game {

    public startingValue: number;
	public player: Player;
	public previousCall: Array<number>;
	public isRoundStarted: boolean = false;
	public playReadyToStart: boolean = false;
	public lastCallerId: number;
	public activePlayer: number;
	public round: Strike;
	public lastStrikeId: number = 0;
	public lastStrike: Strike;




}