package hu.ulti.server.modelOld;

public class Result {

	private String id;
	private int playerId;
	private int callId;
	private boolean isSuccess;
	private String comment = "";

	public Result(String id, int playerId, int callId, boolean isSuccess, String comment) {
		this.id = id;
		this.playerId = playerId;
		this.callId = callId;
		this.isSuccess = isSuccess;
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
