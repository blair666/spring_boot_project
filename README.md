# spring_boot_project
# Project Management App

## Overview
The Project Management App is designed to help portery/on-site manager to find local service vendors, and get and compare quotes efficiently. This application is developed using Spring Boot, Thymeleaf, and Hibernate, with a MySQL database.

## Project Structure

com.blair.projectmgmtapp
├── controller
│   ├── AppController.java
│   ├── CategoryController.java
│   ├── ProjectController.java
│   ├── QuoteController.java
│   └── VendorController.java
├── model
│   ├── Category.java
│   ├── Project.java
│   ├── Quote.java
│   └── Vendor.java
├── repository
│   ├── CategoryRepository.java
│   ├── ProjectRepository.java
│   ├── QuoteRepository.java
│   └── VendorRepository.java
├── service
│   ├── CategoryService.java
│   ├── ProjectService.java
│   ├── QuoteService.java
│   ├── QuoteServiceImpl.java
│   ├── VendorServiceImpl.java
│   └── VendorService.java
└── ProjectmgmtappApplication.java

Unit Testing:
Controller Testing:
QuoteControllerTest

Repository Testing:
CategoryRepositoryTests
VendorRepositoryTest

Service Testing:
ProjectServiceTest
QuoteServiceImplTest
VendorServiceImplTest

## Features
- User authentication with signup and login functionality.
- Manage projects with CRUD operations.
- Manage categories, vendors, and quotes.
- Compare quotes and vendors.
- Dynamic web pages using Thymeleaf.
- RESTful web services.

## Setup and Installation
1. **Clone the repository**:
    ```
    git clone https://github.com/yourusername/project-mgmt-app.git
    ```
2. **Navigate to the project directory**:
    ```
    cd project-mgmt-app
    ```
3. **Configure the MySQL database**:
    - Update `application.properties` with your database credentials.
4. **Run the application**:
    ```
    mvn spring-boot:run
    ```

## Technologies Used
- **Java**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **Thymeleaf**
- **MySQL**
- **Lombok**
- **CSS & JavaScript**

## Business Use Cases
- Efficient project management.
- Vendor and quote comparison.
- Secure user authentication.

## Technical Overview
- **Spring Boot** is used for building the back-end.
- **Thymeleaf** is used for dynamic web pages.
- **Hibernate** is used for ORM.
- **MySQL** is used as the database.

## Learning Outcomes
- Gained experience in full-stack development with Spring Boot.
- Learned to use Hibernate for database operations.
- Improved skills in using Thymeleaf for dynamic web pages.



<img width="1426" alt="Home" src="https://github.com/blair666/spring_boot_project/assets/34014180/7cd690cd-afc5-4971-8382-41f61cde27f9">
![Screenshot 2024-06-13 at 5 14 42 PM](https://github.com/blair666/spring_boot_project/assets/34014180/28b2f759-9dd4-4a76-8dc7-e64f959433ae)


![Quotes](https://github.com/blair666/spring_boot_project/assets/34014180/414742a0-9b99-4a10-b754-277b424bf8be)
![Projects](https://github.com/blair666/spring_boot_project/assets/34014180/9be5613a-6f54-4828-ac08-bba4dfcabc63)

![Screenshot 2024-06-13 at 5 10 41 PM](https://github.com/blair666/spring_boot_project/assets/34014180/74496328-c8b1-4777-9dc1-f08d37388e59)
![Screenshot 2024-06-13 at 5 10 50 PM](https://github.com/blair666/spring_boot_project/assets/34014180/3ddccece-5351-459c-b2f0-4283954224e6)
