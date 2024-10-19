package com.example.web_sports.services;

import com.example.web_sports.dto.request.RoleRequest;
import com.example.web_sports.dto.response.RoleResponse;
import com.example.web_sports.entity.Role;
import com.example.web_sports.mapper.RoleMapper;

import com.example.web_sports.repositories.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;

    RoleMapper roleMapper;


    public List<RoleResponse> getAll(){
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String role){
        roleRepository.deleteById(role);
    }

    public RoleResponse create(RoleRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be empty");
        }

        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        Role savedRole = roleRepository.save(role);
        return roleMapper.toRoleResponse(savedRole);
    }
}