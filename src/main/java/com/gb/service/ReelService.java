package com.gb.service;

import com.gb.entities.*;
import java.util.*;

public interface ReelService {

	public Reel createReel(Reel reel, User user);
	public List<Reel> findAllReel();
	public List<Reel> findUsersReel(int userId) throws Exception;
}
