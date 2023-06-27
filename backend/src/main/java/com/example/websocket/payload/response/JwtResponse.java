/**
 * 
 */
package com.example.websocket.payload.response;

import java.util.List;

import com.example.websocket.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author PhongNc 11:58:24 AM : Jun 26, 2023
 */

@Data
@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Integer userId;
	private String userName;
	private String email;
	private List<String> listRole;

	public JwtResponse(String token,  Integer userId, String userName, String email, List<String> listRole) {
		super();
		this.token = token;
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.listRole = listRole;
	}

}
