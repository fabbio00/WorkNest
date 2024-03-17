# Overview
**WorkNest** is a web application designed to facilitate the booking of desks and meeting rooms within a co-working space by end users and companies. The application allows users to browse available workspaces, make bookings, manage their reservations, and create communities. Companies have access to a dedicated section for managing employees and their bookings. WorkNest also includes an administration section for managing user data, bookings, and agreements with other companies. The application is built using `Vue.js` for the frontend, `Java` with `Spring` framework for the backend, and `PostgreSQL` for the database.

# Technologies Used
### Frontend:

- **Vue.js**: A progressive JavaScript framework for building user interfaces.
- **npm**: A package manager for Node.js modules.
- **CSS**: Cascading Style Sheets for styling the frontend components.

### Backend:

- **Java/Spring**: A powerful and comprehensive framework for building enterprise-level applications.
- **Maven**: A build automation tool for managing Java projects and dependencies.


### Database:

- **PostgreSQL**: An open-source relational database management system.

# Architectural Approach
- **MVC Pattern**: The application architecture follows the Model-View-Controller (MVC) pattern to separate concerns and improve maintainability.
- **Model**: Represents the data and business logic of the application. In WorkNest, models handle data related to users, bookings, workspaces, and companies.
- **View**: Represents the user interface of the application. Vue.js components are used to create dynamic and interactive views for users.
- **Controller**: Handles user input and interaction, as well as business logic execution. Java Spring controllers manage HTTP requests, process data, and interact with the backend services.
