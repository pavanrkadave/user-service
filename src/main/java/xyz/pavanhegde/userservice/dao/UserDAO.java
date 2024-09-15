package xyz.pavanhegde.userservice.dao;

import xyz.pavanhegde.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();

    Optional<User> findById(Integer id);

    User saveUser(User user);

    void deleteById(Integer id);

    Optional<User> findByEmail(String email);

    User updateUser(User user);
}
