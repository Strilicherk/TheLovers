package com.strilicherk.theloversapi.feature_user.domain.model;

import com.strilicherk.theloversapi.domain.gender.Gender;
import com.strilicherk.theloversapi.domain.role.Role;
import com.strilicherk.theloversapi.domain.user_location.UserLocation;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserCreateDTO;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "users")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

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

    private Boolean userVerified;

    private LocalDate birthDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "location_id")
    private UserLocation location;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public static User createFromDTO (String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public void updateFromDTO(UserUpdateDTO dto) {
        if (dto.email() != null && !dto.email().isBlank()) {
            this.email = dto.email();
        }
        if (dto.phone() != null && !dto.phone().isBlank()) {
            this.phone = dto.phone();
        }

        this.updatedAt = LocalDateTime.now();
    }
}
