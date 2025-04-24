package com.strilicherk.theloversapi.feature_auth.services.sms;

import com.strilicherk.theloversapi.core.domain.model.user.User;

public interface PhoneVerificationService {
    void verifyCode(String phone, String smsCode);
    void storeSuccessfulVerificationInDatabase(User user);
}
