package com.strilicherk.theloversapi.domain.state;

import com.strilicherk.theloversapi.domain.country.Country;
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

    @Column(unique = true, nullable = false)
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", unique = true, nullable = false)
    @NonNull
    private Country country;
}
