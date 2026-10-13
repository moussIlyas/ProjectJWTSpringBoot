# JwtSpringApplication

A Spring Boot REST API secured with JWT authentication and Spring Security.

## Tech Stack

- Java 21, Spring Boot 4.1.1
- Spring Security, Spring Data JPA
- JWT (JJWT 0.13.0)
- H2 (in-memory), Lombok, Maven

## Structure

```
hiou.hicham.jwtspringapplication/
├── JwtSpringApplication.java   # Entry point
├── dto/                        # AuthResponse, LoginRequest, RegisterRequest
├── entity/AppUser.java         # User entity ("users" table)
├── repository/UserRepository   # JPA repository
└── service/
    ├── JwtService              # Token generation/validation
    └── CustomerUserDetailsService
```

## Run

```bash
./mvnw spring-boot:run
# API:        http://localhost:8080
# H2 console: http://localhost:8080/h2-console
```

## Configuration (`application.properties`)

```properties
spring.datasource.url=jdbc:h2:mem:securitydbdemo
spring.jpa.hibernate.ddl-auto=update
jwt.secret=<base64-secret>
jwt.expiration=3600000
```

## Status

Implemented: entity, DTOs, repository, JWT service, user details service.

To do: `AuthController` (`/register`, `/login`), `UserController`,
`SecurityConfiguration`, `JwtAuthenticationFilter`, password hashing (BCrypt),
DTO validation, exception handling, and tests.

## Notes

- Store the JWT secret in env vars, never in version control.
- Passwords must be hashed with BCrypt in production.

## Author

**Mouss Ilyas**
