package hu.ulti.server;

import java.util.ArrayList;
import java.util.List;

import hu.ulti.server.model.Player;

public class Helper {
	
	public static List<Player> getEmptyPlayers(int players) {

		if (players > 4 || players < 3)
			players = 3;

		List<Player> playersList = new ArrayList<Player>();

		for (int i = 0; i < players; i++) {
			playersList.add(i, new Player());
		}

		return playersList;
	}
	
	public static List<Player> setPlayers(List<Player> players){
		List<Player> result = new ArrayList<Player>();
		
		for (Player player : players) {
			if (player.getPlayerId() != -1)
				result.add(player);
		}
		
		return result;
	}

}
