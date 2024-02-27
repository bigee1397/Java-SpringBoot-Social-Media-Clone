package com.gb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gb.entities.Post;
import com.gb.entities.Reel;
import com.gb.entities.User;
import com.gb.repository.PostRepository;
import com.gb.response.ApiResponse;
import com.gb.service.PostService;
import com.gb.service.ReelService;
import com.gb.service.UserService;

@RestController
public class ReelController {
	
	@Autowired
	private ReelService reelService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/reel/create")
	public Reel createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String jwt) {
		
		User reqUser = userService.findUserByJwt(jwt);
		Reel createdReel = reelService.createReel(reel, reqUser);

		return createdReel;
	}
	
	@GetMapping("/api/reels")
	public List<Reel> findAllReel() {
		List<Reel> reelList = reelService.findAllReel();
		return reelList;
	}
	
	@PostMapping("/api/reel/user/{userId}")
	public List<Reel> findUserReel(@PathVariable int userId) throws Exception {
		List<Reel> reels = reelService.findUsersReel(userId);
//		Reel createdReel = reelService.createReel(reel, reqUser);

		return reels;
	}
	
}
