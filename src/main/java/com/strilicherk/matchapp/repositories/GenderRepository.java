package com.strilicherk.matchapp.repositories;

import com.strilicherk.matchapp.domain.gender.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
