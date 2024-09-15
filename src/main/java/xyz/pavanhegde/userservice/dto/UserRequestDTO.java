package xyz.pavanhegde.userservice.dto;

import java.time.LocalDate;

public record UserRequestDTO(String firstName, String lastName, String email, String phone, LocalDate dateOfBirth) {
}