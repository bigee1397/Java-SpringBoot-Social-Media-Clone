package com.gb.entities;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.*;

@Entity
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String chat_name;
	private String chat_image;
	@ManyToMany
	private List<User> users = new ArrayList<>();
	private LocalDateTime timeStamp;
	public Chat() {
		super();
	}
	public Chat(int id, String chat_name, String chat_image, List<User> users, LocalDateTime timeStamp) {
		super();
		this.id = id;
		this.chat_name = chat_name;
		this.chat_image = chat_image;
		this.users = users;
		this.timeStamp = timeStamp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChat_name() {
		return chat_name;
	}
	public void setChat_name(String chat_name) {
		this.chat_name = chat_name;
	}
	public String getChat_image() {
		return chat_image;
	}
	public void setChat_image(String chat_image) {
		this.chat_image = chat_image;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
}
