package ulti.response;

import java.util.List;

public class LobbyResponse {

	private boolean isSuccess;
	private List<String> loggedInUsers;

	public LobbyResponse(boolean isSuccess, List<String> loggedInUsers) {
		this.isSuccess = isSuccess;
		this.loggedInUsers = loggedInUsers;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public List<String> getLoggedInUsers() {
		return loggedInUsers;
	}

	public void setLoggedInUsers(List<String> loggedInUsers) {
		this.loggedInUsers = loggedInUsers;
	}

}
