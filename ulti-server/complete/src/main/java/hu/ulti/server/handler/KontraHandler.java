package hu.ulti.server.handler;

import java.util.List;

import hu.ulti.server.model.Call;
import hu.ulti.server.model.Game;
import hu.ulti.server.model.Say;

public class KontraHandler {
	
	public static List<Call> kontraHandler(Say say, Game game) {
		
		System.out.println(say.getPlayerId() + " iddiididididiidididididiidididi");
		
		return game.getPreviousCall();
	}

}
