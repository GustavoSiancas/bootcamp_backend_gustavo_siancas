package com.gustavo_siancas.bootcamp.services;

import org.springframework.stereotype.Service;

import com.gustavo_siancas.bootcamp.repositories.UserRepository;
import com.gustavo_siancas.bootcamp.dto.RandomUser;
import com.gustavo_siancas.bootcamp.entities.Users;
import com.gustavo_siancas.bootcamp.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RandomUserService randomUserService;
    private final UserMapper userMapper;

    private List<Users> getRandomUsers(int count) throws Exception {
        List<RandomUser> randomUsers = randomUserService.fetchUsers(count);
        List<Users> users = randomUsers.stream()
            .map(randomUser -> userMapper.toEntity(randomUser))
            .toList();
        
        userRepository.saveAll(users);
        return users;
    }

    public List<RandomUser> saveRandomUsers(int count) throws Exception {
        List<Users> candidates = getRandomUsers(count);
        List<Users> toSave = new ArrayList<>();
        List<Users> existing = new ArrayList<>();

        for (Users user : candidates) {
            userRepository.findByEmail(user.getEmail())
                    .ifPresentOrElse(
                            existing::add,          
                            () -> toSave.add(user)  
                    );
        }

        userRepository.saveAll(toSave);

        List<Users> result = new ArrayList<>();
        result.addAll(existing);
        result.addAll(toSave);

        return result.stream().map(userMapper::toDto).toList();
    }
    
}
