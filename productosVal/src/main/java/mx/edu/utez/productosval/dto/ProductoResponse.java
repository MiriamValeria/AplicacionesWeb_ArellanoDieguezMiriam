package mx.edu.utez.productosval.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductoResponse(
    Long id,
    @NotBlank(message = "Name is mandatory")
    String name,
    @Positive
    Double price,
    @PositiveOrZero
    int stock,
    Long categoryId,
    String categoryName
) {
}