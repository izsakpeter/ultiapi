package ulti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ulti.entity.User;
import ulti.model.request.UserRequest;
import ulti.repository.UserRepository;
import ulti.response.BaseResponse;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<BaseResponse> addUser(UserRequest request) {

		User user = userRepository.save(new User(request.getUsername(), request.getPassword(), request.getEmail()));

		return new ResponseEntity<BaseResponse>(new BaseResponse(user != null), HttpStatus.OK);
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
