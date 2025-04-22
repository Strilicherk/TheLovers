package com.strilicherk.theloversapi.core.domain.model.role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "roles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleType name;

    private String description;
}
