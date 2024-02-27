package com.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.gb.entities.*;
import com.gb.repository.PostRepository;
import com.gb.response.ApiResponse;
import com.gb.service.*;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment (@PathVariable("postId") Integer postId, @RequestBody Comment comment, @RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		
		Comment createdComment = commentService.createComment(comment, postId, user.getId());
		
		return createdComment;
	}
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment (@PathVariable("commentId") Integer commentId, @RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		
		Comment likedComment = commentService.likeComment(commentId, user.getId());
		
		return likedComment;
	}
}
