# Student System Application

Application based on Spring Boot and JPA.

## Requirements
- JDK 19
- Gradle 8.5
- Spring Boot 3.2.2

## Technologies
- JPA
- H2Database
- Lombok
- Thymeleaf

## Endpoints
Test requests are placed in `testRequests.http` file.
Implemented endpoints:
```http request
### Add student
POST http://localhost:8080/students/add/{name}/{surename}
### Remove student
DELETE http://localhost:8080/students/delete/{studentId}
### Students HTML page
GET http://localhost:8080/students/

### Add course
POST http://localhost:8080/courses/add/{name}
### Remove kursu o ID = 1
DELETE http://localhost:8080/courses/delete/{courseId}
### Courses HTML page
GET http://localhost:8080/courses

### Add grade
POST http://localhost:8080/courses/grades/add/{studentId}/{courseId}/{maxPoints}
### Update grade
POST http://localhost:8080/courses/grades/update/{studentId}/{courseId}/{points}
### Average grades HTML page
GET http://localhost:8080/courses/average
```