package ulti.service;

import org.springframework.http.ResponseEntity;

import ulti.model.request.UserRequest;
import ulti.response.BaseResponse;

public interface UserService {

	ResponseEntity<BaseResponse> addUser(UserRequest request);

	ResponseEntity<BaseResponse> editUser(UserRequest request);

	ResponseEntity<BaseResponse> deleteUser(UserRequest request);

}
