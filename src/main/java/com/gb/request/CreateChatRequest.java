package com.gb.request;

import com.gb.entities.*;

public class CreateChatRequest {

	private User reqUser;
	private User user2;
	public CreateChatRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateChatRequest(User reqUser, User user2) {
		super();
		this.reqUser = reqUser;
		this.user2 = user2;
	}
	public User getReqUser() {
		return reqUser;
	}
	public void setReqUser(User reqUser) {
		this.reqUser = reqUser;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
}
