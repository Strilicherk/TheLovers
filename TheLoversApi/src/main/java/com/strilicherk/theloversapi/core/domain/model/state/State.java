package com.strilicherk.theloversapi.core.domain.model.state;

import com.strilicherk.theloversapi.core.domain.model.country.Country;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "states")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class State {
    @Id
    @GeneratedValue
    private int id;


    @NonNull
    @Column(unique = true, nullable = false)
    private String name;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "country_id", unique = true, nullable = false)
    private Country country;
}
