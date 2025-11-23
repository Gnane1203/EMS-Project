package com.ems.management.service.impl;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.ems.management.dto.request.RoleDTO;
import com.ems.management.mappers.RoleMapper;
import com.ems.management.models.Role;
import com.ems.management.repository.RoleRepository;
import com.ems.management.service.RoleService;






@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // CREATE
    public RoleDTO createRole(RoleDTO dto) {
        Role role = RoleMapper.toEntity(dto);
        Role saved = roleRepository.save(role);
        return RoleMapper.toDTO(saved);
    }

    // GET BY ID
    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        return RoleMapper.toDTO(role);
    }

    // GET ALL
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    public RoleDTO updateRole(Long id, RoleDTO dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        role.setRoleName(dto.getRoleName());

        Role updated = roleRepository.save(role);
        return RoleMapper.toDTO(updated);
    }

    // DELETE
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }
    
    @Override
    public Role getRoleEntityById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

}
