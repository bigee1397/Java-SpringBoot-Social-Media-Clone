package com.gb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.gb.entities.Post;
import com.gb.entities.User;
import com.gb.repository.PostRepository;
import com.gb.response.ApiResponse;
import com.gb.service.PostService;
import com.gb.service.UserService;

@RestController
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		Post createdPost = postService.createNewPost(post, reqUser.getId());
		return  new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/posts/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId, @RequestHeader("Authorization") String jwt) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		String message = postService.deletePost(postId, reqUser.getId());
		ApiResponse res = new ApiResponse(message, true);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable int postId) throws Exception {
		Post post = postService.findPostById(postId);
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}

	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPosts(@PathVariable int userId) {
		List<Post> posts = postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPosts() {
		List<Post> posts = postService.findAllPosts();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@PutMapping("/posts/saved/{postId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable int postId, @RequestHeader("Authorization") String jwt) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		Post post = postService.savedPost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@PutMapping("/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable int postId, @RequestHeader("Authorization") String jwt) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		Post post = postService.likePost(postId, reqUser.getId());		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
}
