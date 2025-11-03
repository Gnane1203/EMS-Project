package com.ems.management.service;

import java.util.List;

import com.ems.management.models.Role;

public interface RoleService {

	Role createRole(Role role);
	
    Role getRoleById(Long id);
    
    List<Role> getAllRoles();
}
