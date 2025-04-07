package com.strilicherk.matchapp.repositories;

import com.strilicherk.matchapp.domain.city.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
