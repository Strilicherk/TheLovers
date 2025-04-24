package com.strilicherk.theloversapi.core.domain.model.city;

import com.strilicherk.theloversapi.core.domain.model.state.State;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "cities")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class City {
    @Id
    @GeneratedValue
    private int id;

    @NonNull
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", unique = true, nullable = false)
    @NonNull
    private State state;

}
