# Expense Tracker REST API

A production-ready REST API built with Java and Spring Boot for tracking personal expenses. Features JWT authentication, PostgreSQL database, and full CRUD operations.

## Live Demo

Base URL: `https://expense-tracker-lmtz.onrender.com`

| Endpoint | Description |
|----------|-------------|
| `POST /api/auth/register` | Create account |
| `POST /api/auth/login` | Get JWT token |
| `GET /api/expenses` | List expenses (auth required) |
| `GET /api/expenses/summary` | Spending summary (auth required) |

## Tech Stack

- **Java 21** + **Spring Boot 3.5**
- **Spring Security** + **JWT** (authentication)
- **Spring Data JPA** + **Hibernate** (ORM)
- **PostgreSQL** (production / Render) / **H2** (development)
- **Lombok** + **Bean Validation**
- **Maven**

## Project Structure

```
src/main/java/com/expensetracker/
├── config/          # App config, DataLoader
├── controller/      # REST controllers
├── dto/             # Request/Response objects
├── exception/       # Global error handling
├── model/           # JPA entities (Expense, User)
├── repository/      # Database queries
├── security/        # JWT filter, Security config
└── service/         # Business logic
```

## API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Create account |
| POST | `/api/auth/login` | Login, returns JWT token |

### Expenses (JWT required)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/expenses` | Get all expenses |
| GET | `/api/expenses?category=Food` | Filter by category |
| GET | `/api/expenses?startDate=2026-05-01&endDate=2026-05-31` | Filter by date range |
| GET | `/api/expenses/summary` | Total spent per category |
| GET | `/api/expenses/{id}` | Get one expense |
| POST | `/api/expenses` | Create expense |
| PUT | `/api/expenses/{id}` | Update expense |
| DELETE | `/api/expenses/{id}` | Delete expense |

## Getting Started

### Prerequisites
- Java 21+
- Maven (or use the included `mvnw`)
- MySQL 8.0 (for production profile)

### Run in Development (H2 in-memory DB)

```bash
git clone https://github.com/arghya0003/expense-tracker.git
cd expense-tracker
./mvnw spring-boot:run
```

App runs on `http://localhost:8080`
H2 Console available at `http://localhost:8080/h2-console`

### Run with MySQL (Production)

1. Create the database:
```sql
CREATE DATABASE expensedb;
```

2. Create `src/main/resources/application-prod.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expensedb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
spring.h2.console.enabled=false
server.port=8080
```

3. Switch profile in `application.properties`:
```properties
spring.profiles.active=prod
```

4. Run:
```bash
./mvnw spring-boot:run
```

## Usage Example

**Register:**
```bash
curl -X POST https://expense-tracker-lmtz.onrender.com/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"john","email":"john@example.com","password":"secret123"}'
```

**Login:**
```bash
curl -X POST https://expense-tracker-lmtz.onrender.com/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"secret123"}'
```

**Create Expense (use token from login):**
```bash
curl -X POST https://expense-tracker-lmtz.onrender.com/api/expenses \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"title":"Coffee","amount":120,"category":"Food","date":"2026-05-18"}'
```

**Get Summary:**
```bash
curl https://expense-tracker-lmtz.onrender.com/api/expenses/summary \
  -H "Authorization: Bearer YOUR_TOKEN"
```

## Features

- JWT-based stateless authentication
- Request validation with descriptive error messages
- Global exception handling with consistent error format
- Filter expenses by category, date range, or both
- Spending summary grouped by category
- Spring Profiles for dev/prod environments
