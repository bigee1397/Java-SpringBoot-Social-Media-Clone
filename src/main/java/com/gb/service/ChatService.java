package com.gb.service;

import com.gb.entities.*;
import java.util.*;

public interface ChatService {

	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(int chatId) throws Exception;
	
	public List<Chat> findUsersChat(int userId);
	
}
