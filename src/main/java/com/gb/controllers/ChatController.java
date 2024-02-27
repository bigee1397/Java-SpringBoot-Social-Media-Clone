package com.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gb.entities.*;
import com.gb.repository.*;
import com.gb.response.*;
import com.gb.request.*;
import com.gb.service.*;

@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	public Chat createChat(@RequestBody CreateChatRequest req) {
		return null;
	}
	
}
