package com.ems.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ems.management.models.Role;
import com.ems.management.repository.RoleRepository;
import com.ems.management.service.RoleService;

public class RoleServiceImpl implements RoleService{

	 @Autowired
	    private RoleRepository repo;

	    @Override
	    public Role createRole(Role role) {
	        return repo.save(role);
	    }

	    @Override
	    public Role getRoleById(Long id) {
	        return repo.findById(id).orElse(null);
	    }

	    @Override
	    public List<Role> getAllRoles() {
	        return repo.findAll();
	    }
}
