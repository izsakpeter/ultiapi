package ulti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ulti.entity.User;
import ulti.model.request.LoginRequest;
import ulti.repository.UserRepository;
import ulti.response.LoginResponse;

@Service
public class LoginServiceImp implements LoginService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<LoginResponse> login(LoginRequest request) {
		System.out.println("login " + request.getEmail() + ", " + request.getPassword());
		
		List<User> userList = userRepository.findAll();
		
		User loginUser = null;
		
		for (User user : userList) {
			
			System.out.println(user.toString());
			
			if (user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword()))
				loginUser = user;
		}
		
		if (loginUser == null)
			return new ResponseEntity<LoginResponse>(new LoginResponse(false), HttpStatus.OK);
		
		System.out.println("loginuser: " + loginUser.toString());
		
		
		return new ResponseEntity<LoginResponse>(new LoginResponse(true, loginUser.getId(), loginUser.getName()), HttpStatus.OK);
	}

}
