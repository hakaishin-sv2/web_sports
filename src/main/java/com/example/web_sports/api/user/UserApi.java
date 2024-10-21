package com.example.web_sports.api.user;

import com.example.web_sports.dto.request.AuthenticationRequest;
import com.example.web_sports.dto.request.UserCreationRequest;
import com.example.web_sports.dto.response.ApiResponse;
import com.example.web_sports.dto.response.AuthenticationResponse;
import com.example.web_sports.dto.response.UserResponse;
import com.example.web_sports.services.AuthenticationService;
import com.example.web_sports.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserApi {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;
    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .message("Tạo tại khoản thành công")
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(request))
                .message("Đăng nhập thành công")
                .build();
    }
}
