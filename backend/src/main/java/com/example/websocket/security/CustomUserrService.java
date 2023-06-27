/**
 * 
 */
package com.example.websocket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.websocket.model.User;
import com.example.websocket.service.UserService;

/**
 * @author PhongNc
 *	9:59:07 AM : Jun 26, 2023 
 */
@Service
public class CustomUserrService implements UserDetailsService{
	@Autowired 
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.findByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException(username + "not fount");
		}
		return CustomUserDetails.mapUserToUserDetail(user);
	}

}
