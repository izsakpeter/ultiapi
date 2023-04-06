import { Hand } from "./hand";
import { Player } from "./player";

export class Game {
    public players!: Array<Player>;
    public isRoundStarted!: boolean;
    public player!: Player;
    public hands!: Array<Hand>;

}