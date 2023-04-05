package hu.ulti.server.modelOld;

import java.util.ArrayList;
import java.util.List;

public class StrikeList {

	private int playerId;
	private List<Strike> strikeList = new ArrayList<Strike>();
	
	public StrikeList() {
	}

	public StrikeList(int playerId, List<Strike> strikeList) {
		this.playerId = playerId;
		this.strikeList = strikeList;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public List<Strike> getStrikeList() {
		return strikeList;
	}

	public void setStrikeList(List<Strike> strikeList) {
		this.strikeList = strikeList;
	}
}
