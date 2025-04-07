package com.strilicherk.theloversapi.domain.city;

import com.strilicherk.theloversapi.domain.state.State;
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

    @Column(unique = true, nullable = false)
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", unique = true, nullable = false)
    @NonNull
    private State state;

}
