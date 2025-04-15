package com.strilicherk.theloversapi.core.domain.mappers;

import com.strilicherk.theloversapi.domain.user_location.UserLocation;
import com.strilicherk.theloversapi.domain.user_location.UserLocationRequestDTO;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserCreateDTO;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserResponseDTO;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserUpdateDTO;
import com.strilicherk.theloversapi.feature_user.domain.model.User;

public class UserMapper {

    public static UserResponseDTO fromUserToUserResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getUserVerified(),
                user.getBirthDate(),
                toLocationRequestDTO(user.getLocation()),
                user.getGender()
        );
    }

//    public static User fromCreateDTO(UserCreateDTO dto) {
//        User user = new User();
//        user.setPhone(dto.phone());
//        user.setUserVerified(false);
//        return user;
//    }
//
//    public static void applyUpdateDTO(User user, UserUpdateDTO dto) {
//        if (dto.name() != null) user.setName(dto.name());
//        if (dto.email() != null) user.setEmail(dto.email());
//        if (dto.phone() != null) user.setPhone(dto.phone());
//        if (dto.birthDate() != null) user.setBirthDate(dto.birthDate());
//        // genderId e locationId exigem fetch da entidade, feito no service
//    }
//
    public static UserLocationRequestDTO toLocationRequestDTO(UserLocation location) {
        if (location == null) return null;
        return new UserLocationRequestDTO(
                location.getUser().getId(),
                location.getId(),
                location.getCountry(),
                location.getState(),
                location.getCity(),
                location.getLatitude(),
                location.getLongitude()
        );
    }
}
