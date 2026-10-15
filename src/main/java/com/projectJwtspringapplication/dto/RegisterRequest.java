package com.projectJwtspringapplication.dto;

public record RegisterRequest(
        String email,
        String password
) {
}
