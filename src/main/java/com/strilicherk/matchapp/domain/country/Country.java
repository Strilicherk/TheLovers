package com.strilicherk.matchapp.domain.country;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "countries")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    @NonNull
    private String name;

    @Column(unique = true, nullable = false)
    @NonNull
    private String flagUrl;
}
