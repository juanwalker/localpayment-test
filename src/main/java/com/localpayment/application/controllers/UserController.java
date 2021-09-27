package com.localpayment.application.controllers;

import com.localpayment.application.actionsResponses.LoginResponse;
import com.localpayment.application.actionsResponses.PersonResponse;
import com.localpayment.application.actionsResponses.ResponseStatus;
import com.localpayment.application.actionsResponses.UserResponse;
import com.localpayment.application.postActions.AddUser;
import com.localpayment.application.services.UserService;
import com.localpayment.domain.entities.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

		@GetMapping("/{id}")
		public UserResponse get(@PathVariable(name = "id") String  id) {
			User user =  this.userService.findById(id);
			if (user != null) {
				return new UserResponse(user,"User found", ResponseStatus.OK.getMessage());
			} else {
				return new UserResponse(null,"User not found", ResponseStatus.KO.getMessage());

			}


		}
	
		@PostMapping("/")
		public UserResponse add(@RequestBody AddUser addUser) {
		User user = this.userService.add(addUser);
		if (user != null) {
			return new UserResponse(user,"User created", ResponseStatus.OK.getMessage());
		} else {
			return new UserResponse(user,"User not created", ResponseStatus.KO.getMessage());
		}
	}

	@GetMapping("/test")
	public LoginResponse test(){
		String token = userService.getJWTToken("test");
		return new LoginResponse("test",token);
	}

	@GetMapping("/login")
	public LoginResponse login(@RequestParam("username") String username, @RequestParam("password") String password) {

		if (userService.checkCredentials(username,password)) {
			String token = userService.getJWTToken(username);
			return new LoginResponse(username,token);
		} else {
			return new LoginResponse(username,"Not a valid user and/or password!");
		}


	}

}
