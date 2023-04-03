package hu.ulti.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.ulti.server.request.LoginRequest;
import hu.ulti.server.response.LoginResponse;
import hu.ulti.server.service.LoginService;

@CrossOrigin
@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@PostMapping("login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		return loginService.login(request);
	}

}
