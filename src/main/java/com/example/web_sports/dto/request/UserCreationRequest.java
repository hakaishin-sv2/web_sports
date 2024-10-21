package com.example.web_sports.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    String gmail;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;
    String full_name;
    List<String> roles;
}