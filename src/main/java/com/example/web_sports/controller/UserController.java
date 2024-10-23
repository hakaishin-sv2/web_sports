package com.example.web_sports.controller;

import com.example.web_sports.dto.request.UserCreationRequest;
import com.example.web_sports.dto.request.UserUpdateRequest;
import com.example.web_sports.dto.response.ApiResponse;
import com.example.web_sports.dto.response.UserResponse;
import com.example.web_sports.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @RequestMapping(value = {"/index","/"}, method = RequestMethod.GET)
    public String index(Model model) {
//        List<UserResponse> users = userService.getAllUsers();
//        model.addAttribute("users", users);
        return "user/index.html";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String createUserForm() {
        log.info("Navigating to create user form page");
        return "user/register";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        log.info("Navigating to create user form page");
        return "user/login";
    }
//    @PostMapping
//    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.createUser(request))
//                .build();
//    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        //log.info("Navigating to create user form page");
        return "user/login";
    }

//    @GetMapping
//    ApiResponse<List<UserResponse>> getUsers(){
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        log.info("Username: {}", authentication.getName());
//        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
//
//        return ApiResponse.<List<UserResponse>>builder()
//                .result(userService.getUsers())
//                .build();
//    }

//    @GetMapping("/{userId}")
//    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId){
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.getUser(userId))
//                .build();
//    }

//    @GetMapping("/myInfo")
//    ApiResponse<UserResponse> getMyInfo(){
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.getMyInfo())
//                .build();
//    }
//
//    @DeleteMapping("/{userId}")
//    ApiResponse<String> deleteUser(@PathVariable String userId){
//        userService.deleteUser(userId);
//        return ApiResponse.<String>builder()
//                .result("User has been deleted")
//                .build();
//    }
//
//    @PutMapping("/{userId}")
//    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.updateUser(userId, request))
//                .build();
//    }
}