package com.gb.service;

import java.util.List;
import com.gb.repository.*;
import com.gb.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReelServiceImplementation implements ReelService {

	@Autowired
	private ReelRepository reelRepo;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Reel createReel(Reel reel, User user) {
		Reel createReel = new Reel();
		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setVideo(reel.getVideo());
		
		return reelRepo.save(createReel);
	}

	@Override
	public List<Reel> findAllReel() {
		return reelRepo.findAll();
	}

	@Override
	public List<Reel> findUsersReel(int userId) throws Exception {
		userService.findUserById(userId);
		return reelRepo.findByUserId(userId);
	}

}
