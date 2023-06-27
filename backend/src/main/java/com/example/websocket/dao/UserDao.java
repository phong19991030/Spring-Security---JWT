/**
 * 
 */
package com.example.websocket.dao;

import com.example.websocket.model.User;

/**
 * @author PhongNc 9:37:26 AM : Jun 26, 2023
 */
public interface UserDao {

	User findByUserName(String userName);

	Integer existsByUserName(String userName) throws Exception;

	Integer existsByEmail(String email) throws Exception;

	User SaveUser(User user) throws Exception;

	 void insertUser(User user) throws Exception;

}
