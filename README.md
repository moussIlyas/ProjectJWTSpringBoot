# JwtSpringApplication

A Spring Boot REST API secured with **JWT authentication** and **Spring Security**.
Users register, log in, and receive a signed token used to access protected endpoints.

> Author: **Mouss Ilyas**

## Tech Stack

- Java 21, Spring Boot 4.1.1
- Spring Security, Spring Data JPA
- JWT (JJWT 0.13.0)
- H2 (in-memory), Lombok, Maven

## Structure

```
com.projectJwtspringapplication/
├── JwtSpringApplication.java        # Entry point
├── config/
│   └── SecurityConfig.java          # Security rules, filter chain, encoder
├── controller/
│   ├── AuthController.java          # /api/auth/register, /api/auth/login
│   └── UserController.java          # /api/hello, /api/profile (protected)
├── dto/                             # AuthResponse, LoginRequest, RegisterRequest
├── entity/AppUser.java              # User entity ("users" table)
├── repository/UserRepository.java   # JPA repository (findByEmail, existsByEmail)
├── security/
│   └── JwtFilter.java               # Validates the Bearer token per request
└── service/
    ├── JwtService.java              # Token generation / validation
    └── CustomUserDetailsService.java
```

## Endpoints

| Method | Path                 | Access        | Description                          |
|--------|----------------------|---------------|--------------------------------------|
| POST   | `/api/auth/register` | Public        | Create a user, returns a JWT         |
| POST   | `/api/auth/login`    | Public        | Authenticate, returns a JWT          |
| GET    | `/api/hello`         | Authenticated | Protected greeting                   |
| GET    | `/api/profile`       | Authenticated | Returns the authenticated user       |

Send the token on protected calls:

```
Authorization: Bearer <token>
```

## Configuration (`application.properties`)

```properties
spring.datasource.url=jdbc:h2:mem:securitydbdemo
spring.jpa.hibernate.ddl-auto=update
jwt.secret=<base64-secret>
jwt.expiration=3600000
```

## Run

```bash
./mvnw spring-boot:run
# API:        http://localhost:8080
# H2 console: http://localhost:8080/h2-console
```

## API Tests

All requests are defined in [`requests.http`](requests.http) (open it in IntelliJ IDEA and
click the run arrow). The screenshots below show the responses.

### Register a new user → `201 Created`

Create a user with `POST /api/auth/register`; the response contains the JWT.

### Register duplicate → `409 Conflict`

Registering the same email again is rejected (author: **Mouss Ilyas**).

<p align="center">
  <img src="images/registerDuplicate.png" alt="Register duplicate returns 409 Conflict" width="820">
</p>

### Login → `200 OK`

<p align="center">
  <img src="images/postOnLogin.png" alt="Login returns 200 with a JWT token" width="720">
</p>

### Hello with token → `200 OK`

<p align="center">
  <img src="images/helloWithToken.png" alt="GET /api/hello with a valid token returns 200" width="820">
</p>

### Profile with token → `200 OK`

<p align="center">
  <img src="images/profileWithToken.png" alt="GET /api/profile with a valid token returns the authenticated user" width="820">
</p>

## Author

**Mouss Ilyas**

## Notes

- Store the JWT secret in environment variables, never in version control.
- Passwords are hashed with BCrypt before being persisted.
