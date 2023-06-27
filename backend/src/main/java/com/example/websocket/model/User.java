/**
 * 
 */
package com.example.websocket.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author PhongNc
 *	9:32:06 AM : Jun 26, 2023 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer userId;
	private String userName;
	private String groupName;
	private String adrres;
	private String cellPhone;
	private String dob;
	private String email;
	private String fullName;
	private String gender;
	private String password;
	private List<Role> listRole;

}
