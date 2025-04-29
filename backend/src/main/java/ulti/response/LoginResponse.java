package ulti.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
	private boolean isSuccess;
	private int playerId = -1;
	private String username;

	public LoginResponse(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public LoginResponse(boolean isSuccess, int playerId, String username) {
		this.isSuccess = isSuccess;
		this.playerId = playerId;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
