package com.example.web_sports.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String gmail;
    //@Size(min = 6, message = "INVALID_PASSWORD")
    String password;
    String full_name;
    String img;
    List<String> roles;
}