package com.gb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping
	public String homeControllerHandler() {
		return "Hello welcome page";
	}
	
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "home 2";
	}
}