# Project Setup Documentation

## IDE Configuration - IntelliJ IDEA

1. **Install IntelliJ IDEA:**
   - Download and install IntelliJ IDEA (Community or Ultimate Edition) from [JetBrains' official website](https://www.jetbrains.com/idea/download/).
   - Launch IntelliJ IDEA and configure your preferences.

2. **Install Lombok Plugin:**
   - Navigate to `File` > `Settings` > `Plugins`.
   - Search for `Lombok Plugin` and click on `Install`.

3. **Enable Annotation Processors:**
   - Navigate to `File` > `Settings` > `Build, Execution, Deployment` > `Compiler` > `Annotation Processors`.
   - Check `Enable annotation processing`.

4. **Import the Project:**
   - Select `Open or Import` from the IntelliJ IDEA welcome screen.
   - Navigate to the directory of your project and select the `pom.xml` file.
   - Click `Open` as a project.

## Project Structure

Your project should follow the standard Maven structure:

```
src/
|-- main/
|   |-- java/
|   |   |-- com.ams.yourProjectName/
|   |   |   |-- configurations/
|   |   |   |   `-- file.java
|   |   |   |-- controllers/
|   |   |   |   `-- file.java
|   |   |   |-- model/
|   |   |   |   |-- dto/
|   |   |   |   |   `-- file.java
|   |   |   |   `-- entities/
|   |   |   |       `-- file.java
|   |   |   |-- resources/
|   |   |   |   `-- file.java
|   |   |   |-- repositories/
|   |   |   |-- services/
|   |   |   |   |-- impl/
|   |   |   |   |   `-- file.java
|   |   |   |   `-- file.java
|   |   |   `-- YourChoosenNameApplication.java
|   |   `-- resources/
|   |       |-- db.migrations/
|   |       |-- application.yml
|   |       |-- application-local.yml
|   |       |-- application-test.yml
|-- test/
    `-- java/
        `-- com.ams.yourProjectName/
            `-- BaseMvcTest.java
            `-- controllers/
                `-- file.java
```

- Ensure that all your Java code resides inside `src/main/java`.
- Place your application resources, including application properties and Flyway migrations, inside `src/main/resources`.
- Tests should be in the `src/test/java` directory.

## Custom Configurations

### Flyway Integration with Zonky Plugin for Test Containers

1. **Add Zonky Test Database Rider to your `pom.xml`:**

   ```xml
   <dependency>
       <groupId>io.zonky.test</groupId>
       <artifactId>zonky-test-db-spring-boot-starter</artifactId>
       <version>2.5.0</version>
       <scope>test</scope>
   </dependency>
   ```

2. **Configure Flyway for Database Migrations:**

   - Add Flyway dependencies to your `pom.xml`.
   - Place your migration scripts in the `src/main/resources/db/migration` directory.
   - Flyway will automatically detect and apply these migrations on application startup.

### PostgreSQL Configuration

1. **Add PostgreSQL Driver Dependency to `pom.xml`:**

   ```xml
   <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
       <scope>runtime</scope>
   </dependency>
   ```

2. **Configure `application.properties` or `application.yml`:**

   - Set your datasource URL, username, and password.
   - Configure the connection pool if necessary.

### IntelliJ Run Configurations

- Set up your run configurations in IntelliJ to include VM options for selecting the active profile, for example, `-Dspring.profiles.active=local`.
- You can also specify environment variables such as `DATABASE_URL` if required by your application.

