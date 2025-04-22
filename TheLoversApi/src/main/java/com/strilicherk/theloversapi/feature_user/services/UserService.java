package com.strilicherk.theloversapi.feature_user.services;

import com.strilicherk.theloversapi.feature_user.domain.dto.UserUpdateDTO;
import com.strilicherk.theloversapi.core.domain.model.user.User;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserCreateDTO;
import com.strilicherk.theloversapi.feature_user.exceptions.UserNotFoundException;
import com.strilicherk.theloversapi.feature_user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreateDTO data) {
        try {
            Optional<User> existingUser = userRepository.findByPhone(data.phone());

            if (existingUser.isPresent()) {
                log.info("User already exists.");
                return existingUser.get();
            }

            User user = new User();
            user.setPhone(data.phone());
            userRepository.save(user);

            log.info("User created successfully.");
            return user;
        } catch (DataIntegrityViolationException e) {
            log.error("Error while trying to save new user: invalid data or absent data");
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public User updateUser(UUID userId, UserUpdateDTO data) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);

            if (optionalUser.isEmpty()) {
                throw new UserNotFoundException("Can't update user infos because user does not exist.");
            }

            User user = optionalUser.get();
            user.update(data.email(), data.email(), null);
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while updating the user.", e);
        }

    }
}