package com.example.web_sports.mapper;

import com.example.web_sports.dto.request.AdminSetRoleRequest;
import com.example.web_sports.dto.request.UserCreationRequest;
import com.example.web_sports.dto.request.UserUpdateRequest;
import com.example.web_sports.dto.response.UserResponse;
import com.example.web_sports.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Hàm map từ UserCreationRequest sang User (DTO -> Entity)
    public User toUser(UserCreationRequest request) {
        return modelMapper.map(request, User.class);
    }

        public UserResponse toUserResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    // Hàm cập nhật User từ UserUpdateRequest (DTO -> Entity)
    public void updateUser(User user, UserUpdateRequest request) {
        modelMapper.map(request, user);
    }
    public void toUpdateRole(User user, AdminSetRoleRequest request) {
        modelMapper.map(request, user);
    }
}
