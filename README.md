# Swagger REST API - Java 17 (No Spring Framework)

A pure Swagger/OpenAPI 3.0 REST API project without Spring Framework, using Jersey and Grizzly.

## Features

- ✅ Java 17 (LTS)
- ✅ Jersey REST Framework (JAX-RS)
- ✅ Grizzly HTTP Server
- ✅ Swagger/OpenAPI 3.0 documentation
- ✅ REST API endpoints (CRUD operations)
- ✅ Lombok for reducing boilerplate
- ✅ Apache HTTP Client for testing
- ✅ No Spring Framework - Pure JAX-RS

## Project Structure

```
RestAPI/
├── src/
│   ├── main/java/com/example/
│   │   ├── server/
│   │   │   └── RestAPIServer.java
│   │   ├── resource/
│   │   │   └── UserResource.java
│   │   ├── model/
│   │   │   └── User.java
│   │   └── client/
│   │       └── RestAPIClient.java
├── pom.xml
└── README.md
```

## Building the Project

```bash
mvn clean package
```

## Running the Server

```bash
mvn exec:java -Dexec.mainClass="com.example.server.RestAPIServer"
```

Or:

```bash
mvn compile exec:java
```

The server will start on `http://localhost:8080`

## API Endpoints

### User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| POST | `/api/users` | Create new user |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

## API Documentation (Swagger)

Once the server is running, access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:
```
http://localhost:8080/v3/api-docs
```

## Running the Client

In another terminal:

```bash
mvn exec:java -Dexec.mainClass="com.example.client.RestAPIClient"
```

## Example Requests

**Create User:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","age":30}'
```

**Get All Users:**
```bash
curl http://localhost:8080/api/users
```

**Get User by ID:**
```bash
curl http://localhost:8080/api/users/1
```

**Update User:**
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"John Updated","email":"john.updated@example.com","age":31}'
```

**Delete User:**
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

## Technologies

- **Framework**: Jersey (JAX-RS)
- **Server**: Grizzly HTTP Server
- **Language**: Java 17
- **Build Tool**: Maven 3.8.1+
- **Documentation**: Swagger/OpenAPI 3.0
- **HTTP Client**: Apache HttpClient5
- **Utilities**: Lombok

## Prerequisites

- Java 17 or higher
- Maven 3.8.1 or higher
