package xyz.pavanhegde.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.pavanhegde.userservice.dto.UserRequestDTO;
import xyz.pavanhegde.userservice.dto.UserResponseDTO;
import xyz.pavanhegde.userservice.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        log.info("UserController::getAllUsers - Received request to fetch all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        log.info("UserController::getUserById - Received request to fetch user by ID: {}", id);
        Optional<UserResponseDTO> user = userService.getUserById(id);
        if (user.isPresent()) {
            log.info("UserController::getUserById - User found with ID: {}", id);
            return ResponseEntity.ok(user.get());
        } else {
            log.warn("UserController::getUserById - User not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Create a user
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        log.info("UserController::createUser - Received request to create user with email: {}", userRequestDTO.email());
        return ResponseEntity.status(CREATED).body(userService.saveUser(userRequestDTO));
    }

    // Update user by ID
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id, @RequestBody UserRequestDTO userRequestDTO) {
        log.info("UserController::updateUser - Received request to update user with ID: {}", id);
        Optional<UserResponseDTO> updatedUser = userService.updateUser(id, userRequestDTO);

        if (updatedUser.isPresent()) {
            log.info("UserController::updateUser - User with ID: {} updated successfully", id);
            return ResponseEntity.ok(updatedUser.get());
        } else {
            log.warn("UserController::updateUser - User not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Find user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(@PathVariable String email) {
        log.info("UserController::findByEmail - Received request to fetch user by email: {}", email);
        Optional<UserResponseDTO> user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            log.info("UserController::findByEmail - User found with email: {}", email);
            return ResponseEntity.ok(user.get());
        } else {
            log.warn("UserController::findByEmail - User not found with email: {}", email);
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        log.info("UserController::deleteUser - Received request to delete user by ID: {}", id);
        userService.deleteUser(id);
        log.info("UserController::deleteUser - User with ID: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}