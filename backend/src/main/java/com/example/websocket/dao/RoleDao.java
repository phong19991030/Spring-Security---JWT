/**
 * 
 */
package com.example.websocket.dao;

import java.util.Optional;

import com.example.websocket.model.Erole;
import com.example.websocket.model.Role;

/**
 * @author PhongNc
 *	9:39:02 AM : Jun 26, 2023 
 */
public interface RoleDao {
	Optional<Role> findByRoleName(Erole roleName);

}
