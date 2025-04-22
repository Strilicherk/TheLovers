package com.strilicherk.theloversapi.feature_auth.services.sms;

import com.strilicherk.theloversapi.core.exceptions.GenericException;
import com.strilicherk.theloversapi.core.exceptions.VerificationException;
import com.strilicherk.theloversapi.core.domain.model.phone_verification.PhoneVerification;
import com.strilicherk.theloversapi.feature_auth.repositories.sms.SendSmsCodeRepository;
import com.strilicherk.theloversapi.core.domain.model.user.User;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhoneVerificationServiceImpl implements PhoneVerificationService {
    private final SendSmsCodeRepository sendSmsCodeRepository;
    private final String serviceSid;

    @Autowired
    public PhoneVerificationServiceImpl(
            SendSmsCodeRepository sendSmsCodeRepository,
            @Value("${twilio.service-sid}") String serviceSid) {
        this.sendSmsCodeRepository = sendSmsCodeRepository;
        this.serviceSid = serviceSid;
    }

    @Override
    public void verifyCode(String phone, String smsCode) {
        try {
            log.info("Verify code: {}, {}", phone, smsCode);
            VerificationCheck verificationCheck = VerificationCheck.creator(serviceSid)
                    .setCode(smsCode)
                    .setTo(phone)
                    .create();

            if (!"approved".equalsIgnoreCase(verificationCheck.getStatus())) {
                log.warn("Phone verification failed: {}", verificationCheck.getStatus());
                throw new VerificationException(HttpStatus.UNAUTHORIZED, "Phone verification failed.");
            }

            log.info("Phone verification approved for {}", phone);

        } catch (ApiException e) {
            handleTwilioException(e, smsCode);
        } catch (Exception e) {
            log.error("Unexpected error during phone verification for {}: {}", phone, e.getMessage(), e);
            throw new GenericException("Error occurred during phone verification. Message: " + e.getMessage());
        }
    }

    @Override
    public void storeSuccessfulVerificationInDatabase(User user) {
        try {
            if (sendSmsCodeRepository.findByPhone(user.getPhone()) == null) {
                PhoneVerification phoneVerification = new PhoneVerification();
                phoneVerification.setUserId(user.getId());
                phoneVerification.setPhone(user.getPhone());
                phoneVerification.setVerified(true);
                sendSmsCodeRepository.save(phoneVerification);
            }
        } catch (Exception e) {
            log.error("Error storing phone verification: {}", e.getMessage(), e);
            throw new GenericException("Could not store phone verification.");
        }
    }

    private void handleTwilioException(ApiException e, String code) {
        int errorCode = e.getCode();
        switch (errorCode) {
            case 20404 -> {
                log.error("Verification service not found: {}", e.getMessage());
                throw new VerificationException(HttpStatus.NOT_FOUND, "Verification service not found.");
            }
            case 22113 -> {
                log.error("Invalid or expired verification code: {}", code);
                throw new VerificationException(HttpStatus.BAD_REQUEST, "Invalid or expired verification code.");
            }
            default -> {
                log.error("Twilio internal error: {}", e.getMessage());
                throw new VerificationException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred in Twilio.");
            }
        }
    }
}

