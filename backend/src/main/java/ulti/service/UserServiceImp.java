package ulti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ulti.entity.User;
import ulti.helper.MD5;
import ulti.model.request.UserRequest;
import ulti.repository.UserRepository;
import ulti.response.BaseResponse;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LobbyService lobbyService;

	@Override
	public ResponseEntity<BaseResponse> addUser(UserRequest request) {

		String password = MD5.getMD5(request.getPassword());
		User user = userRepository.save(new User(request.getUsername(), password, request.getEmail()));

		if (user != null) {
			lobbyService.addLoggedUser(user.getName());
			return new ResponseEntity<BaseResponse>(new BaseResponse(false), HttpStatus.OK);
		}

		return new ResponseEntity<BaseResponse>(new BaseResponse(false), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BaseResponse> editUser(UserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<BaseResponse> deleteUser(UserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
