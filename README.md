# Fusion Plex - Catalogue Management

Fusion Plex is a comprehensive catalogue management system designed to provide a foundation for managing movie, TV series, and documentary catalogues. This project serves as a base for educational purposes, allowing others to fork and utilize it as a starting point for learning about web application development with a focus on catalogue management.

## Project Overview

The Fusion Plex system is intended to demonstrate key aspects of web application development, including but not limited to, database management, CRUD operations, and user interface design. It's built with a modern technology stack that can be adapted for teaching various software development concepts.

## Tutorial Overview

In this section, we will enhance Fusion Plex, a catalogue management system, by integrating application audit capabilities. Our goal is to track and maintain a comprehensive audit trail for all entities within our system. This ensures transparency and accountability for actions performed on the data by tracking who created or updated a record, when it was done, and the IP address from which the modification was made.

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Before you begin, ensure you have the following installed:
- JDK 21 or later
- Maven 3.9.6 or later
- Docker

Additionally, familiarity with Spring Boot and PostgreSQL is recommended to fully understand the project setup and functionality.

### Technology Stack for this Tutorial

- Spring Security
- Javers

### Default Login to Application

1. **Regular User**
    - username: `user`
    - password: `User@1234`

2. **Admin User**
    - username: `admin`
    - password: `Admin@1234`

### Initial Setup

To obtain the initial project setup, use the base branch of this repository:

```bash
git clone -b base git@github.com:softwarebuilding-io/application-auditing.git

cd application-auditing
```

### Base Project
This branch contains the base project structure and configurations necessary to start developing your application.

```bash
git checkout base
```

### Final Project
For those interested in viewing the completed project with all tutorials features implemented, switch to the final branch:

```bash
git checkout final
```

### Database Setup
Fusion Plex uses PostgreSQL for data persistence. To create and run a PostgreSQL database using Docker, execute the following Maven command:

```bash
mvn clean compile -Pdocker
```
This command cleans the project, compiles the source code, and runs a Docker profile defined in the pom.xml that sets up a PostgreSQL container suitable for development.

### Database Connection

- hostname: localhost
- database: postgres
- username: admin
- password: admin@1234


### Running the Application
After setting up the database, you can start the application by running:

```bash
mvn spring-boot:run
```
This command will start the Spring Boot application on the default port (usually 8080). Access the application by navigating to http://localhost:8080/fusion-plex in your web browser.