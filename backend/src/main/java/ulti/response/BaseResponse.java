package ulti.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {

	private boolean isSuccess;

	public BaseResponse(boolean isSuccess) {
		super();
		this.isSuccess = isSuccess;
	}

	@JsonProperty("isSuccess")
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
