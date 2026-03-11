# FakerAPI Automation - Persons Endpoint

This project contains API automation scripts using the Java programming language and implements the MVC (Model, View, Controller) design pattern. It automates testing the `/api/v2/persons` endpoint from [FakerAPI](https://fakerapi.it/).

## Project Structure (MVC Pattern)

*   **Model (`src/main/java/com/fakerapi/automation/model`)**: Contains POJO classes representing the data structure of the API responses (`Person`, `Address`, `ApiResponse`).
*   **Controller (`src/main/java/com/fakerapi/automation/controller`)**: Contains classes responsible for making the HTTP API requests and returning the mapped response objects (`PersonController`).
*   **View (`src/test/java/com/fakerapi/automation/view`)**: Contains TestNG test classes that execute assertions based on the response data provided by the Controller (`PersonApiTest`).

## Requirements

*   Java Development Kit (JDK) 11 or higher
*   Maven 3.6.0 or higher

## Dependencies Used

*   **Rest-Assured**: For REST API testing and validation.
*   **TestNG**: As the testing framework.
*   **Jackson Databind**: For JSON matching and mapping JSON responses to Java Objects (POJOs).
*   **Lombok**: To reduce boilerplate code in Model classes (Getters, Setters, etc.).

## Handled Test Scenarios

The test invokes the `/api/v2/persons` endpoint with the following query parameters:
*   `_quantity=10`
*   `_gender=male`
*   `_birthday_start=1990-01-01`
*   `_birthday_end=2000-12-31`

**Assertions performed:**
1.  **Total Data Verification**: Verifies that the returned `total` and data array size is exactly `10`.
2.  **Gender Verification**: Verifies that each person returned in the array has the gender `male`.
3.  **Birthday Ranger Verification**: Verifies that each person's birthday is between `1990-01-01` and `2000-12-31`.

## How to Run the Tests

To run the automated tests using Maven, open your terminal at the root of the project directory where `pom.xml` is located, and execute the following command:

```bash
mvn clean test
```

This will download all necessary dependencies, compile the project, and execute the TestNG suites, providing results in your terminal output.

## Testing with cURL

If you want to quickly test the API endpoint without running the Java code, you can use `cURL` from your terminal.

```bash
curl -X GET "https://fakerapi.it/api/v2/persons?_quantity=10&_gender=male&_birthday_start=1990-01-01&_birthday_end=2000-12-31" -H "Accept: application/json"
```

**Example Output:**

```json
{
  "status": "OK",
  "code": 200,
  "locale": "en_US",
  "seed": null,
  "total": 10,
  "data": [
    {
      "id": 1,
      "firstname": "Eduardo",
      "lastname": "Williamson",
      "email": "patricia22@effertz.org",
      "phone": "+19307538271",
      "birthday": "1997-05-03",
      "gender": "male",
      "address": {
        "id": 1,
        "street": "150 Monserrat Plaza",
        "streetName": "Stracke Run",
        "buildingNumber": "8710",
        "city": "Marisabury",
        "zipcode": "69508",
        "country": "Venezuela",
        "country_code": "VE",
        "latitude": 56.318151,
        "longitude": -70.996372
      },
      "website": "http://green.com",
      "image": "http://placeimg.com/640/480/people"
    }
  ]
}
```
*(Note: Output data is truncated for brevity. To format the actual output nicely in your terminal, you can pipe the curl command to `jq`, e.g., `... | jq`)*
