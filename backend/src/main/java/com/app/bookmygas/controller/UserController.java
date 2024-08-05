package com.app.bookmygas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.app.bookmygas.dto.SigninRequest;
import com.app.bookmygas.dto.SigninResponse;
import com.app.bookmygas.entity.User;
import com.app.bookmygas.repository.UserRepository;
import com.app.bookmygas.security.CustomUserDetails;
import com.app.bookmygas.security.JwtUtils;
import com.app.bookmygas.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(allowedHeaders = "*", originPatterns = {"http://localhost:3000"})
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authMgr;

	@Autowired
	private JwtUtils jwtUtils;

	/*
	 * URL - http://host:port/users/signin Method - POST request payload : Auth req
	 * DTO : email n password resp payload : In case of success : Auth Resp DTO :
	 * mesg + JWT token + SC 201 In case of failure : SC 401
	 * 
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid SigninRequest request) {
		System.out.println("in sign in" + request);
		// create a token to store un verified user email n pwd
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		// invoke auth mgr's authenticate method;
		Authentication verifiedToken = authMgr.authenticate(token);
		// => auth successful !
		System.out.println(verifiedToken.getPrincipal().getClass());// custom user details object
		// create JWT n send it to the clnt in response
		SigninResponse resp = new SigninResponse(jwtUtils.generateJwtToken(verifiedToken), "Authenticated succesfully!!!!");
		User user = userService.getUserByEmail(request.getEmail());
		user.setPassword(null);
		resp.setUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

	@PostMapping("/signup")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		User user = userService.getUserById(id);
		return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
		User updatedUser = userService.updateUser(id, user);
		return updatedUser != null ? new ResponseEntity<>(updatedUser, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		boolean isDeleted = userService.deleteUser(id);
		return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}