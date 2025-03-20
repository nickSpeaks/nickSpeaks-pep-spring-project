package com.example.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateMessageRequest {

    @NotNull
    private final Integer postedBy;

    @NotBlank
    @Size(max = 255)
    private final String messageText;
    
    private final Long timePostedEpoch;
}
