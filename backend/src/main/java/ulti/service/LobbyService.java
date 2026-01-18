package ulti.service;

import org.springframework.http.ResponseEntity;

import ulti.response.BaseResponse;
import ulti.response.LobbyResponse;

public interface LobbyService {
	
	ResponseEntity<LobbyResponse> getLoggedInUsers();
	
	void addLoggedUser(String name);
	
	ResponseEntity<BaseResponse> removeLoggedUser(String name);

}
