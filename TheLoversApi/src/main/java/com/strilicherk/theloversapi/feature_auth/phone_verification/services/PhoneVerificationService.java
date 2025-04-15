package com.strilicherk.theloversapi.feature_auth.phone_verification.services;

import com.strilicherk.theloversapi.domain.shared.ResponseDTO;
import com.strilicherk.theloversapi.feature_user.domain.model.User;
import com.strilicherk.theloversapi.exceptions.GenericException;
import com.strilicherk.theloversapi.exceptions.VerificationException;
import com.strilicherk.theloversapi.feature_user.repositories.UserRepository;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PhoneVerificationService {
    @Value("${twilio.serviceSid}")
    private String serviceSid;
    private final UserRepository userRepository;

    @Autowired
    public PhoneVerificationService(UserRepository userRepository) {this.userRepository = userRepository;}

    static {
        String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
        String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public ResponseDTO<Boolean> requestPhoneVerification(String phone) {
        try {
            Verification verification = Verification.creator(serviceSid, phone, "sms").create();
            log.info("Created verification: {}", verification);

            if ("pending".equals(verification.getStatus())) {
                Optional<User> existingUser = userRepository.findByPhone(phone);
                log.info("Existing user: {}", existingUser);
                return new ResponseDTO<>(200, "SMS code sent.", true, existingUser.isPresent());
            } else {
                log.error("Failed to create verification: {}", verification.getStatus());
                throw new VerificationException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
            }
        } catch (ApiException e) {
            if (e.getStatusCode() == 404) {
                throw new VerificationException(HttpStatus.NOT_FOUND, "Verification service not found.");
            } else if (e.getStatusCode() == 400) {
                throw new VerificationException(HttpStatus.BAD_REQUEST, "Invalid phone number.");
            } else {
                throw new VerificationException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
            }
        } catch (Exception e) {
            throw new GenericException("Error occurred during phone verification for phone: " + phone + ". Message: " + e.getMessage());
        }
    }
}
