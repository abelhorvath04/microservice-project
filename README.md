# Microservice Application

## Overview of the university project

This group-project is a microservice-based application developed to interact with Raspberry Pi sensors, process measurements, and present data on a web and mobile platform. The system architecture consists of several components working together, including a Spring Boot backend, an Angular frontend, a NativeScript mobile app, and communication with a Raspberry Pi device.

## Architecture

### Backend (Spring Boot)
- **Client**: A Spring Boot application that registers itself with Eureka and communicates with the rest of the system. It stores sensor data and measurements in a PostgreSQL database hosted on AWS via Aiven.
- **Eureka Server**: Manages service discovery for the microservices in the system.
- **Config Server**: Provides configuration management for the services, delivering necessary parameters to registered clients.
- **API Gateway**: Serves as a unified entry point to all backend services, integrating communication with the frontend, mobile app, and Raspberry Pi via HiveMQ.

### Database
- PostgreSQL database hosted on AWS through Aiven.
- The client application handles sensor and measurement data, which is received from the Raspberry Pi and stored in the database via DTOs (Data Transfer Objects).
- CRUD operations are implemented via REST APIs.

### Frontend (Angular)
- Displays sensor and measurement data in charts using Bootstrap and Angular Material.
- Provides basic CRUD functionality with a user-friendly interface.

### Mobile App (NativeScript)
- Mirrors the Angular frontend, displaying charts with sensor data.
- The mobile app supports only READ operations, providing a similar user experience as the frontend.

### Raspberry Pi Communication
- A Python-based message broker was developed to enable communication between the Raspberry Pi and the Spring Boot backend.
- **HiveMQ** was used for message brokering, allowing data transmission from the Raspberry Pi to the backend.
- The Raspberry Pi reads data from a **DHT-11** sensor (humidity and temperature) and displays it on an LCD screen. The data is transmitted to the backend via HiveMQ.

## Technologies Used
- **Spring Boot** (Backend): Client, Eureka, Config Server, API Gateway
- **Angular** (Frontend): Data visualization and CRUD functionality
- **NativeScript** (Mobile App): Displaying sensor data in charts
- **Raspberry Pi**: DHT-11 sensor for temperature and humidity, Python-based message broker, HiveMQ for communication
- **Database**: PostgreSQL on AWS via Aiven
- **Message Broker**: HiveMQ for communication between Raspberry Pi and Spring Boot backend

## Summary
This project demonstrates the integration of microservices, real-time data collection, and visualization using modern technologies such as Spring Boot, Angular, NativeScript, and Raspberry Pi. The use of message brokers and cloud services allows for efficient and scalable communication between components.
