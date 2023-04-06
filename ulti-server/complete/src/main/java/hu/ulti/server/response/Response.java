package hu.ulti.server.response;

public class Response {

	private boolean isSuccess;
	private String message;
	
	public Response() {
	}

	public Response(boolean isSuccess, String message) {
		this.isSuccess = isSuccess;
		this.message = message;
	}
	
	public Response(boolean isSuccess) {
		this.isSuccess = isSuccess;
		this.message = "";
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
