package com.strilicherk.matchapp.services;

import com.strilicherk.matchapp.domain.shared.ResponseDTO;
import com.strilicherk.matchapp.domain.user.User;
import com.strilicherk.matchapp.domain.user.UserCreateDTO;
import com.strilicherk.matchapp.repositories.GenderRepository;
import com.strilicherk.matchapp.repositories.UserLocationRepository;
import com.strilicherk.matchapp.repositories.UserRepository;
import com.strilicherk.matchapp.services.login.PhoneVerificationService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final GenderRepository genderRepository;
    private final UserLocationRepository userLocationRepository;
    private final UserRepository userRepository;
    private final PhoneVerificationService phoneVerificationService;

    public UserService(GenderRepository genderRepository,
                       UserLocationRepository userLocationRepository,
                       UserRepository userRepository,
                       PhoneVerificationService phoneVerificationService) {
        this.genderRepository = genderRepository;
        this.userLocationRepository = userLocationRepository;
        this.userRepository = userRepository;
        this.phoneVerificationService = phoneVerificationService;
    }

    public ResponseDTO<String> createUser(UserCreateDTO data) {
        User user;
        UUID userId;
        String message;

        try {
            Optional<User> existingUser = userRepository.findByPhone(data.phone());
            if (existingUser.isEmpty()) {
                user = new User();
                user.setPhone(data.phone());
                userId = user.getId();
                userRepository.save(user);
                message = "User created successfully. ";
            } else {
                user = existingUser.get();
                userId = existingUser.get().getId();
                message = "User already exists. ";
            }

            phoneVerificationService.requestPhoneVerification(data.phone(), userId);
            return new ResponseDTO<>(200, message + "Sending verification code.", true, user.getPhone());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}