package com.strilicherk.theloversapi.core.domain.model.country;

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

    @NonNull
    @Column(unique = true, nullable = false)
    private String name;

    @NonNull
    @Column(unique = true, nullable = false)
    private String flag;
}
