package com.ems.management.mappers;


import com.ems.management.dto.request.RoleDTO;
import com.ems.management.models.Role;

public class RoleMapper {

    public static RoleDTO toDTO(Role role) {
        if (role == null) return null;

        return new RoleDTO(
            role.getRoleId(),
            role.getRoleName()
        );
    }

    public static Role toEntity(RoleDTO dto) {
        if (dto == null) return null;

        Role role = new Role();
        role.setRoleId(dto.getRoleId());
        role.setRoleName(dto.getRoleName());
        return role;
    }
}
