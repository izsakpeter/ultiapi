package hu.ulti;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
	private boolean isSuccess;
	private int playerId = -1;

	public LoginResponse(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public LoginResponse(boolean isSuccess, int playerId) {
		this.isSuccess = isSuccess;
		this.playerId = playerId;
	}

	@JsonProperty("isSuccess")
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
