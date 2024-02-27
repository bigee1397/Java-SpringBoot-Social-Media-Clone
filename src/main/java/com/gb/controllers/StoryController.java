package com.gb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gb.entities.*;
import com.gb.service.StoryService;
import com.gb.service.UserService;

@RestController
public class StoryController {

	@Autowired
	private StoryService storyService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/story/create")
	public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
		User reqUser = userService.findUserByJwt(jwt);
		Story createdStory = storyService.createStory(story, reqUser);
		return createdStory;
	}
	
	@GetMapping("/api/story/user/{userId}")
	public List<Story> findUsersStory(@PathVariable int userId, @RequestHeader("Authorization") String jwt) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		List<Story> stories = storyService.findStoryByUserId(userId);
		return stories;
	}	
}
