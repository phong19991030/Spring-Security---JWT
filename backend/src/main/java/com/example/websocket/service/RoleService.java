/**
 * 
 */
package com.example.websocket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.websocket.dao.RoleDao;
import com.example.websocket.model.Erole;
import com.example.websocket.model.Role;

/**
 * @author PhongNc
 *	1:24:28 PM : Jun 26, 2023 
 */
@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	public Optional<Role> findByRoleName(Erole roleName){
		return roleDao.findByRoleName(roleName);
	}

}
