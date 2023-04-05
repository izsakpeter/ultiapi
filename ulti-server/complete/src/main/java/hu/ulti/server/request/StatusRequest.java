package hu.ulti.server.request;

public class StatusRequest {

	private int playerId;
	private Long lastTimeStamp;

	public StatusRequest() {
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public Long getLastTimeStamp() {
		return lastTimeStamp;
	}

	public void setLastTimeStamp(Long lastTimeStamp) {
		this.lastTimeStamp = lastTimeStamp;
	}

}
