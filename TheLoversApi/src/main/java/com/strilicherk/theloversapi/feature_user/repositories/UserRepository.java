package com.strilicherk.theloversapi.feature_user.repositories;

import com.strilicherk.theloversapi.feature_user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhone(String phone);
}