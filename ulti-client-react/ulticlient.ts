import { ApiController } from "./apiController";
import { Game } from "./model/game";

let message: string = "ulti kliens";
console.log(message);

//game: Game;
const apiController: ApiController = new ApiController();

apiController.startRequest("1").then(game => {
    console.log("game id: " + game.player.id);
})
.catch(error => {
    console.log("error: " + error);
});
