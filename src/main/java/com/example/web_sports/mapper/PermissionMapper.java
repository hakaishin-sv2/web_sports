package com.example.web_sports.mapper;

import com.example.web_sports.dto.request.PermissionRequest;
import com.example.web_sports.dto.response.PermissionResponse;
import com.example.web_sports.entity.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Ánh xạ từ Permission sang PermissionResponse
    public PermissionResponse toPermissionResponse(Permission permission) {
        return modelMapper.map(permission, PermissionResponse.class);
    }

    // Ánh xạ từ PermissionRequest sang Permission
    public Permission toPermission(PermissionRequest permissionRequest) {
        return modelMapper.map(permissionRequest, Permission.class);
    }
}
