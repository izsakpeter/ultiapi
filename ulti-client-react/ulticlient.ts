import { Game } from "./model/game";
import { startRequest } from "./request";

let message: string = "ulti kliens";
console.log(message);

startRequest("1").then(game => {
    console.log("game id: " + game.player.id);
})
.catch(error => {
    console.log("error: " + error);
});
