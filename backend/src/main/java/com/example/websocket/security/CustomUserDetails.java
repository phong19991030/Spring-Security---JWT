/**
 * 
 */
package com.example.websocket.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.websocket.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author PhongNc 9:44:21 AM : Jun 26, 2023
 */

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

	private Integer userId;
	private String userName;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

//	Map user to UserDetail
	public static CustomUserDetails mapUserToUserDetail(User user) {
		List<GrantedAuthority> listAuthorities = user.getListRole().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
		CustomUserDetails result = new CustomUserDetails(
				user.getUserId(),
				user.getUserName(), 
				user.getEmail(),
				user.getPassword(),
				listAuthorities);
		return result;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
