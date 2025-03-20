package com.example.dto;

import lombok.Data;

@Data
public class MessageResponse {
    
    private final Integer messageId;
    private final Integer postedBy;
    private final String messageText;
    private final Long timePostedEpoch;
    
}
