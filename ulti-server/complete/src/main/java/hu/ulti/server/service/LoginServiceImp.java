package hu.ulti.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.ulti.server.model.Player;
import hu.ulti.server.request.LoginRequest;
import hu.ulti.server.response.LoginResponse;

@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	GameService gameService;

	@Override
	public LoginResponse login(LoginRequest request) {

		if (request.getUsername().equals("IP") && request.getPassword().equals("8")) {
			Player player = new Player(1, "IP");
			gameService.setPlayerReady(player);

			return new LoginResponse(true, "", 2, player.getPlayerId());
		} else if (request.getUsername().equals("NN") && request.getPassword().equals("6")) {
			Player player = new Player(2, "NN");
			gameService.setPlayerReady(player);

			return new LoginResponse(true, "", 1, player.getPlayerId());
		} else if (request.getUsername().equals("Á") && request.getPassword().equals("4")) {
			Player player = new Player(3, "Á");
			gameService.setPlayerReady(player);

			return new LoginResponse(true, "", 1, player.getPlayerId());
		} else if (request.getUsername().equals("AB") && request.getPassword().equals("666")) {
			Player player = new Player(4, "AB");
			gameService.setPlayerReady(player);

			return new LoginResponse(true, "", 1, player.getPlayerId());
		} else {
			return new LoginResponse(false, "", 0, -1);
		}
	}

}
