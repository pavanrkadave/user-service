package xyz.pavanhegde.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @Column(nullable = false)
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Must be a valid email")
    private String email;

    private String phone;
    private LocalDate dateOfBirth;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
