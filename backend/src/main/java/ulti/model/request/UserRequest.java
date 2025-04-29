package ulti.model.request;

public class UserRequest {

	private String email;
	private String username;
	private String password;

	public UserRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRequest [email=" + email + ", username=" + username + ", password=" + password + "]";
	}

}
