package com.gustavo_siancas.bootcamp.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gustavo_siancas.bootcamp.dto.RandomUser;
import com.gustavo_siancas.bootcamp.entities.value_object.Gender;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class RandomUserService {
    
    @Value("${randomuser.api.url}")
    private String apiUsersUrl;

    public List<RandomUser> fetchUsers(int count) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        
        String response = restTemplate.getForObject(getApiUrlwithCount(count), String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode results = root.get("results");

        List<RandomUser> users = new ArrayList<>();

        for (JsonNode node : results) {
            String fullName  = node.get("name").get("first").stringValue()
                             + " " + node.get("name").get("last").stringValue();

            Gender gender = node.get("gender").stringValue().equals("female")
                          ? Gender.female : Gender.male;

            String ubication = node.get("location").get("city").stringValue()
                             + ", " + node.get("location").get("country").stringValue();

            LocalDate birthDate = LocalDate.parse(
                node.get("dob").get("date").stringValue().substring(0, 10)
            );

            RandomUser user = new RandomUser(
                fullName,
                gender,
                ubication,
                birthDate,
                node.get("email").stringValue(),
                node.get("picture").get("large").stringValue()
            );

            users.add(user);
        }

        return users;
    }

    private String getApiUrlwithCount(int count) {
        return apiUsersUrl + "?results=" + count;
    }
}
