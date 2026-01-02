package com.example.springpractice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDTO {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50,message = "Fist name should be between 2 and 50")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 50,message = "Last name should be between 2 and 50")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 500,message = "Learning goals must not exceed 500 characters")
    private String learningGoals;


}
