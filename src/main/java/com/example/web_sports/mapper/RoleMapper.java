package com.example.web_sports.mapper;

import com.example.web_sports.dto.request.RoleRequest;
import com.example.web_sports.dto.response.RoleResponse;
import com.example.web_sports.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Hàm map từ RoleRequest sang Role (DTO -> Entity)
    public Role toRole(RoleRequest request) {
        // Sử dụng ModelMapper để ánh xạ từ DTO sang Entity
        return modelMapper.map(request, Role.class);
    }

    // Hàm map từ Role sang RoleResponse (Entity -> DTO)
    public RoleResponse toRoleResponse(Role role) {
        // Sử dụng ModelMapper để ánh xạ từ Entity sang DTO
        return modelMapper.map(role, RoleResponse.class);
    }
}
