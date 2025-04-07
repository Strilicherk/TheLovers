package com.strilicherk.theloversapi.services.login;

import com.strilicherk.theloversapi.domain.phone_verification.PhoneVerification;
import com.strilicherk.theloversapi.domain.shared.ResponseDTO;
import com.strilicherk.theloversapi.exceptions.GenericException;
import com.strilicherk.theloversapi.exceptions.VerificationException;
import com.strilicherk.theloversapi.repositories.PhoneVerificationRepository;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
                PhoneVerification existingPhoneVerification = phoneVerificationRepository.findByPhone(phone);
                if (existingPhoneVerification == null) {
                    PhoneVerification phoneVerification = new PhoneVerification();
                    phoneVerification.setUserId(userId);
                    phoneVerification.setPhone(phone);
                    phoneVerificationRepository.save(phoneVerification);
                }
            }
        } catch (ApiException e) {
            if (e.getStatusCode() == 404) {
                throw new VerificationException(HttpStatus.NOT_FOUND, "Verification service not found.");
            } else if (e.getStatusCode() == 400) {
                throw new VerificationException(HttpStatus.BAD_REQUEST, "Invalid verification code.");
            } else {
                throw new VerificationException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
            }
        } catch (Exception e) {
            throw new GenericException("Error occurred during phone verification for phone: " + phone + ". Message: " + e.getMessage());
        }
    }

    public ResponseDTO<String> verifyPhone(String phone, String verificationCode) {
        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(serviceSid)
                    .setCode(verificationCode)
                    .setTo(phone)
                    .create();
            if ("approved".equals(verificationCheck.getStatus())) {
                PhoneVerification phoneVerification = phoneVerificationRepository.findByPhone(phone);
                phoneVerification.setVerified(true);
                phoneVerificationRepository.save(phoneVerification);
                return new ResponseDTO<>(200, "Phone verified successfully.", true, verificationCheck.getStatus());
            } else if ("expired".equals(verificationCheck.getStatus())) {
                return new ResponseDTO<>(410, "Expired verification code.", false, verificationCheck.getStatus());
            } else {
                return new ResponseDTO<>(400, "Invalid verification code.", false, verificationCheck.getStatus());
            }
        } catch (ApiException e) {
            int errorCode = e.getCode();
            System.out.println(errorCode);

            if (errorCode == 20404) {
                throw new VerificationException(HttpStatus.NOT_FOUND, "Verification service not found.");
            } else if (errorCode == 22113) {
                throw new VerificationException(HttpStatus.BAD_REQUEST, "Invalid or expired verification code.");
            } else {
                throw new VerificationException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred in Twilio.");
            }

        } catch (Exception e) {
            throw new GenericException("Error occurred during phone verification for phone: " + phone + ". Message: " + e.getMessage());
        }
    }
}
