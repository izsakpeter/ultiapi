package hu.ulti.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.ulti.LoginResponse;
import hu.ulti.model.request.LoginRequest;

@Service
public class LoginServiceImp implements LoginService {

	@Override
	public ResponseEntity<LoginResponse> login(LoginRequest request) {
		System.out.println("login " + request.getUsername());
		
		return new ResponseEntity<LoginResponse>(new LoginResponse(true, 8), HttpStatus.OK);
	}

}
