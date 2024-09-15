package xyz.pavanhegde.userservice.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import xyz.pavanhegde.userservice.dao.UserDAO;
import xyz.pavanhegde.userservice.model.User;
import xyz.pavanhegde.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        log.info("UserDAOImpl::findAll");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        log.info("UserDAOImpl::findById ID={}", id);
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        log.info("UserDAOImpl::saveUser {}", user);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("UserDAOImpl::deleteById ID={}", id);
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("UserDAOImpl::findByEmail email={}", email);
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @Override
    public User updateUser(User user) {
        log.info("UserDAOImpl::updateUser {}", user);
        return userRepository.save(user);
    }
}