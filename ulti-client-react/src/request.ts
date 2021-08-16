import axios from "axios";
import { Game } from "./model/game";

const baseUrl = 'http://localhost:8888';

export class Requests {
    public static async startRequest(id: string): Promise<Game> {

        console.log(id + " dididdididiididididididiidididi")

        const respone = await axios.get<Game>("http://localhost:8888/start?id=1");
        console.log(respone.data);

        return respone.data;
    }
}


