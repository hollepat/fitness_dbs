# fitness_dbs_system

This project focused on designing a database system for fitness center (Gyms).

This project is a Java application that demonstrates the integration of Jakarta Persistence API (JPA) with Hibernate, 
focusing on managing entities related to a membership fitness system. The application uses PostgreSQL as the database 
and showcases the implementation of data access, business logic, and transaction management.

## Technologies Used:

1. Jakarta Persistence API (JPA):
   Description: JPA is a specification in Jakarta EE for managing relational data in Java applications. It provides a set of annotations to map Java objects to database tables and facilitates database operations.
2. Hibernate:
   Description: Hibernate is an Object-Relational Mapping (ORM) framework that implements JPA. It simplifies database interactions by allowing developers to work with Java objects instead of SQL queries.
3. PostgreSQL:
   Description: PostgreSQL is an open-source relational database management system. In this project, it serves as the backend database for storing and retrieving entity data.
4. Jakarta EE Annotations:
   Description: Jakarta EE annotations are used to configure and manage various aspects of the application within a Jakarta EE container. Examples in the project include @Entity, @Table, @Column, and @PersistenceContext.
5. Java Transaction API (JTA):
   Description: JTA is a specification for managing transactions in Java applications. It allows for the definition of transaction boundaries and ensures the atomicity and consistency of database operations.
   Project Structure:

## Project structure

1. fel.cvut.cz.entity Package:
   Contains the Entity classes, annotated with JPA annotations (implementation - Jakarta EE), representing a business 
2. objects of the system
2. fel.cvut.cz.serviceDAO Package:
   Contains the MembershipService class and TaskService, providing a service for adding members to the system. It demonstrates the use of Hibernate to interact with the database and manage transactions.
3. fel.cvut.cz.dao Package:
   Contains the DAO objects for Entities, responsible for data access operations related to Entities. It showcases the usage of JPA EntityManager for CRUD operations.
4. fel.cvut.cz.Main Class:
   The main class of the project. It establishes a database connection, initializes the EntityManager, and demonstrates various operations like creating persons, creating memberships, and reading workout classes.

## Running the Project:

Ensure that the PostgreSQL database is set up and running.
Configure the database connection parameters in the Main class.
Run the Main class to execute the application.
Note: The project includes commented-out sections in the Main class, representing different operations. Uncomment and 
modify them as needed for specific functionalities.

Feel free to explore, extend, and adapt this project for your Jakarta EE and Hibernate-based applications.


