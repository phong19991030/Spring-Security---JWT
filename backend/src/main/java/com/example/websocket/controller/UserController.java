/**
 * 
 */
package com.example.websocket.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.example.websocket.jwt.JwtTokenProvider;
import com.example.websocket.model.Erole;
import com.example.websocket.model.Role;
import com.example.websocket.model.User;
import com.example.websocket.payload.request.LoginRequest;
import com.example.websocket.payload.request.SigupRequest;
import com.example.websocket.payload.response.JwtResponse;
import com.example.websocket.payload.response.MessageResponse;
import com.example.websocket.security.CustomUserDetails;
import com.example.websocket.service.RoleService;
import com.example.websocket.service.UserService;

/**
 * @author PhongNc 1:02:56 PM : Jun 26, 2023
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/auth")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder encoder;

	@CrossOrigin(origins = "*")
	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<?> registerUser(@RequestBody SigupRequest signupRequest) {
		try {
			if (userService.existsByEmail(signupRequest.getEmail())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already"));
			}
			if (userService.existsByUserName(signupRequest.getUserName())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Usermame is already"));
			}
			User user = new User();
			user.setUserName(signupRequest.getUserName());
			user.setPassword(encoder.encode(signupRequest.getPassword()));
			user.setEmail(signupRequest.getEmail());
			List<String> strRoles = signupRequest.getListRole();
			List<Role> listRoles = new ArrayList<>();
			if (strRoles == null) {
				// User quyen mac dinh
				Role userRole = roleService.findByRoleName(Erole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
				listRoles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
//	                    	Erole.ROLE_ADMIN
						Role adminRole = roleService.findByRoleName(Erole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
						listRoles.add(adminRole);
						break;
					case "moderator":
						Role modRole = roleService.findByRoleName(Erole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
						listRoles.add(modRole);
						break;
					case "user":
						Role userRole = roleService.findByRoleName(Erole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
						listRoles.add(userRole);
						break;
					}
				});
			}
			user.setListRole(listRoles);
			userService.saveOrUpdate(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(new MessageResponse("User registered successfully"));
	}

	@CrossOrigin
	@PostMapping("/signin")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		CustomUserDetails customUserDetail = (CustomUserDetails) authentication.getPrincipal();
		// Sinh JWT tra ve client
		String jwt = tokenProvider.generateToken(customUserDetail);
		try {
			jwt = tokenProvider.generateToken(customUserDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Lay cac quyen cua user
		List<String> listRoles = customUserDetail.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, customUserDetail.getUserId(), customUserDetail.getUsername(),
				customUserDetail.getEmail(), listRoles));
	}

}
