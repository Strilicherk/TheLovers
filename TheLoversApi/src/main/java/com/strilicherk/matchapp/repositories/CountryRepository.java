package com.strilicherk.matchapp.repositories;

import com.strilicherk.matchapp.domain.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
