package com.backenddemo.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backenddemo.springboot.exception.UserNotFoundException;
import com.backenddemo.springboot.model.User;
import com.backenddemo.springboot.repository.UserRepository;
//crossOrigin specifies where a request is coming from

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// e.g. http://localhost:8080/api/v1/users
	@GetMapping("users")
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	// get users by id
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found for this id:" + userId));
		return ResponseEntity.ok().body(user);
	}

	// save/create a user
	@PostMapping("users")
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);

	}

	// update
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found for this id:" + userId));

		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setDob(userDetails.getDob());
		user.setHobby(userDetails.getHobby());
		user.setLocation(userDetails.getLocation());
		user.setOccupation(user.getOccupation());

		return ResponseEntity.ok(this.userRepository.save(user));
	}

	// delete
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found for this id:" + userId));
		this.userRepository.delete(user);
		Map<String, Boolean> responseMap = new HashMap<>();
		responseMap.put("deleted", Boolean.TRUE);

		return responseMap;

	}

}
