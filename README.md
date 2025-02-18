# ShopKaro : Scalable E-Commerce Backend

## Overview
This is a **Scalable E-Commerce Backend** built using a **microservices architecture** with Spring Boot. It is designed to handle high traffic, scale efficiently, and provide seamless user experience.

## Features
- **Product Service**: Manages product catalog, categories, and inventory.
- **Order Service**: Handles order placement, tracking, and history.
- **Payment Service**: Secure payment processing and transaction handling.
- **User Service**: Manages authentication, user profiles, and roles.
- **API Gateway**: Centralized entry point for all microservices.
- **Config Server**: Manages distributed configurations.

## Tech Stack
- **Backend**: Java, Spring Boot (Microservices, Spring Cloud, Spring Security)
- **Databases**: MongoDB (Product Service), PostgreSQL (Order & User Service)
- **Messaging**: Kafka (Event-Driven Communication)
- **Caching**: Redis
- **Search**: ElasticSearch (For product search, will be implemented later)
- **API Gateway**: Spring Cloud Gateway
- **Orchestration**: Kubernetes, Docker

## Microservices Breakdown
1. **Product Service**
   - CRUD operations for products
   - Category & Brand filtering
   - Pricing & Inventory management

2. **Order Service**
   - Order placement & tracking
   - Order history
   - Payment integration

3. **Payment Service**
   - Payment processing
   - Secure transactions
   - Refund handling

4. **User Service**
   - User authentication (JWT + OAuth2)
   - Role-based access control (RBAC)
   - Profile management

5. **API Gateway**
   - Handles authentication & authorization
   - Routes requests to respective services
   - Rate limiting & security

6. **Config Server**
   - Centralized configuration management
   - Dynamic updates for microservices

## Project Structure
```
/shopkaro
│── /product-service  (Spring Boot Project for Product Service)
│── /order-service  (Spring Boot Project for Order Service)
│── /payment-service  (Spring Boot Project for Payment Service)
│── /user-service  (Spring Boot Project for User Service)
│── /api-gateway  (Spring Boot Project for API Gateway)
│── /config-server  (Spring Cloud Config for Centralized Config)
│── docker-compose.yml (Local setup with Docker)
│── README.md
```

## Prerequisites
Before running this project, ensure you have the following installed and configured:

- **Java 17+** (Ensure JDK is installed and configured in your environment)
- **Maven** (For building and running Spring Boot applications)
- **Docker & Docker Compose** (For containerized deployment)
- **MongoDB** (For Product Service - running locally or using Docker)
- **PostgreSQL** (For Order and User Services - running locally or using Docker)
- **Redis** (For caching - running locally or using Docker)
- **Kafka** (For event-driven communication - running locally or using Docker)

### Environment Variables & Configuration
Each microservice requires specific environment variables:
- **MongoDB Connection**: `MONGO_URI=mongodb://localhost:27017/ecommerce`
- **PostgreSQL Connection**: `DATABASE_URL=jdbc:postgresql://localhost:5432/ecommerce`
- **Redis Connection**: `REDIS_HOST=localhost` and `REDIS_PORT=6379`
- **Kafka Connection**: `KAFKA_BROKER=localhost:9092`

## Setup & Installation
1. **Clone the Repository**
   ```sh
   git clone https://github.com/ervishalpathak7/shopkaro.git
   cd shopkaro
   ```
2. **Run Services** (using Docker-Compose)
   ```sh
   docker-compose up -d
   ```
3. **Run Individual Services Locally**
   - Start each Spring Boot service separately using:
     ```sh
     mvn spring-boot:run
     ```

## Future Enhancements
- Implement **ElasticSearch** for advanced product search.
- Add **Prometheus & Grafana** for monitoring.
- Implement **Circuit Breaker** with Resilience4j.
- Migrate API Gateway to **Kong** for better scalability.

## Contributing
Feel free to open issues and contribute to this project!

## License
MIT License

