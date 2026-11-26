package mx.edu.utez.productosval.dto;

import java.time.LocalDateTime;

public record CustomErrorResponse(
    String message,
    int status,
    LocalDateTime timestamp
) {}