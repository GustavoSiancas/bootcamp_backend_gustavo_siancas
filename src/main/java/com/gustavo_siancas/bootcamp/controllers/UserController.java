package com.gustavo_siancas.bootcamp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo_siancas.bootcamp.dto.RandomUser;
import com.gustavo_siancas.bootcamp.services.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/random")
    public ResponseEntity<List<RandomUser>> getRandomUsers(@RequestParam(defaultValue = "10") int count) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveRandomUsers(count));
    }
    
}