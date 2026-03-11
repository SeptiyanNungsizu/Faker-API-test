package com.fakerapi.automation.view;

import com.fakerapi.automation.controller.PersonController;
import com.fakerapi.automation.model.ApiResponse;
import com.fakerapi.automation.model.Person;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class PersonApiTest {

    private PersonController personController;
    private ApiResponse apiResponse;
    private final LocalDate startDate = LocalDate.parse("1990-01-01");
    private final LocalDate endDate = LocalDate.parse("2000-12-31");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @BeforeClass
    public void setupAndFetchData() {
        personController = new PersonController();
        
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("_quantity", 10);
        queryParams.put("_gender", "male");
        queryParams.put("_birthday_start", "1990-01-01");
        queryParams.put("_birthday_end", "2000-12-31");

        apiResponse = personController.getPersons(queryParams);
        Assert.assertNotNull(apiResponse, "API Response should not be null");
    }

    @Test
    public void verifyTotalDataAmount() {
        Assert.assertEquals(apiResponse.getTotal(), 10, "Total count should be 10");
        Assert.assertEquals(apiResponse.getData().size(), 10, "Data list size should be 10");
    }

    @Test
    public void verifyGenderOfAllPersons() {
        for (Person person : apiResponse.getData()) {
            Assert.assertEquals(person.getGender(), "male", "Gender of person " + person.getFirstname() + " is not 'male'");
        }
    }

    @Test
    public void verifyBirthdayIsWithinRange() {
        for (Person person : apiResponse.getData()) {
            LocalDate birthday = LocalDate.parse(person.getBirthday(), formatter);
            
            boolean isAfterOrEqualStart = !birthday.isBefore(startDate);
            boolean isBeforeOrEqualEnd = !birthday.isAfter(endDate);
            
            Assert.assertTrue(isAfterOrEqualStart && isBeforeOrEqualEnd,
                    "Birthday " + person.getBirthday() + " for person " + person.getFirstname() +
                    " is not between " + startDate + " and " + endDate);
        }
    }
}
