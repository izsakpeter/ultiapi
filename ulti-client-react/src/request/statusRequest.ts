import axios from "axios";
import { Game } from "../model/game";
import { baseURL, configuration } from "./config";

export async function StatusPostRequest(
  id: number,
  lastTimeStamp: number
): Promise<Game> {
  const response = await axios.post<Game>(
    baseURL + "gamestatus",
    {
      playerId: id,
      lastTimeStamp: lastTimeStamp,
    },
    configuration
  );

  if (response.status !== 200) {
    console.log("ERROR: HTTP response code: " + response.status);
  }

  return response.data;
}
