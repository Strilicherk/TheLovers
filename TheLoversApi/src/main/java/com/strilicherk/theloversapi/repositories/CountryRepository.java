package com.strilicherk.theloversapi.repositories;

import com.strilicherk.theloversapi.core.domain.model.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
