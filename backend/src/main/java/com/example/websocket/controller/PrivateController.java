/**
 * 
 */
package com.example.websocket.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PhongNc
 *	4:34:01 PM : Jun 26, 2023 
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/test")
public class PrivateController {
	@GetMapping("/all")
    public String allAccess(){
        return "Public Content";
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess(){
        return "UserContent";
    }
    @GetMapping("/mod")
//    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
