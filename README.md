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

## Configuration
Only available configuration is the grades distribution what is loading from file `resources/grades.yaml` as map.
```yaml
0.9: 5.0
0.8: 4.5
0.7: 4.0
0.6: 3.5
0.5: 3.0
0.0: 2.0
```

## Endpoints
Test requests are placed in `resources/testRequests.http` file.
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
### Remove course
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