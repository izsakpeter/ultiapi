import { Player } from "./player";
import { Strike } from "./strike";


export class Game {

    private startingValue: number;
	public player: Player;
	private previousCall: Array<number>;
	private isRoundStarted: boolean = false;
	private isPlayReadyToStart: boolean = false;
	private lastCallerId: number;
	private activePlayer: number;
	private round: Strike;
	private lastStrikeId: number = 0;
	private lastStrike: Strike;




}