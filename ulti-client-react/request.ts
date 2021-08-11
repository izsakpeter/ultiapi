import axios from "axios";
import { Game } from "./model/game";

const baseUrl = 'http://localhost:8888';

export async function startRequest(id: string): Promise<Game> {
    const respone = await axios.get<Game>(baseUrl + "/start?id=" + id);
    return respone.data;
}
