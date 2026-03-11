package com.fakerapi.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    private String status;
    private int code;
    private String locale;
    private Object seed;
    private int total;
    private List<Person> data;
}
