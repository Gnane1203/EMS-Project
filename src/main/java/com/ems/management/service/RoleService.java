package com.ems.management.service;

import java.util.List;

import com.ems.management.dto.request.RoleDTO;
import com.ems.management.models.Role;



public interface RoleService {

    RoleDTO createRole(RoleDTO dto);

    RoleDTO getRoleById(Long id);

    List<RoleDTO> getAllRoles();

    RoleDTO updateRole(Long id, RoleDTO dto);

    void deleteRole(Long id);
    Role getRoleEntityById(Long id);
}
