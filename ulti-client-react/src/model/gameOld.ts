import { Call } from "./call";
import { Card } from "./card";
import { Hand } from "./hand";
import { PlayerOld } from "./playerOld";
import { Result } from "./result";
import { Say } from "./say";
import { SayMsg } from "./sayMsg";
import { Score } from "./score";
import { Strike } from "./strike";
import { StrikeList } from "./strikeList";


export class GameOld {

    public startingValue!: number;
	public player!: PlayerOld;
	public roundStarted: boolean = false;
	public playReadyToStart: boolean = false;
	public lastCallerId!: number;
	public activePlayer!: number;
	public round!: Strike;
	public gameOver: boolean = false;
	public resultList!: Array<Result>;
	public firstTurn!: boolean;
	public says!: Array<Say>;
	public hands!: Array<Hand>;
	public strikeList!: Array<StrikeList>;
	public talon!: Array<Card>;
	public previousCall!: Array<Call>;
	public call!: Array<Call>;
	public kontraPartFinished!: boolean;
	public sayMsgList!: Array<SayMsg>;
	public scores!: Array<Score>;

	public errorMessage!: string ;
}