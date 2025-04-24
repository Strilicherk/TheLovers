package com.strilicherk.theloversapi.repositories;

import com.strilicherk.theloversapi.core.domain.model.user_location.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserLocationRepository extends JpaRepository<UserLocation, UUID> {
}
