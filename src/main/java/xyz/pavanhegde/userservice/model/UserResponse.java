package xyz.pavanhegde.userservice.model;

import java.time.LocalDate;

public record UserResponse(Integer id, String firstName, String lastName, String email, String phone,
                           LocalDate dateOfBirth, Integer age, LocalDate createdAt, LocalDate updatedAt) {
}
