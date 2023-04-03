package hu.ulti.server.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

	private boolean isSuccess;
	private String errorMsg;
	private int permission;
	private int playerId;

	public LoginResponse(boolean isSuccess, String errorMsg, int permission, int playerId) {
		this.isSuccess = isSuccess;
		this.errorMsg = errorMsg;
		this.permission = permission;
		this.playerId = playerId;
	}

	@JsonProperty(value = "isSuccess")
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

}
