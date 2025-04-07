package com.strilicherk.theloversapi.repositories;

import com.strilicherk.theloversapi.domain.gender.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
