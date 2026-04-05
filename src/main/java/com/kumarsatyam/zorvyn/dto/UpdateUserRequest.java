package com.kumarsatyam.zorvyn.dto;

import com.kumarsatyam.zorvyn.model.Role;
import com.kumarsatyam.zorvyn.model.UserStatus;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private String password; // Optional
    private Role role; // Optional
    private UserStatus status; // Optional
}
