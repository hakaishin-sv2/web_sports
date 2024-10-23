package com.example.web_sports.api.admin;

import com.example.web_sports.dto.request.AdminSetRoleRequest;
import com.example.web_sports.dto.request.TokenRequest;
import com.example.web_sports.dto.request.UserCreationRequest;
import com.example.web_sports.dto.request.UserUpdateRequest;
import com.example.web_sports.dto.response.ApiResponse;
import com.example.web_sports.dto.response.UserResponse;
import com.example.web_sports.exceptions.AppException;
import com.example.web_sports.services.AuthenticationService;
import com.example.web_sports.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminApi {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/list-users")
    public ApiResponse<List<UserResponse>> getAllUsers() {

        List<UserResponse> users = userService.getUsers();
        return ApiResponse.<List<UserResponse>>builder()
                .result(users)
                .message("Lấy danh sách người dùng thành công")
                .build();
    }
    @GetMapping("/user/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        // Lấy thông tin người dùng theo ID
        UserResponse user = userService.getUserByid(id);
            return ApiResponse.<UserResponse>builder()
                    .result(user)
                    .message("Lấy thông tin người dùng thành công")
                    .build();
    }

    @GetMapping("/profile/{id}")
    public ApiResponse<UserResponse> getProfile(@PathVariable Long id) {
        UserResponse user = userService.getUserByid(id);
        return ApiResponse.<UserResponse>builder()
                .result(user)
                .message("Lấy thông tin người dùng thành công")
                .build();
    }
    @PutMapping("/user/{id}")
    public ApiResponse<UserResponse> updateRoles(@PathVariable Long id, @RequestBody AdminSetRoleRequest request) {
        UserResponse user = userService.updateUser(id, request);
        return ApiResponse.<UserResponse>builder()
                .result(user)
                .message("Cập nhật thông tin người dùng thành công")
                .build();
    }
    @DeleteMapping("/user/{id}")
    public ApiResponse<Void> deleteUserById(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
