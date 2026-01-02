package com.example.springpractice.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {
    @NotNull(message = "Session ID cannot be null")
    private Long sessionId;

    @NotNull(message = "Student ID cannot be null")
    private Integer studentId;

    @Size(min = 2, max = 50, message = "Please enter lesser than 50 words")
    private String text;


}