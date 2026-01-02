package com.example.springpractice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String errorCode;
    private String timestamp;

    @Builder.Default // without this annotation, validationErrors could be null;
    private Map<String, String> validationErrors = new HashMap<>();
}


//This will be returned :

//{
//        "message": "Validation failed",
//        "errorCode": "BAD REQUEST",
//        "timestamp": "2025-01-01T10:30:12",
//        "validationErrors": {
//        "email": "must be a valid email",
//        "password": "minimum 8 characters"
//        }
//        }