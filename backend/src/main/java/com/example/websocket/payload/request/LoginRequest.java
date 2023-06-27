/**
 * 
 */
package com.example.websocket.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author PhongNc
 *	11:57:41 AM : Jun 26, 2023 
 */
@Data
@AllArgsConstructor
public class LoginRequest {
	private String userName;
	private String password;

}
