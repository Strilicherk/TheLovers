package com.strilicherk.theloversapi.feature_auth.services.sms;

import com.strilicherk.theloversapi.core.exceptions.GenericException;
import com.strilicherk.theloversapi.core.exceptions.VerificationException;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendSmsCodeServiceImpl implements SendSmsCodeService {
    @Value("${twilio.serviceSid}")
    private String serviceSid;

    static {
        String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
        String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public Boolean sendSmsCode(String phone) {
        try {
            Verification verification = Verification.creator(serviceSid, phone, "sms").create();
            log.info("Created verification: {}", verification);

            if ("pending".equals(verification.getStatus())) {
                log.info("Verification is pending: {}", verification.getStatus());
                return true;
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
