package com.example.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateMessageRequest {

    @NotBlank
    @Size(max = 255)
    private final String messageText;
    
}
