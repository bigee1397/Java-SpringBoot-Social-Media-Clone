package com.gb.service;

import java.util.List;

import com.gb.entities.Story;
import com.gb.entities.User;

public interface StoryService {

	public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
