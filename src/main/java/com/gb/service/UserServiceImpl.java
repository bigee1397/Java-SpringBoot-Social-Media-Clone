package com.gb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.entities.User;
import com.gb.repository.UserRepository;
import com.gb.config.JwtProvider;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository ur;
	
	@Override
	public User registerUser(User user) {
		
		User newUser = new User();
		newUser.seteMail(user.geteMail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		
		User savedUser = ur.save(newUser);
		
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		
		Optional<User> user = ur.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new Exception("user not exist with userid " + userId);
	}

	@Override
	public User findUserByEmail(String eMail) {
		User user = ur.findByeMail(eMail);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws Exception {
		
		User reqUser = findUserById(reqUserId);
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());
		ur.save(reqUser);
		ur.save(user2);
		
		return reqUser;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		Optional<User> user1 = ur.findById(userId);
		if(user1.isEmpty()) 
			throw new Exception("User does not exist with the ID " + userId);
		
		User oldUser = user1.get();
		if(user.getFirstName() != null) 
			oldUser.setFirstName(user.getFirstName());
		if(user.getLastName() != null) 
			oldUser.setLastName(user.getLastName());
		if(user.geteMail() != null) 
			oldUser.seteMail(user.geteMail());
		if(user.getGender() != null) 
			oldUser.setGender(user.getGender());
		
		User updatedUser = ur.save(oldUser);
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		return ur.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		
		User user = ur.findByeMail(email);
		
		return user;
	}


}
