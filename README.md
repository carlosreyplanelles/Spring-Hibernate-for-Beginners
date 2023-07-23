# Spring-Hibernate-for-Beginners

https://www.udemy.com/course/spring-hibernate-tutorial/

# Spring Boot

![image](https://focusedlabs.io/hubfs/FocusedLabs_November_2022/Images/9995591c43c050fbfc25beacd8db1cc3d6eb7b75-600x315.png)

## General

### Starters

Starters are groups of dependencies used to reduce the size of the pom file and make it easier to manage.

[Starters Official Doc](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.build-systems.starters)

- **Actuator:** Actuator dependency provides of some endpoints to track the applications activity( endpoints, status of the app...)
  [Actuator Official Doc](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator)

- **Security:** Allows to limit the access to specific endpoints and external resources.
- **Spring Boot Logging:** Logging systems configuration based on the pakage or to the whole app.
  [Loggin official doc](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)

## INDEX

1.  [Types of injection](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#types-of-injection)
    - [Constructor](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#constructor)
    - [Setter](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#setter)
    - [Field injection](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#field)
    - [Qualifiers](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#qualifiers)
2.  [Component Scanning](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#component-scanning)
3.  [Lazy Initialization](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#lazy-initialization)
4.  [Bean Scopes](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#bean-scopes)
    - [Bean Lifecycle methods](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#bean-lifecycle-methods)
5.  [Bean configuration using java class](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#bean-configuration-using-java-class)

6.  [Spring Security -Rest - Role based authentication]()

<details>
<summary>Server properties configuration through properties file</summary>

Spring boot server can be configured through the properties file of the project
[Common properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties)

## Beans
## Hibernate

- Hibernate handles all of the low-level SQL
- Minimizes the amount of JDBC code you have to develop
- Hibernate provides the Object-to-Relational Mapping (ORM)
- Hibernate is the default implementation of JPA

[ORM](https://cdn.javarush.com/images/article/4b8f2fcd-2c48-497e-96a6-bf2100f93425/800.jpeg)

<details>
<summary> Hibernate Starters </summary>

- **Mysql Driver** Mysql JDBC Driver
- **Spring Data JPA** Spring Data JPA provides repository support for the Jakarta Persistence API (JPA). It eases development of applications that need to access JPA data sources.
[JPA Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
</details>

<details>
<summary> DB connection config</summary>

In order to stablish a connection with the DB URL and the access information needs to be provided in the properties file.

[Database properties](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/01-cruddemo-student/src/main/resources/application.properties)

```
spring.datasource.url=jdbc:mysql://localhost:3306/hb_student_tracker
spring.datasource.username=hbstudent
spring.datasource.password=hbstudent
```

<details>
<summary>CRUD</summary>

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

</details>

<details>
<summary>Creating tables from java Classes</summary>

Application can be configured to create new tables based on the classes annotations. In order to do enable it the property spring.jpa.hibernate.ddl-auto needs to be configured.

| **spring.jpa.hibernate.ddl-auto value** |                                                                                 **Effect**                                                                                  |
| :-------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|                **none**                 |                                                                      No database Schema initialization                                                                      |
|               **create**                |                           Drops and creates the schema at the application startup. With this option, all your data will be gone on each startup.                            |
|             **create-drop**             |                                      Creates schema at the startup and destroys the schema on context closure. Useful for unit tests.                                       |
|              **validate**               |           Only checks if the Schema matches the Entities. If the schema doesn’t match, then the application startup will fail. Makes no changes to the database.            |
|               **update**                | Updates the schema only if necessary. For example, If a new field was added in an entity, then it will simply alter the table for a new column without destroying the data. |

</details>
</details>

<details>
<summary>REST</summary>

## Rest Services

Steps to implement rest service:

1. Set up Database Dev Environment
2. Create Spring Boot project using Spring Initializr
3. Implement DAO layer
   > 1. Update db configs in application.properties
   >    [properties](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/resources/application.properties)
   > 2. Create Employee entity
   >    [entity](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/entity/Employee.java)
   > 3. Create DAO interface
   >    [DAO interface](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/dao/EmployeeDAO.java)
   > 4. Create DAO implementation
   >    [DAO impl](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/dao/EmployeeDAOImpl.java)
   > 5. Create REST controller to use DAO
   >    [Rest Controller](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/rest/EmployeeRestController.java)
4. Implement Service layer
   [Employee Service interface](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/service/EmployeeService.java)
   [Employee Service Impl](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/service/EmployeeServiceImpl.java)
5. Get list of employees
6. Get single employee by ID
7. Add a new employee
8. Update an existing employee
9. Delete an existing employee
   [Rest Controller](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/rest/EmployeeRestController.java)

## Error Handling

1. Create a custom error response class
   [StudentErrorResponse] (https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/demo/src/main/java/com/luv2code/demo/exception/StudentErrorResponse.java)
2. Create a custom exception class
   [StudentNotFound exception](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/demo/src/main/java/com/luv2code/demo/exception/StudentNotFoundException.java)
3. Update REST service to throw exception if student not found
   [StudentRestController (Exception Handling)](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/demo/src/main/java/com/luv2code/demo/rest/StudentRestController.java)
4. Add an exception handler method using @ExceptionHandler for Student not found exception
   [StudentRestController (Exception Handling)](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/demo/src/main/java/com/luv2code/demo/rest/StudentRestController.java)
5. Add an exception handler method using @ExceptionHandler for General exceptions
   [StudentRestController (Exception Handling)](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/demo/src/main/java/com/luv2code/demo/rest/StudentRestController.java)

In order to share this exception handling through all the Controllers a class for exception handling has to be created outside the controllers, be annotated as _@ControllerAdvice_ and move all the exception management code into this class. The methods in an @ControllerAdvice apply globally to all controllers.
[Exception handling class](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/demo/src/main/java/com/luv2code/demo/exception/StudentRestExceptionHandler.java)

## JPA Repositories

Spring Data JPA provides the interface: JpaRepository
Will have some basic CRUD methods (and queries) defined and implemented.
[Define custom queries](www.luv2code.com/spring-data-jpa-defining-custom-queries)

## Spring Data Rest

Once injected through pom file, spring data rest dependency will create a basic REST service based on the jpaRepository automatically with the corresponding endpoints.

### Configuration

**@RepositoryRestResource** Allows to modify the route to access to a resource and to create custom routes (@RestResource(path = "byEmail", rel = "customFindMethod")).
[Employee JPA repository](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/40-spring-boot-rest-crud-employee-with-spring-data-rest/src/main/java/com/luv2code/springboot/cruddemo/dao/EmployeeRepository.java)

When using spring data rest service HATEOAS service will generate the response paginated on batches of 20 registers. Pagination along with other properties can be modified via properties file:
[Spring Data Rest properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.data.spring.data.rest.base-path)

</details>

<details>

</details>
<details>
<summary>Thymeleaf</summary>

Thymeleaf is a templating framework used for java applications. This framework can be used for any type of application based on java.
[Thymeleaf docs](https://www.thymeleaf.org/documentation.html)

In order to return an html file, a controller have to be created and return the name of the html file without the extension. All the information will be added to the model by using the addAttribute method indicating the name and the value for this property in the model.
[Controller](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/07-Spring-boot-spring-mvc/01-thymeleafdemo-helloworld/src/main/java/com/luv2code/springboot/thymeleafdemo/controller/DemoController.java)

## Resources

By default, the system look for the templates in the resources folder of the project.

## Accessing java objects information

In order to access java objects information from the template we will have to will use the syntax ${\[Model property name\]}
[HTML template](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/07-Spring-boot-spring-mvc/01-thymeleafdemo-helloworld/src/main/resources/templates/helloWorld.html)

</details>

[SpringBoot Security](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/08-spring-boot-spring-mvc-security)

[Final Spring Boot Project - Employees Directory CRUD](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/08-spring-boot-spring-mvc-crud)

</details>
