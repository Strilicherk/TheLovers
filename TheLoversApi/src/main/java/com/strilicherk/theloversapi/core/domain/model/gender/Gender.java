package com.strilicherk.theloversapi.core.domain.model.gender;

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

    @NonNull
    @Column(unique = true, nullable = false)
    private String name;
}
