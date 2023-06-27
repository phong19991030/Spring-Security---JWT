/**
 * 
 */
package com.example.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author PhongNc 4:15:27 PM : Jun 26, 2023
 */
@Data
@AllArgsConstructor
public class UserRole {
	private Integer userId;
	private Integer roleId;

}
