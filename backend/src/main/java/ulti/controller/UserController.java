package ulti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ulti.model.request.UserRequest;
import ulti.response.BaseResponse;
import ulti.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("registration")
	public ResponseEntity<BaseResponse> addUser(@RequestBody UserRequest request) {
		return userService.addUser(request);
	}

	public ResponseEntity<BaseResponse> editUser(@RequestBody UserRequest request) {
		return userService.editUser(request);
	}

	public ResponseEntity<BaseResponse> deleteUser(@RequestBody UserRequest request) {
		return userService.deleteUser(request);
	}

}
