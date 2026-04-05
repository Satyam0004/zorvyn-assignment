package com.kumarsatyam.zorvyn.dto;

import com.kumarsatyam.zorvyn.model.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateRecordRequest {
    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    @NotBlank(message = "Category is required")
    private String category;

    private LocalDateTime date;

    private String notes;
}
