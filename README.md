# E-commerce Website

Welcome to our E-commerce website project! This project serves as a learning opportunity to build a full-stack web application using Spring Boot. Our goal is to implement various features including security mechanisms like JWT, authentication, encryption, decryption, and more.

## Backend Overview

The backend of our E-commerce website consists of several key components. Here's a brief overview:

### Repositories

In the backend, we utilize repository classes for interacting with the database. Currently, these classes inherit from a `DatabaseHelper` and execute SQL queries. However, we plan to transition to using DAOs (Data Access Objects) in the future for better organization and maintainability.

### Database Configuration

To observe the effects of running SQL queries, it's essential to configure your database credentials appropriately. By default, the credentials are set to:

- **Username:** root
- **Password:** 1234

You can modify these credentials in the `DatabaseHelper` class to match your database setup.
