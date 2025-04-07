package com.strilicherk.matchapp.services;

import com.strilicherk.matchapp.domain.phone_verification.PhoneVerification;
import com.strilicherk.matchapp.domain.shared.ResponseDTO;
import com.strilicherk.matchapp.exceptions.GenericException;
import com.strilicherk.matchapp.exceptions.VerificationException;
import com.strilicherk.matchapp.repositories.PhoneVerificationRepository;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhoneVerificationService {
    private final PhoneVerificationRepository phoneVerificationRepository;
    @Value("${twilio.serviceSid}")
    private String serviceSid;

    @Autowired
    public PhoneVerificationService(PhoneVerificationRepository phoneVerificationRepository) {
        this.phoneVerificationRepository = phoneVerificationRepository;
    }

    static {
        String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
        String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void requestPhoneVerification(String phone, UUID userId) {
        try {
            Verification verification = Verification.creator(serviceSid, phone, "sms").create();

            if ("pending".equals(verification.getStatus())) {
                PhoneVerification phoneVerification = new PhoneVerification();
                phoneVerification.setUserId(userId);
                phoneVerification.setPhone(phone);
                phoneVerificationRepository.save(phoneVerification);
            }
        } catch (ApiException e) {
            throw new VerificationException(e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            throw new GenericException("Error occurred during phone verification for phone: " + phone + ". Message: " + e.getMessage());
        }
    }

    public ResponseDTO<Boolean> verifyPhone(String phone, String verificationCode) {
        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(serviceSid)
                    .setCode(verificationCode)
                    .setTo(phone)
                    .create();
            if ("approved".equals(verificationCheck.getStatus())) {
                PhoneVerification phoneVerification = phoneVerificationRepository.findByPhone(phone);
                phoneVerification.setVerified(true);
                phoneVerificationRepository.save(phoneVerification);
                return new ResponseDTO<>(200, "Approved", Boolean.TRUE);
            } else {
                return new ResponseDTO<>(400, "Not approved", Boolean.FALSE);
            }
        } catch (ApiException e) {
            throw new VerificationException(e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            throw new GenericException("Error occurred during phone verification for phone: " + phone + ". Message: " + e.getMessage());
        }
    }
}
