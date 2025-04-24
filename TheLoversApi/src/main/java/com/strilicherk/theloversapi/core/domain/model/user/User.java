package com.strilicherk.theloversapi.core.domain.model.user;

import com.strilicherk.theloversapi.core.domain.model.gender.Gender;
import com.strilicherk.theloversapi.core.domain.model.role.Role;
import com.strilicherk.theloversapi.core.domain.model.role.RoleType;
import com.strilicherk.theloversapi.core.domain.model.user_location.UserLocation;
import jakarta.persistence.*;
import lombok.*;
import org.flywaydb.core.internal.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "users")
@Entity
@Setter
@Getter
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    @NonNull
    private String phone;

    @Column(name = "user_verified")
    private Boolean userVerified;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NonNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NonNull
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "location_id")
    private UserLocation location;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.role = RoleType.toEntity(RoleType.COMMON);
    }

    public void update(String newEmail, String newPhone, Boolean userVerified) {
        if (StringUtils.hasText(newEmail)) {
            this.email = newEmail;
        }
        if (StringUtils.hasText(newPhone)) {
            this.phone = newPhone;
        }
        if (userVerified != null) {
            this.userVerified = userVerified;
        }

        this.updatedAt = LocalDateTime.now();
    }
}
