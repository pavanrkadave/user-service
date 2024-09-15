package xyz.pavanhegde.userservice.dto;

import java.time.LocalDate;

public record UserResponseDTO(Integer id, String firstName, String lastName, String email, String phone,
                              LocalDate dateOfBirth, LocalDate createdAt, LocalDate updatedAt) {
}