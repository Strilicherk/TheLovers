package com.strilicherk.theloversapi.repositories;

import com.strilicherk.theloversapi.core.domain.model.city.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
