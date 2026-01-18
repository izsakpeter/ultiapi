package ulti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ulti.response.BaseResponse;
import ulti.response.LobbyResponse;

@Service
public class LobbyServiceImp implements LobbyService {

	private List<String> loggedUsers = new ArrayList<String>();

	@Override
	public ResponseEntity<LobbyResponse> getLoggedInUsers() {
		return new ResponseEntity<LobbyResponse>(new LobbyResponse(true, loggedUsers), HttpStatus.OK);
	}

	@Override
	public void addLoggedUser(String name) {
		if (!loggedUsers.contains(name))
			loggedUsers.add(name);
	}

	@Override
	public ResponseEntity<BaseResponse> removeLoggedUser(String name) {
		loggedUsers.remove(name);
		return new ResponseEntity<BaseResponse>(new BaseResponse(true), HttpStatus.OK);
	}
}
