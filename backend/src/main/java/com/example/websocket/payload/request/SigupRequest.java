/**
 * 
 */
package com.example.websocket.payload.request;

import java.util.List;

import com.example.websocket.model.Role;

import lombok.Data;

/**
 * @author PhongNc
 *	11:55:42 AM : Jun 26, 2023 
 */
@Data
public class SigupRequest {
	private Long userId;
	private String userName;
	private String adrres;
	private String cellPhone;
	private String email;
	private String fullName;
	private String password;
	private List<String> listRole;
	

}
