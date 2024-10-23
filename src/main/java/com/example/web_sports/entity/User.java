package com.example.web_sports.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String gmail;
    @Column(nullable = false)
    String password;

    @Column(nullable = true)
    String full_name;

    @Column(nullable = true)
    String img;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"), // Liên kết tới cột user_id trong bảng User
            inverseJoinColumns = @JoinColumn(name = "role_name") // Liên kết tới cột role_name trong bảng Role
    )
    Set<Role> roles;
}