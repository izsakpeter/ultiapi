package hu.ulti.server.service;

import hu.ulti.server.request.LoginRequest;
import hu.ulti.server.response.LoginResponse;

public interface LoginService {
	
	LoginResponse login(LoginRequest request);

}
