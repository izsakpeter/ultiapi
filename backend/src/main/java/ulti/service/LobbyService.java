package ulti.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ulti.response.BaseResponse;

public interface LobbyService {
	
	ResponseEntity<List<String>> getLoggedInUsers();
	
	void addLoggedUser(String name);
	
	ResponseEntity<BaseResponse> removeLoggedUser(String name);

}
