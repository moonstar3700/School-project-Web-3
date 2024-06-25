# Sportclub Web Application

This project is a Java web application built using JSP and Servlets, following the MVC architecture and emphasizing clean code practices. The theme of the application is a website for a sport club.

## Project Overview

The Sportclub Web Application is designed to manage the activities of a sport club. The application includes features such as CRUD operations, authorization, and authentication. The database used for this project is PostgreSQL.

## Features

- **CRUD Operations:** Create, read, update, and delete functionalities for various entities in the sport club.
- **Authentication:** Secure login for users with different roles.
- **Authorization:** Role-based access control with different levels of administrative rights.
- **User Roles:** 
  - **Trainer:** Minimal administrative rights.
  - **Coordinator:** Can manage users in their group and matches.
  - **Admin:** Full administrative capabilities.

## Technologies Used

- **HTML/CSS:** 
- **Java:** 
- **JSP (JavaServer Pages):** For creating dynamic web pages.
- **Servlets:** To handle HTTP requests and responses.
- **PostgreSQL:** Database system used for storing application data.
- **Apache Tomcat:** Web server and servlet container for deploying the application.
- **IntelliJ IDEA:** Recommended IDE for creating and running the project.

## Setup Instructions

### Prerequisites

- Install [Apache Tomcat](https://tomcat.apache.org/)
- Install [PostgreSQL](https://www.postgresql.org/)

### Database Setup

1. **Create Database:**
   - Run the queries found in the `web 3 db.txt` file to set up the database schema.

2. **Update Database Credentials:**
   - Update the database credentials in the `src/main/java/util/Secret.java` file.

3. **Inject Admin User:**
   - Run the `src/main/java/ui/view/UserView.java` file to inject an admin user into the database.

### Running the Application (in Intellij)

1. Open the project in IntelliJ IDEA.
2. Build the project to generate the WAR file.
3. Deploy the WAR file to the Tomcat `webapps` directory.
4. Start the Tomcat server.
5. Access the application via `http://localhost:8080/YourAppName`.

### Initial Login

- Use the following credentials to log in as the admin:
  - **Email:** admin@admin.be
  - **Password:** t

## User Roles and Permissions

- **Trainer:** 
  - Has minimal administrative rights.
  - Limited access to certain features.

- **Coordinator:**
  - Can manage users in their group.
  - Can administrate matches.

- **Admin:**
  - Has full administrative capabilities.
  - Can manage all aspects of the application.

## Acknowledgements

This project is created for educational purposes to understand the basics of web application development using Java, JSP, and Servlets with a focus on the MVC architecture and clean coding practices.
