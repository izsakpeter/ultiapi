import { Card } from "./card";
import { Hand } from "./hand";
import { Player } from "./player";
import { Result } from "./result";
import { Say } from "./say";
import { Strike } from "./strike";
import { StrikeList } from "./strikeList";


export class Game {

    public startingValue: number;
	public player: Player;
	public roundStarted: boolean = false;
	public playReadyToStart: boolean = false;
	public lastCallerId: number;
	public activePlayer: number;
	public round: Strike;
	public previousCall: Array<number>;
	public call: Array<number>;
	public gameOver: boolean = false;
	public resultList: Array<Result>;
	public firstTurn: boolean;
	public says: Array<Say>;
	public hands: Array<Hand>;
	public strikeList: Array<StrikeList>;
	public talon: Array<Card>;

	public errorMessage: string ;
}