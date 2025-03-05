package com.strilicherk.matchapp.services;

import com.strilicherk.matchapp.domain.user.User;
import com.strilicherk.matchapp.domain.user.UserCreateDTO;
import com.strilicherk.matchapp.repositories.GenderRepository;
import com.strilicherk.matchapp.repositories.UserLocationRepository;
import com.strilicherk.matchapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void createUser(UserCreateDTO data) {
        User user;

        try {
            Optional<User> existingUser = userRepository.findByPhone(data.phone());

            if (existingUser.isEmpty()) {
                user = new User();
                user.setPhone(data.phone());
                userRepository.save(user);
            } else {
                user = existingUser.get();
            }

            phoneVerificationService.requestPhoneVerification(data.phone(), user.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}