package com.gb.service;

import java.time.*;
import java.util.Optional;

import com.gb.entities.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gb.repository.*;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplementation implements CommentService{

	@Autowired
	PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	
	@Override
	public Comment createComment(Comment comment, int postId, int userId) throws Exception {
		User user = userService.findUserById(userId);
		
		Post post = postService.findPostById(postId);

		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		
		Comment savedComment = commentRepo.save(comment);
		
		post.getComments().add(savedComment);
		postRepo.save(post);
		return savedComment;
	}

	@Override
	public Comment findCommentById(int commentId) throws Exception {
		Optional<Comment> opt = commentRepo.findById(commentId);
		
		if(opt.isEmpty()) {
			throw new Exception("Comment doesn't exist");
		}
		
		return opt.get();
	}

	@Override
	public Comment likeComment(int commentId, int userId) throws Exception {
		Comment comment = findCommentById(commentId);
		User user = userService.findUserById(userId);
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		} else {
			comment.getLiked().remove(user);
		}
		return commentRepo.save(comment);
	}

	
	
}
