package com.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gb.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
}
