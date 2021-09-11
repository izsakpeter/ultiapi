package hu.ulti.server.model;

public class Result {

	private int playerId;
	private int callId;
	private boolean isSucces;

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}

	public boolean isSucces() {
		return isSucces;
	}

	public void setSucces(boolean isSucces) {
		this.isSucces = isSucces;
	}
}
