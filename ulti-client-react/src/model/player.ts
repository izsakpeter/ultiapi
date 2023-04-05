import { Card } from "./card";

export class Player {

    public playerId!: number;
    public playerName!: string;

	public isColorOrder!: boolean;
	public hand!: Array<Card>;
	public isColorForced!: boolean;
	public isPlaying!: boolean;
}