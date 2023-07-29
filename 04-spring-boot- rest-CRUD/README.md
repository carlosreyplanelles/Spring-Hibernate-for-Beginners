# Spring-Hibernate-for-Beginners
 ----
 
# Spring Boot - Rest Services
____

# Rest implementation
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
[Project example](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/04-spring-boot-%20rest-CRUD/30-spring-boot-rest-crud-employee-with-spring-data-jpa)

## Spring Data Rest

Once injected through pom file, spring data rest dependency will create a basic REST service based on the jpaRepository automatically with the corresponding endpoints.
[Project example](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/04-spring-boot-%20rest-CRUD/40-spring-boot-rest-crud-employee-with-spring-data-rest)