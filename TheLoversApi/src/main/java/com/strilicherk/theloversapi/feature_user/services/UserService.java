package com.strilicherk.theloversapi.feature_user.services;

import com.strilicherk.theloversapi.domain.shared.ResponseDTO;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserUpdateDTO;
import com.strilicherk.theloversapi.feature_user.domain.model.User;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserCreateDTO;
import com.strilicherk.theloversapi.feature_user.exceptions.UserAlreadyExistsException;
import com.strilicherk.theloversapi.feature_user.exceptions.UserNotFoundException;
import com.strilicherk.theloversapi.feature_user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                throw new UserAlreadyExistsException("User already exists.");
            }

            User user = User.createFromDTO(data.phone());
            userRepository.save(user);

            log.info("User created successfully.");
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User updateUser(UUID userId, UserUpdateDTO data) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);

            if (optionalUser.isEmpty()) {
                throw new UserNotFoundException("Can't update user infos because user does not exist.");
            }

            User user = optionalUser.get();
            user.updateFromDTO(data);
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while updating the user.", e);
        }

    }
}