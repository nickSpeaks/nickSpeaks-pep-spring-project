package com.example.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() { super("User not found"); } 
    
}
