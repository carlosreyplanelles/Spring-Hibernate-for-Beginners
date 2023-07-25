# Spring Boot - Hibernate
____
- Hibernate handles all of the low-level SQL
- Minimizes the amount of JDBC code you have to develop
- Hibernate provides the Object-to-Relational Mapping (ORM)
- Hibernate is the default implementation of JPA

[ORM](https://cdn.javarush.com/images/article/4b8f2fcd-2c48-497e-96a6-bf2100f93425/800.jpeg)

## Hibernate Starters 

- **Mysql Driver** Mysql JDBC Driver
- **Spring Data JPA** Spring Data JPA provides repository support for the Jakarta Persistence API (JPA). It eases development of applications that need to access JPA data sources.
[JPA Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)

## DB connection config

In order to stablish a connection with the DB URL and the access information needs to be provided in the properties file.

[Database properties](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/01-cruddemo-student/src/main/resources/application.properties)

```
spring.datasource.url=jdbc:mysql://localhost:3306/hb_student_tracker
spring.datasource.username=hbstudent
spring.datasource.password=hbstudent
```
## CRUD

In Spring Boot EntityManager is main component for creating queries.

**@Entity** Indicates this class represents a table in the database. If the class name and the table name are the same it it will connect both models automatically.
**@Table** Used to define the name of the table in the database to be related.Optional
**@Column** Defines the name of the column in the database that is stored in the following object.

[Student Entity](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/01-cruddemo-student/src/main/java/com/luv2code/cruddemo/entity/Student.java)

[StudentDAO (Data Access Object) interface](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/01-cruddemo-student/src/main/java/com/luv2code/cruddemo/dao/StudentDAO.java)

**@Repository** special @Component annotation implementation. Takes care of the bean creation (like @Component annotation) and also manages JDBC errors.
**@Transactional** Can be used on class or method level. This annotation handles the transaction to the database and the connection closing; also it will rollback the method calls when an unhandled expection is thrown **(The checked exception does not trigger a rollback of the transaction. We can, of course, configure this behavior with the rollbackFor and noRollbackFor annotation parameters)**.

[StudentDAO interface impementation](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/01-cruddemo-student/src/main/java/com/luv2code/cruddemo/dao/StudentDAOImpl.java)

[Main program](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/01-cruddemo-student/src/main/java/com/luv2code/cruddemo/CruddemoApplication.java)

## Creating tables from java Classes

Application can be configured to create new tables based on the classes annotations. In order to do enable it the property spring.jpa.hibernate.ddl-auto needs to be configured.

| **spring.jpa.hibernate.ddl-auto value** |                                                                                 **Effect**                                                                                  |
| :-------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|                **none**                 |                                                                      No database Schema initialization                                                                      |
|               **create**                |                           Drops and creates the schema at the application startup. With this option, all your data will be gone on each startup.                            |
|             **create-drop**             |                                      Creates schema at the startup and destroys the schema on context closure. Useful for unit tests.                                       |
|              **validate**               |           Only checks if the Schema matches the Entities. If the schema doesn’t match, then the application startup will fail. Makes no changes to the database.            |
|               **update**                | Updates the schema only if necessary. For example, If a new field was added in an entity, then it will simply alter the table for a new column without destroying the data. |
