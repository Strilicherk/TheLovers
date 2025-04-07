package com.strilicherk.theloversapi.repositories;

import com.strilicherk.theloversapi.domain.state.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
