package com.example.projet_mairie.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(int status, LocalDateTime time, String code, String message) {
}
