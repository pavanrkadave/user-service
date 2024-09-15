package xyz.pavanhegde.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.pavanhegde.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
