# ContextLogger 
### Multi-User Journaling & Notes Platform

[![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6-brightgreen?style=flat-square&logo=springsecurity)](https://spring.io/projects/spring-security)
[![MongoDB](https://img.shields.io/badge/MongoDB-Database-green?style=flat-square&logo=mongodb)](https://www.mongodb.com)
[![Docker](https://img.shields.io/badge/Docker-Containerized-blue?style=flat-square&logo=docker)](https://www.docker.com)
[![Live Demo](https://img.shields.io/badge/Swagger-Live%20API-85EA2D?style=flat-square&logo=swagger)](https://contextlogger.onrender.com/journal/swagger-ui/index.html)

> A secure, multi-user backend platform for creating, uploading, and organizing personal notes, journals, and context documents — built with Spring Boot, Spring Security 6, JWT, and OAuth2.

---

##  Live Links

| Resource | URL                                                                                                                  |
|----------|----------------------------------------------------------------------------------------------------------------------|
| Swagger UI (Live API Docs) | [contextlogger.onrender.com/journal/swagger-ui/index.html](https://contextlogger.onrender.com/swagger-ui/index.html) |
| GitHub | [github.com/sivanand24/ContextLogger](https://github.com/sivanand24/ContextLogger)                                   |

---

## Overview

ContextLogger is a production-ready journaling and notes platform where users can write, upload, and organize personal documents with full authentication and authorization. It demonstrates a clean, scalable Spring Boot architecture with enterprise-grade security patterns including stateless JWT auth and OAuth2 social login.

---

##  Key Features

- **Multi-user support** — each user's notes and journals are private and isolated
- **JWT authentication** — stateless, token-based auth for all protected endpoints
- **OAuth2 social login** — sign in with Google or GitHub without managing a password
- **Spring Security 6** — fine-grained authorization boundaries across all routes
- **Clean layered architecture** — Controller → Service → Repository pattern for maintainability and testability
- **MongoDB** — flexible document store, ideal for user-generated note content
- **Swagger / OpenAPI** — all REST endpoints are fully documented and interactively testable at the live URL
- **GitHub Actions CI** — automated build and test pipeline on every push, enforcing build stability
- **Dockerized** — containerized for consistent local development and cloud deployment

---

##  Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Security | Spring Security 6, JWT, OAuth2 |
| Database | MongoDB |
| API Docs | Swagger / OpenAPI (Springdoc) |
| CI/CD | GitHub Actions |
| Containerization | Docker |
| Deployment | Render |
| Build Tool | Maven |

---

##  Architecture

```
Client (Web / Mobile)
        │
        ▼
  Spring Boot REST API
  ┌──────────────────────────────────┐
  │  Security Layer                  │
  │  ├── JWT Filter (stateless auth) │
  │  ├── OAuth2 Login (Google/GitHub)│
  │  └── Spring Security 6 Config    │
  │                                  │
  │  Controller Layer                │
  │  ├── AuthController              │
  │  ├── JournalController           │
  │  └── UserController              │
  │                                  │
  │  Service Layer                   │
  │  ├── AuthService                 │
  │  ├── JournalService              │
  │  └── UserService                 │
  │                                  │
  │  Repository Layer                │
  │  └── Spring Data MongoDB         │
  └──────────────┬───────────────────┘
                 │
              MongoDB
```

---

## Running Locally

### Prerequisites
- Java 17+
- Maven 3.8+
- MongoDB (local or Atlas connection string)
- Docker (optional)

### 1. Clone the repo
```bash
git clone https://github.com/sivanand24/ContextLogger.git
cd ContextLogger
```

### 2. Configure environment
Set the following in `application.properties` or as environment variables:
```properties
spring.data.mongodb.uri=your_mongodb_connection_string
jwt.secret=your_jwt_secret_key
spring.security.oauth2.client.registration.google.client-id=your_google_client_id
spring.security.oauth2.client.registration.google.client-secret=your_google_client_secret
```

### 3. Build and run
```bash
# With Maven
mvn clean install
mvn spring-boot:run

# With Docker
docker build -t contextlogger .
docker run -p 8080:8080 --env-file .env contextlogger
```

### 4. Access
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- API base: `http://localhost:8080/api`

---

## 📡 API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Login and receive a JWT |
| `GET`  | `/oauth2/authorization/google` | OAuth2 Google login |

### Journals
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET`  | `/api/journals` | Get all journals for the logged-in user |
| `POST` | `/api/journals` | Create a new journal entry |
| `PUT`  | `/api/journals/{id}` | Update a journal entry |
| `DELETE` | `/api/journals/{id}` | Delete a journal entry |

>  All journal endpoints require a valid JWT in the `Authorization: Bearer <token>` header. Explore the full interactive API at the [live Swagger UI](https://contextlogger.onrender.com/swagger-ui/index.html).

---

## Security Design

```
Request
  │
  ▼
JWT Filter
  ├── Valid token?  → Set SecurityContext → Proceed to Controller
  └── No token?    → Check if public route
                       ├── Public (register/login/oauth2) → Allow
                       └── Protected → 401 Unauthorized
```

OAuth2 flow uses Spring Security's built-in OAuth2 client support — the user is redirected to Google/GitHub, and on callback a JWT is issued and returned to the client.

---

##  CI/CD

GitHub Actions runs on every push to `main`:
1. Checkout code
2. Set up Java 17
3. Run `mvn clean install` (build + tests)
4. Fail the pipeline on any test or build error

This ensures the `main` branch always builds successfully.

---

##  Author

**Sivanand Mishra**
- GitHub: [@sivanand24](https://github.com/sivanand24)
- LinkedIn: [sivanand-mishra](https://www.linkedin.com/in/sivanand-mishra-6aba4123a)
- Email: Sivanandmishra24@gmail.com
