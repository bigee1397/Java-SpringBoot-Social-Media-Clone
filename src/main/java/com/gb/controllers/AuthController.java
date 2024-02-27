package com.gb.controllers;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gb.config.JwtProvider;
import com.gb.entities.User;
import com.gb.repository.UserRepository;
import com.gb.request.LoginRequest;
import com.gb.response.AuthResponse;
import com.gb.service.CustomUserDetailsService;
import com.gb.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository ur;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomUserDetailsService cuds;
	
//	localhost:5454/auth/create/signup
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isExist = ur.findByeMail(user.geteMail());
		if(isExist!=null) 
			throw new Exception("Email is associated with another account.....");
		
		User newUser = new User();
		newUser.seteMail(user.geteMail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = ur.save(newUser);
		
		Authentication auth = new UsernamePasswordAuthenticationToken(savedUser.geteMail(), savedUser.getPassword());
		
		String token = JwtProvider.generateToken(auth);
		
		AuthResponse res = new AuthResponse(token, "Registration Successfull!!!");
		
		return res;
	}
	
//	/auth/signin
	@PostMapping("/signin")
	public AuthResponse singIn(@RequestBody LoginRequest loginRequest) {
		
		Authentication auth = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		
		String token = JwtProvider.generateToken(auth);
		
		AuthResponse res = new AuthResponse(token, "Login Successfull!!!");
		
		return res;
	}

	private Authentication authenticate(String email, String password) {
		
		UserDetails userDetails = cuds.loadUserByUsername(email);
		if(userDetails.equals(null))
			throw new BadCredentialsException("Invalid Username....!!!!");
		if(!passwordEncoder.matches(password, userDetails.getPassword()))
			throw new BadCredentialsException("Invalid Username or Password....!!!!");
		System.out.println(userDetails.getPassword());
		System.out.println(password);
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
}
