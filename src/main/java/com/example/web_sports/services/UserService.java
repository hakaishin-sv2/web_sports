package com.example.web_sports.services;

import com.example.web_sports.dto.request.UserCreationRequest;
import com.example.web_sports.dto.request.UserUpdateRequest;
import com.example.web_sports.dto.response.ApiResponse;
import com.example.web_sports.dto.response.UserResponse;
import com.example.web_sports.entity.Role;
import com.example.web_sports.entity.User;

import com.example.web_sports.exceptions.AppException;
import com.example.web_sports.exceptions.ErrorCode;
import com.example.web_sports.mapper.UserMapper;
import com.example.web_sports.repositories.RoleRepository;
import com.example.web_sports.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByGmail(request.getGmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        roles.add(userRole);
        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }


    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByGmail(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.convertToEntity(request);
        if(request.getRoles() != null) {
            var roles = roleRepository.findAllById(request.getRoles());
            user.setRoles(new HashSet<>(roles));
        }
        user.setImg(request.getImg());
        return userMapper.toUserResponse(userRepository.save(user));
    }
    public UserResponse userUpdate(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, request);
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        } else {
            String password = user.getPassword();
            user.setPassword(password);
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(userRepository.save(user));

    }
    // delete theo permission
//    @PreAuthorize("hasAuthority('DELETE_USERS')")
//    public void deleteUser(Long userId){
//        userRepository.deleteById(userId);
//    }
    public ApiResponse<Void> deleteUser(Long userId) {
        try {
            if (!userRepository.existsById(userId)) {
                throw new AppException(ErrorCode.USER_NOT_EXISTED);
            }
            userRepository.deleteById(userId);
            return ApiResponse.<Void>builder()
                    .message("Xóa người dùng thành công")
                    .build();

        } catch (AppException e) {
            return ApiResponse.<Void>builder()
                    .code(404) // Mã lỗi
                    .message(e.getMessage())
                    .build();

        } catch (Exception e) {
            return ApiResponse.<Void>builder()
                    .code(500) // Mã lỗi nội bộ server
                    .message("Đã xảy ra lỗi khi xóa người dùng")
                    .build();
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers(){
        log.info("In method get Users");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUserByid(Long id){
        log.info("In method get user by Id");
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

//    public void saveFilePathToUser(String filePath) {
//        Long userId = getCurrentUserId(); // Lấy ID người dùng hiện tại
//
//        // Tìm người dùng và cập nhật đường dẫn
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
//
//        // Giả định bạn có một trường để lưu đường dẫn tệp
//        user.setFilePath(filePath); // Cập nhật lại trường phù hợp với cấu trúc của bạn
//        userRepository.save(user);
//    }

}