package com.gustavo_siancas.bootcamp.mapper;

import org.springframework.stereotype.Component;
import com.gustavo_siancas.bootcamp.dto.RandomUser;
import com.gustavo_siancas.bootcamp.entities.Users;

@Component
public class UserMapper {
    
    public Users toEntity(RandomUser randomUser) {
        return Users.builder()
            .name(randomUser.name())
            .gender(randomUser.gender())
            .ubication(randomUser.ubication())
            .birthDate(randomUser.birthDate())
            .email(randomUser.email())
            .avatar(randomUser.avatar())
            .build();
    }

    public RandomUser toDto(Users user) {
        return new RandomUser(
            user.getName(),
            user.getGender(),
            user.getUbication(),
            user.getBirthDate(),
            user.getEmail(),
            user.getAvatar()
        );
    }
}
