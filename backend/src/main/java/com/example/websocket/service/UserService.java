/**
 * 
 */
package com.example.websocket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.websocket.dao.RoleDao;
import com.example.websocket.dao.UserDao;
import com.example.websocket.dao.UserRoleDao;
import com.example.websocket.model.Role;
import com.example.websocket.model.User;
import com.example.websocket.model.UserRole;

/**
 * @author PhongNc 10:00:23 AM : Jun 26, 2023
 */
@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRoleDao userRoleDao;
	
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	public boolean existsByUserName(String userName) throws Exception {
		Integer integer = userDao.existsByUserName(userName);
		return integer > 0 ? true : false;

	}

	public boolean existsByEmail(String email) throws Exception {
		Integer integer = userDao.existsByEmail(email);
		return integer > 0 ? true : false;
	}

	/**
	 * @param user
	 */
	public void saveOrUpdate(User user) throws Exception {
		userDao.insertUser(user);
		Integer userId = user.getUserId();
		List<Role> listRole = user.getListRole();
		for (Role role : listRole) {
			userRoleDao.saveUserRole(new UserRole(userId, role.getRoleId()));
		}
	}

}
