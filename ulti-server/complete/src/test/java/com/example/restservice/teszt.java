package com.example.restservice;

import java.util.List;

import hu.ulti.server.Helper;
import hu.ulti.server.model.Player;

public class teszt {

	private static int playersNumber = 4;
	private static List<Player> players = Helper.getPlayerList(playersNumber);

	public static void main(String[] args) {
		players.get(0).setId(1);
		players.get(0).setPlaying(false);
		players.get(1).setId(8);
		players.get(1).setPlaying(true);
		players.get(2).setId(18);
		players.get(2).setPlaying(true);
		players.get(3).setId(88);
		players.get(3).setPlaying(true);

		System.out.println(getIncreasedPlayerId(3) + " fgsdfgsdfgfdg");

	}

	private static int getIncreasedPlayerId(int activePlayerIndex) {

		int indexPl = activePlayerIndex + 1 >= players.size() ? 0 : activePlayerIndex + 1;

		if (!players.get(indexPl).isPlaying())
			return getIncreasedPlayerId(indexPl);

		return players.get(indexPl).getId();
	}

}
