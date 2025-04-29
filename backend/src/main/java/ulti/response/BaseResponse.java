package ulti.response;

public class BaseResponse {

	private boolean isSuccess;

	public BaseResponse(boolean isSuccess) {
		super();
		this.isSuccess = isSuccess;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
