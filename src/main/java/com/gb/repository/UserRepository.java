package com.gb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gb.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByeMail(String eMail);
	
	@Query("Select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.eMail LIKE %:query%")
	public List<User> searchUser(@Param("query") String query);
	
}
