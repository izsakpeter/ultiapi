package hu.ulti.service;

import org.springframework.http.ResponseEntity;

import hu.ulti.LoginResponse;
import hu.ulti.model.request.LoginRequest;

public interface LoginService {

	ResponseEntity<LoginResponse> login(LoginRequest request);

}
