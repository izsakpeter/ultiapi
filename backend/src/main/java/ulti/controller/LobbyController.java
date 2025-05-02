package ulti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ulti.response.BaseResponse;
import ulti.service.LobbyService;

@Controller
public class LobbyController {
	
	@Autowired
	private LobbyService lobbyService;
	
	@PostMapping("loggedinusers")
	ResponseEntity<List<String>> getLoggedInUsers(){
		return lobbyService.getLoggedInUsers();
	}
	
	@PostMapping("logout")
	ResponseEntity<BaseResponse> logout(@ RequestBody String name){
		return lobbyService.removeLoggedUser(name);
	}
}
