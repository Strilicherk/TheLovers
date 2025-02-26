package com.strilicherk.matchapp.domain.gender;

import jakarta.persistence.*;
import lombok.*;

@Table (name = "genders")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Gender {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    @NonNull
    private String gender;
}
