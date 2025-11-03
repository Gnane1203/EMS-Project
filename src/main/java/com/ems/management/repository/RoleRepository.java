package com.ems.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ems.management.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(String roleName);
}
