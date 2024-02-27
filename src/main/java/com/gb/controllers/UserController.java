package com.gb.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gb.entities.User;
import com.gb.repository.UserRepository;
import com.gb.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserRepository ur;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		List<User> users = ur.findAll();
		return users;	
	}

	@GetMapping("/api/user/{id}")
	public User getUserById(@PathVariable("id") Integer id) throws Exception {
		User user = userService.findUserById(id);
		return user;
	}
	
	
	@PutMapping("/api/updateuser")
	public User updateUser(@RequestBody User u, @RequestHeader("Authorization") String jwt) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser = userService.updateUser(u, reqUser.getId());
		
		return updatedUser;
	}
	
	@PutMapping("/api/users/follow/{u2}")
	public User followUserHandler(@PathVariable Integer u2, @RequestHeader("Authorization") String jwt) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId(), u2); 
		return user;
		
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query) {
		List<User> users = userService.searchUser(query);
		return users;
	}
	
//	@DeleteMapping("/deleteuser/{id}")
//	public String deleteUser(@PathVariable int id) throws Exception {
//		Optional<User> user = ur.findById(id);
//		if(user.isEmpty()) 
//			throw new Exception("User does not exist with the ID " + id);
//		ur.delete(user.get());
//		return "User deleted " + id;
//	}
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}
}
