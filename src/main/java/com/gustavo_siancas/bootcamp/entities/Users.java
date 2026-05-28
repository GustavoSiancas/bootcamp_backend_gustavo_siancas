package com.gustavo_siancas.bootcamp.entities;


import java.time.LocalDate;

import com.gustavo_siancas.bootcamp.entities.value_object.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name= "gender", nullable = false)
    private Gender gender;

    @Column(name = "ubication")
    private String ubication;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String avatar;

}
