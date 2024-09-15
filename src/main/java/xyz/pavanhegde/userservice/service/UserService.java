package xyz.pavanhegde.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.pavanhegde.userservice.dao.UserDAO;
import xyz.pavanhegde.userservice.dto.UserRequestDTO;
import xyz.pavanhegde.userservice.dto.UserResponseDTO;
import xyz.pavanhegde.userservice.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserDAO userDAO;

    // Get all users
    public List<UserResponseDTO> getAllUsers() {
        log.info("UserService::getAllUsers");
        return userDAO.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get user by ID
    public Optional<UserResponseDTO> getUserById(Integer id) {
        log.info("UserService::getUserById ID={}", id);
        return userDAO.findById(id)
                .map(this::mapToResponseDTO);
    }

    // Create or update a user
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        log.info("UserService::saveUser {}", userRequestDTO.email());
        User user = mapToEntity(userRequestDTO);
        log.info("UserService::saveUser Users to be saved {}", user);
        return mapToResponseDTO(userDAO.saveUser(user));
    }

    // Update a user
    public Optional<UserResponseDTO> updateUser(Integer id, UserRequestDTO userRequestDTO) {
        log.info("UserService::updateUser ID={} data={}", id, userRequestDTO);
        return userDAO.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(userRequestDTO.firstName());
                    existingUser.setLastName(userRequestDTO.lastName());
                    existingUser.setEmail(userRequestDTO.email());
                    existingUser.setPhone(userRequestDTO.phone());
                    existingUser.setDateOfBirth(userRequestDTO.dateOfBirth());
                    User updatedUser = userDAO.updateUser(existingUser); // Call explicit update method
                    log.info("UserService::updateUser Updated user with ID: {}", id);
                    return mapToResponseDTO(updatedUser);
                });
    }

    // Delete a user
    public void deleteUser(Integer id) {
        log.info("UserService::deleteUser ID={}", id);
        userDAO.deleteById(id);
        log.info("UserService::deleteUser delete successful ID={}", id);
    }

    // Find user by email
    public Optional<UserResponseDTO> getUserByEmail(String email) {
        log.info("UserService::getUserByEmail email={}", email);
        return userDAO.findByEmail(email)
                .map(this::mapToResponseDTO);
    }

    // Mapping User entity to UserResponseDTO
    private UserResponseDTO mapToResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getDateOfBirth(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    // Mapping UserRequestDTO to User entity
    private User mapToEntity(UserRequestDTO userRequestDTO) {
        return User.builder()
                .firstName(userRequestDTO.firstName())
                .lastName(userRequestDTO.lastName())
                .email(userRequestDTO.email())
                .phone(userRequestDTO.phone())
                .dateOfBirth(userRequestDTO.dateOfBirth())
                .build();
    }
}