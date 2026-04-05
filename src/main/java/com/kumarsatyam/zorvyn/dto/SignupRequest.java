package com.kumarsatyam.zorvyn.dto;

import com.kumarsatyam.zorvyn.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    private Role role; // Optional, defaults to something if not provided
}
