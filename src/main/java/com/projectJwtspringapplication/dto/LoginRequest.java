package com.projectJwtspringapplication.dto;

public record LoginRequest(
        String email,
        String password
) {
}
