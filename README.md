# MultiMarketHub Technical Documentation

## Overview

MultiMarketHub makes selling easy! Manage your products effortlessly across many online stores, ensuring a smooth shopping experience for both sellers and buyers with inventory control and customized features. This README provides an overview of the platform's architecture, deployment, development setup, and key technologies used.

## Microservices Architecture

MultiMarketHub leverages a microservices architecture for scalability and flexibility. The core microservices include:

- **User Service:** Manages authentication and user profiles.
- **Store Service:** Facilitates store creation and management.
- **Product Service:** Enables product addition, updates, and retrieval.
- **Order Service:** Handles the end-to-end order processing workflow.
- **Payment Service:** Ensures secure and seamless transactions.

## Infrastructure

### Spring Cloud Config Server

Centralized configuration management for easy updates across services.

### Netflix Eureka Naming Server

Efficient service discovery for seamless communication between microservices.

### API Gateway

Central entry point for external clients, routing requests to the appropriate microservices.

### Zipkin Distributed Tracing Server

Facilitates tracing and monitoring of requests across microservices.

## Database: PostgreSQL

MultiMarketHub relies on PostgreSQL for its robust and scalable database management.

## Deployment

MultiMarketHub can be deployed in various environments, and the following technologies support its deployment:

- **Containerization:** Docker for containerization, ensuring consistency across different environments.

- **Orchestration:** Kubernetes for orchestrating and managing containers at scale.


## Admins API's (USER-SERVICE)
- **GET /admins** - retrieves a list of all users  
- **GET /admins/{id}** - retrieves a specific users by ID  
- **POST /admins** - creates a new users (admin) 
- **PUT /admins/{id}** - updates an existing users by ID 
- **DELETE /admins/{id}** - deletes a users by ID 


## Stores API's (STORE-SERVICE)
- **GET /stores**  - retrieves a list of all stores 
- **GET /stores/{Id}**  - retrieves a specific stores by ID 
- **POST /stores**  - creates a new stores 
- **PUT /stores/{Id}**  - updates an existing stores by ID
- **DELETE /stores/{Id}**  - deletes a stores by ID


## Products API's (PRODUCT-SERVICE)
- **GET /units** - get all the units
- **POST /products** - creates a new products
- **GET /products**  - retrieves a list of all products
- **GET /products/{id}** - retrieves a specific products by ID
- **PUT /products/{id}** - updates an existing products by ID
- **DELETE /products/{id}** - deletes a products by ID
- **GET /stores/{storeId}/categories** - retrieves list of all categories
- **GET /stores/{storeId}/categories/{CategoryId}/products** - retrieves products by categories and store Id


## Customers API's (USER-SERVICE)
- **GET /customers** - retrieves a list of all users
- **GET /customers/{id}** - retrieves a specific users by ID
- **POST /customers** - creates a new users (admin)
- **POST /customers?storeId=?** - creates a new users (customer) based on store id
- **PUT /customers/{id}** - updates an existing users by ID
- **DELETE /customers/{id}** - deletes a users by ID
