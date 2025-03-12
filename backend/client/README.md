# HA1 Microservice - Group 9

A Spring Boot microservice designed to persist and provide access to sensor data as part of a distributed system for monitoring environmental conditions.

## Project Overview

This microservice supports storing and retrieving temperature and humidity measurements from multiple sensors. It provides a RESTful API and is built using a microservices architecture with Spring Cloud components, including:

- **API Gateway**
- **Load Balancer**
- **Service Registry**
- **Config Server**

Each sensor will logging measurements with timestamped temperature and/or humidity values, with data persisted in a PostgreSQL database.

## Features

- **Sensor Data Management**: Create, read, update, and delete operations for sensor data.
- **Resilience and Scalability**: At least two service instances running with load balancing.
- **API Documentation**: Integrated with Swagger UI for easy API exploration.

## Technology Stack

- **Java 21**
- **Spring Boot 3.3.5**
- **Spring Cloud 2023.0.3**
- **PostgreSQL** for data persistence
- **Resilience4j** for circuit breaking

## Setup

1. Clone the repository.
2. Connect to the configuration server.
3. Build and run the service instances.
4. Access the API documentation via Swagger UI at `/swagger-ui.html`.

## Submission

- Test cases
- Documentation
- Source code
