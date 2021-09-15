import { Hand } from "./hand";
import { Player } from "./player";
import { Result } from "./result";
import { Say } from "./say";
import { Strike } from "./strike";


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
	public player1Hand: Hand;
	public player2Hand: Hand;
	public player3Hand: Hand;
	public resultList: Array<Result>;
	public firstTurn: boolean;
	public says: Array<Say>;
}