package com.gb.service;

import org.springframework.stereotype.Service;
import java.time.*;
import java.util.*;
import com.gb.repository.*;
import com.gb.entities.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ChatServiceImplementation implements ChatService {

	@Autowired
	ChatRepository chatRepo;
	
	@Override
	public Chat createChat(User reqUser, User user2) {
		Chat isExist = chatRepo.findChatByUsersId(user2, reqUser);
		if(isExist != null)	return isExist;
		
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimeStamp(LocalDateTime.now());
		
		return chatRepo.save(chat);
	}

	@Override
	public Chat findChatById(int chatId) throws Exception {
		Optional<Chat> opt = chatRepo.findById(chatId);
		
		if(opt.isEmpty()) 
			throw new Exception("Chat not found with ID - " + chatId);
		
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(int userId) {
		return chatRepo.findByUsersId(userId);
	}

}
