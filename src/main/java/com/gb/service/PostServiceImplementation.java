package com.gb.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gb.entities.Post;
import com.gb.entities.User;
import com.gb.repository.PostRepository;
import com.gb.repository.UserRepository;


@Service
public class PostServiceImplementation implements PostService {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Post createNewPost(Post post, int userId) throws Exception {
		User user = userService.findUserById(userId);
		
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCreatedAt(LocalDate.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		return postRepository.save(newPost);
	}

	@Override
	public String deletePost(int postId, int userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(post.getUser().getId() != user.getId())
			throw new Exception("You cannot delete another user's post");
		
		postRepository.delete(post);
		
		return "Post Deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(int userId) {
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(int postId) throws Exception {
		Optional<Post> post = postRepository.findById(postId);
		if(post.isEmpty()) throw new Exception("Post does not exists with id " + postId);
		return post.get();
	}

	@Override
	public List<Post> findAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(int postId, int userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(user.getSavedPosts().contains(post)) 
			user.getSavedPosts().remove(post);
		else 
			user.getSavedPosts().add(post);
		
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(int postId, int userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		if(post.getLiked().contains(user)) 
			post.getLiked().remove(user);
		else 
			post.getLiked().add(user);
		return postRepository.save(post);
	}

	

}
