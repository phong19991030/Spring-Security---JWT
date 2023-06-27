/**
 * 
 */
package com.example.websocket.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PhongNc 10:41:24 AM : Jun 27, 2023
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/get")
	public String allAccess() {
		return "Admin Content";
	}

}
