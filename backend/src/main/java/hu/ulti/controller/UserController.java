package hu.ulti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hu.ulti.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	public void addUser() {
		userService.addUser();
	}

	public void editUser() {
		userService.editUser();
	}

	public void deleteUser() {
		userService.deleteUser();
	}

}
