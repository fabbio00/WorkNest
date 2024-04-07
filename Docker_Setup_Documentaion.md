# Docker Setup Documentation

## Overview

This documentation outlines the setup of a Dockerized environment for the `Worknest` application, consisting of three main services: a Vue.js frontend, a Spring Boot backend, and a PostgreSQL database. We use `docker-compose` for defining and running multi-container Docker applications.

## Docker Installation

1. **Install Docker:**
   - For Windows and Mac: Download Docker Desktop from [Docker Hub](https://hub.docker.com/?overlay=onboarding).
   - For Linux: Follow the official [Get Docker](https://docs.docker.com/get-docker/) guide.

2. **Install Docker Compose:**
   - Docker Desktop for Windows and Mac comes with Docker Compose.
   - For Linux, follow the [Install Docker Compose](https://docs.docker.com/compose/install/) guide.

## Project Structure

Ensure your project folder has the following structure:

```
worknest/
|-- worknest-ui/           # Vue.js frontend application directory
|   `-- ...                # Vue.js application files
|-- worknest-service/      # Spring Boot backend application directory
|   `-- ...                # Spring Boot application files
|-- docker-compose.yml     # Docker Compose configuration file
```

## Docker Compose Configuration

The `docker-compose.yml` file defines the services, networks, and volumes for the application:

```yaml
version: '3.8'
services:
  frontend:
    build:
      context: worknest-ui/
    ports:
      - "8000:8000"
    volumes:
      - type: bind
        source: ./worknest-ui
        target: /app
    tty: true
    depends_on:
      - backend

  backend:
    build:
      context: worknest-service/
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/db_worknest
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - db

  db:
    image: postgres:14.11
    environment:
      POSTGRES_DB: db_worknest
      POSTGRES_USER: root
      POSTGRES_PASSWORD: root
    volumes:
      - postgres_data:/var/lib/postgresql/data
    ports:
      - "5432:5432"

volumes:
  postgres_data:
```

## Dockerfiles

### Vue.js Frontend (`worknest-ui/Dockerfile`)

This `Dockerfile` sets up the Vue.js application:

```dockerfile
FROM node:21.7.1 AS build-stage
WORKDIR /app

COPY . .

EXPOSE 8000

CMD ["sh", "-c", "npm install ; npm run dev"]
```

### Spring Boot Backend (`worknest-service/Dockerfile`)

This `Dockerfile` sets up the Spring Boot application:

```dockerfile
FROM amazoncorretto:17

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

CMD apt-get update -y

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]
```

## Running the Application

1. **Start Services:**
   - Open a terminal.
   - Navigate to the root directory containing `docker-compose.yml`.
   - Run the command: `docker-compose up --build -d`. This will build and start all the services defined in the `docker-compose.yml` file.

2. **Stop Services:**
   - To stop all services, press `Ctrl + C` in the terminal where `docker-compose` is running.
   - Optionally, to remove the containers and networks created, run: `docker-compose down`.
