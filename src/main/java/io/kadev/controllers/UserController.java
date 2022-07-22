package io.kadev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kadev.models.User;
import io.kadev.models.UserForm;
import io.kadev.services.UserService;

@RestController
@RequestMapping("/configuration")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/password")
	public ResponseEntity<String> changePassword(@RequestParam String username,@RequestParam String newPassword) {
		return ResponseEntity.ok().body(userService.changePassword(username, newPassword));
	}
	
	@PostMapping("/admin/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody UserForm userUpdate) {
		return ResponseEntity.ok().body(userService.updateUser(userUpdate));
	}
	
	@PostMapping("/admin/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok().body(userService.addUser(user));
	}
	
	@GetMapping("/admin/getUsers")
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok().body(userService.getAllUsers());
	}
	
	@GetMapping("/admin/getUser")
	public ResponseEntity<User> getUser(@RequestParam Long id){
		return ResponseEntity.ok().body(userService.getUserById(id));
	}
	
	
	
}
