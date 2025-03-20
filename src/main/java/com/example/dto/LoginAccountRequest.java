package com.example.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class LoginAccountRequest {

    @NotBlank(message = "Usename cannot be blank")
    private final String username;
    @NotBlank(message = "Password cannot be blank")
    private final String password;
    
}
