package com.strilicherk.theloversapi.repositories;

import com.strilicherk.theloversapi.core.domain.model.state.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
