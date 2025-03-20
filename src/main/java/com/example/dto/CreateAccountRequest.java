package com.example.dto;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class CreateAccountRequest {
    
    @NotBlank(message = "Username cannot be blank")
    private final String username;
    @Size(min=4, message = "Password should be at least 4 characters long")
    private final String password;

}
