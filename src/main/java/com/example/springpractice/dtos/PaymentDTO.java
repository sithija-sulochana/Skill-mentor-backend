package com.example.springpractice.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class PaymentDTO {
    @NotNull(message = "Student ID cannot be null")
    private Integer studentId;

    @NotNull(message = "Session Id cannot be null")
    private Integer sessionId;

    @NotNull(message = "Receipt url can't be null")
    private String receipt_url;

    @NotNull(message = "Notes can't be null")
    @Size(min = 5, max = 50)
    private String note;
}
