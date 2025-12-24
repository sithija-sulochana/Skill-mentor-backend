package com.example.springpractice.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {

    @NotNull(message = "cannot be null")
    @Size(min = 5, message = "Subject must be at least 5 characters long")
    private String subjectName;

    @Size(max = 100, message = "Description must not exceed 100 characters")
    private String description;

    @NotNull
    private Long mentorId;
}
