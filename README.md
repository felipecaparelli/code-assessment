# CODE ASSESSMENT - WEATHER FORECAST

This application provides a web service to check the today's highest temperature in Celsius for a specific city.
To check the weather in a specific location it is required to use the WCO code for this location and the grid position (X, Y)

For example: 
- TAE/35,70
- LWX/99,76
- PBZ/106,76

## Installing the dependencies
Run the command `mvn clean install` to properly add all the required dependencies.

## Running the app
This is a SpringBoot application, so to properly execute it you just need to run the following command
`mvn spring-boot:run`

The application will start in the port 8080, and to access the web service you should call it with the following URL
[http://localhost:8080/weather/daily-forecast/TAE/35,70](http://localhost:8080/weather/daily-forecast/TAE/35,70)

## Technologies

- Java 17
- Kotlin
- Maven
- Spring Boot
