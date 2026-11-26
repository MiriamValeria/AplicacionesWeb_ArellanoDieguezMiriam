package com.example.productosVal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(
        @NotBlank(message = "Name is mandatory")
        String name,

        @NotNull(message = "Price is mandatory")
        @Positive(message = "Price must be greater than 0")
        Double price,

        @PositiveOrZero(message = "Stock cannot be negative")
        int stock) {
}
