package hu.ulti.server.service;

import org.springframework.stereotype.Service;

import hu.ulti.server.request.LoginRequest;
import hu.ulti.server.response.LoginResponse;

@Service
public class LoginServiceImp implements LoginService {

	@Override
	public LoginResponse login(LoginRequest request) {

		if (request.getUsername().equals("IP") && request.getPassword().equals("8")) {
			return new LoginResponse(true, "", 2, 1);
		} else if (request.getUsername().equals("NN") && request.getPassword().equals("6")) {
			return new LoginResponse(true, "", 1, 2);
		} else if (request.getUsername().equals("Á") && request.getPassword().equals("4")) {
			return new LoginResponse(true, "", 1, 3);
		} else if (request.getUsername().equals("AB") && request.getPassword().equals("666")) {
			return new LoginResponse(true, "", 1, 4);
		} else {
			return new LoginResponse(false, "", 0, -1);
		}
	}

}
