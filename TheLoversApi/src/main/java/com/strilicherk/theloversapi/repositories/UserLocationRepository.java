package com.strilicherk.theloversapi.repositories;

import com.strilicherk.theloversapi.domain.user_location.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserLocationRepository extends JpaRepository<UserLocation, UUID> {
}
