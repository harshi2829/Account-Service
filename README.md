# Account Service

Manages bank accounts in the Banking System project. Handles creating accounts, fetching account details, and tracking balances.

All endpoints are JWT-protected. You need a valid token from the Auth Service to access them.

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- MySQL 8.0
- Spring Cloud Eureka Client

## Port

8082

## Database

banking_account

## Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | /accounts | Create a new account |
| GET | /accounts/{id} | Get account by ID |
| GET | /accounts | Get all accounts |

## How to Run (with Docker)

This service is part of a larger Docker setup. Clone the main repo and follow the instructions there:
https://github.com/harshi2829/Banking-microservices-docker

## How to Run (standalone)

./mvnw spring-boot:run

Make sure MySQL is running locally and update application.properties with your database credentials.

## Part of

Banking Microservices project: https://github.com/harshi2829/Banking-microservices-docker
