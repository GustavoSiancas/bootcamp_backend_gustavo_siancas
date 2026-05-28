package com.gustavo_siancas.bootcamp.dto;

import java.time.LocalDate;

import com.gustavo_siancas.bootcamp.entities.value_object.Gender;

public record RandomUser(
    String name,
    Gender gender,
    String ubication,
    LocalDate birthDate,
    String email,
    String avatar
) {}