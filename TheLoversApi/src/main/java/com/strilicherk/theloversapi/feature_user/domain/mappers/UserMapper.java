package com.strilicherk.theloversapi.feature_user.domain.mappers;

import com.strilicherk.theloversapi.core.domain.model.user_location.UserLocation;
import com.strilicherk.theloversapi.core.domain.model.user_location.UserLocationRequestDTO;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserResponseDTO;
import com.strilicherk.theloversapi.core.domain.model.user.User;

public class UserMapper {

    public static UserResponseDTO fromUserToUserResponseDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getUserVerified(),
                user.getBirthDate(),
                user.getGender() != null ? user.getGender().getId() : null,
                toLocationRequestDTO(user.getLocation()),
                user.getRole().getId()
        );
    }

//    public static User

//    public static User fromUserUpdateDtoToUser(User user, UserUpdateDTO dto) {
//        return new User(
//            if (dto.email() != null) user.setEmail(dto.email());
//            if (dto.phone() != null) user.setPhone(dto.phone());
//        )
//    }
//
//    public static void applyUpdateDTO(User user, UserUpdateDTO dto) {
//        if (dto.name() != null) user.setName(dto.name());
//
//        if (dto.birthDate() != null) user.setBirthDate(dto.birthDate());
//    }

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
                location.getCity()
//                location.getLatitude(),
//                location.getLongitude()
        );
    }
}
