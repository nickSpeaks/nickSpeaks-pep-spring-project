package com.example.dto;

import lombok.Data;

@Data
public class AccountResponse {

    private final Integer accountId;
    private final String username;
    private final String password; 
    
}
