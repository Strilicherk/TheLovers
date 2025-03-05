package com.strilicherk.matchapp.repositories;

import com.strilicherk.matchapp.domain.state.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
