package ulti.service;

import org.springframework.http.ResponseEntity;

import ulti.model.request.LoginRequest;
import ulti.response.LoginResponse;

public interface LoginService {

	ResponseEntity<LoginResponse> login(LoginRequest request);

}
