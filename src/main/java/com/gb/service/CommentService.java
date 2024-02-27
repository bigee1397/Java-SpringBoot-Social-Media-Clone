package com.gb.service;

import com.gb.entities.Comment;

public interface CommentService {
	
	public Comment createComment(Comment comment, int postId, int userId) throws Exception;
	
	public Comment findCommentById(int commentId) throws Exception;
	
	public Comment likeComment(int commentId, int userId) throws Exception;
}
