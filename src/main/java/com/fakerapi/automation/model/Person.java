package com.fakerapi.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String birthday;
    private String gender;
    private Address address;
    private String website;
    private String image;
}
