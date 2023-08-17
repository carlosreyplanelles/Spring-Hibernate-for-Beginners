# Spring Boot - Hibernate
- Hibernate handles all of the low-level SQL
- Minimizes the amount of JDBC code you have to develop
- Hibernate provides the Object-to-Relational Mapping (ORM)
- Hibernate is the default implementation of JPA

![ORM](https://cdn.javarush.com/images/article/4b8f2fcd-2c48-497e-96a6-bf2100f93425/800.jpeg)

## Hibernate Starters 

| | |
|-----------------------------------------|--------------------------------------------------------------------|
|             `Mysql Driver`             |                         Mysql JDBC Driver                           |
|             `Spring Data JPA`         | Spring Data JPA provides repository support for the Jakarta Persistence API (JPA). It eases development of applications that need to access JPA data sources. |
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

| Annotation          | Description                                                                                              |
|---------------------|----------------------------------------------------------------------------------------------------------|
| `@Entity`           | Marks a class as a JPA entity, representing a database table.                                          |
| `@Table`            | Specifies the name of the database table associated with the entity.                                   |
| `@Id`               | Marks a field as the primary key of the entity.                                                        |
| `@GeneratedValue`   | Configures the way the primary key is generated (e.g., `GenerationType.IDENTITY`, `GenerationType.SEQUENCE`). |
| `@Column`           | Specifies the mapping of a field to a column in the database table.                                    |
| `@Repository`        | Special `@Component` annotation implementation. Takes care of the bean creation (like `@Component` annotation) and also manages JDBC errors. |
| `@Transactional`     | Can be used on class or method level. This annotation handles the transaction to the database and the connection closing; also, it will rollback the method calls when an unhandled exception is thrown. (The checked exception does not trigger a rollback of the transaction. We can configure this behavior with the `rollbackFor` and `noRollbackFor` annotation parameters.) |

In Spring Boot `EntityManager` is main component for creating queries. Some of the common methods used are:

| EntityManager Method                       | Description                                                                                     |
|--------------------------------------------|-------------------------------------------------------------------------------------------------|
| `persist(Object entity)`                   | Make an instance managed and persistent.                                                      |
| `merge(Object entity)`                     | Merge the state of the given entity into the current persistence context.                      |
| `remove(Object entity)`                    | Remove the entity instance.                                                                     |
| `find(Class<T> entityClass, Object id)`    | Find by primary key.                                                                            |
| `getReference(Class<T> entityClass, Object id)` | Get a reference to the entity.                                                             |
| `createQuery(String qlString)`             | Create an instance of `Query` for executing a Java Persistence Language (JPQL) query.          |
| `createNamedQuery(String name)`            | Create an instance of `Query` for executing a named JPQL query.                                 |
| `createNativeQuery(String sqlString)`      | Create an instance of `Query` for executing a native SQL query.                                 |
| `flush()`                                  | Synchronize the persistence context to the underlying database.                                 |
| `clear()`                                  | Clear the persistence context, causing all managed entities to become detached.                |
| `detach(Object entity)`                    | Detach an entity from the persistence context, making it detached.                              |
| `contains(Object entity)`                  | Check if an entity is managed in the persistence context.                                      |
| `getLockMode(Object entity)`               | Get the lock mode for an entity.                                                               |
| `refresh(Object entity)`                   | Refresh the state of the instance from the database.                                           |
| `setFlushMode(FlushModeType flushMode)`     | Set the flush mode for the persistence context.                                                |
| `getFlushMode()`                           | Get the current flush mode for the persistence context.                                       |
| `setFlushMode(FlushModeType flushMode)`     | Set the flush mode for the persistence context.                                                |
| `lock(Object entity, LockModeType lockMode)` | Lock an entity with the specified lock mode.                                                 |
| `unwrap(Class<T> cls)`                    | Obtain an instance of the given class type from the provider’s runtime service.                |


[Student Entity](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/01-cruddemo-student/src/main/java/com/luv2code/cruddemo/entity/Student.java)

[StudentDAO (Data Access Object) interface](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/01-cruddemo-student/src/main/java/com/luv2code/cruddemo/dao/StudentDAO.java)


[StudentDAO interface impementation](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/01-cruddemo-student/src/main/java/com/luv2code/cruddemo/dao/StudentDAOImpl.java)

[Main program](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/01-cruddemo-student/src/main/java/com/luv2code/cruddemo/CruddemoApplication.java)

## Creating tables from java Classes

Application can be configured to create new tables based on the classes annotations. In order to do enable it the property spring.jpa.hibernate.ddl-auto needs to be configured.

| spring.jpa.hibernate.ddl-auto value  | Effect                                                                                                        |
|-------------------------------------|---------------------------------------------------------------------------------------------------------------|
| `none`                              | No database Schema initialization                                                                           |
| `create`                            | Drops and creates the schema at the application startup. With this option, all your data will be gone on each startup. |
| `create-drop`                       | Creates schema at the startup and destroys the schema on context closure. Useful for unit tests.          |
| `validate`                          | Only checks if the Schema matches the Entities. If the schema doesn’t match, then the application startup will fail. Makes no changes to the database. |
| `update`                            | Updates the schema only if necessary. For example, If a new field was added in an entity, then it will simply alter the table for a new column without destroying the data. |


## Advanced Mappings
FOr this examples you will have to create the dao entity and services following the steps mentioned in the CRUD previous section. The main change will be made in the entities used adding the fields to define the relation between the tables.

| Annotation          | Description                                                                                              |
|---------------------|----------------------------------------------------------------------------------------------------------|
| `@OneToMany`        | Defines a one-to-many relationship between two entities.                                               |
| `@ManyToOne`        | Defines a many-to-one relationship between two entities.                                               |
| `@OneToOne`         | Defines a one-to-one relationship between two entities.                                                |
| `@ManyToMany`       | Defines a many-to-many relationship between two entities.                                              |
| `@JoinColumn`       | Specifies the foreign key column used in a relationship.                                               |
| `@JoinTable`        | Configures the details of a many-to-many relationship and the join table.                              |
| `@Cascade`          | Configures cascading operations (e.g., `CascadeType.ALL`, `CascadeType.PERSIST`, `CascadeType.REMOVE`). |
| `@Fetch`            | Specifies the fetching strategy (e.g., `FetchType.LAZY`, `FetchType.EAGER`).                           |
| `@Transient`        | Marks a field as not persistent, i.e., not to be stored in the database.                               |
| `@Embedded`         | Indicates that a field is an embedded object and its attributes should be persisted as part of the owning entity. |

When defining a relationship between two entities we can define the cascade type (or types). This will affect to the behavior when any of the entities in the relationship is modified. The cascade methods are:
| Cascade Option         | Description                                                                                                                                                                                                                                  |
|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `CascadeType.ALL`      | All operations (INSERT, UPDATE, DELETE) performed on the parent entity will be cascaded to the associated child entities.                                                                                                                  |
| `CascadeType.PERSIST`  | The `persist` operation (INSERT) performed on the parent entity will be cascaded to the associated child entities.                                                                                                                         |
| `CascadeType.MERGE`    | The `merge` operation (UPDATE) performed on the parent entity will be cascaded to the associated child entities.                                                                                                                           |
| `CascadeType.REMOVE`   | The `remove` operation (DELETE) performed on the parent entity will be cascaded to the associated child entities.                                                                                                                          |
| `CascadeType.REFRESH`  | The `refresh` operation will propagate from the parent entity to the child entities, effectively refreshing their state to match that of the parent entity.                                                                             |
| `CascadeType.DETACH`   | The `detach` operation will detach the parent entity along with its associated child entities from the current persistence context. This means they will no longer be managed by Hibernate's session.                                  |
| `CascadeType.ALL_DELETE_ORPHAN` | This cascade type is specific to Hibernate. It is similar to `CascadeType.ALL`, but it also deletes orphaned child entities that are no longer associated with a parent entity.                                      |
| `CascadeType.REPLICATE` | The `replicate` operation is specific to Hibernate. It propagates from the parent entity to the child entities, creating new instances in the database that correspond to the state of the parent and its children.                        |


### One to One
We will use the following database schema for this example that can be create with the following [script](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/00-jpa-advanced-mappings-database-scripts/hb-01-one-to-one-uni/create-db.sql):
![Schema](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/images/Screenshot_1.png)
After following the process a new field will be added to the [instructor entity](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/02-jpa-one-to-one-uni/src/main/java/com/example/cruddemo/entity/Instructor.java) to represent the relation between both entitities.
```
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail;

We will define a new private field of related entity (InstructorDetail) in our instructor. When performing a persist operation, Springboot will save the IntructorDetail along with the instructor.
```

A bidirectional relationship  can be defined between entities without modifying the database. To do that, we will define a property in the entity where we want to define the relationship and indicate the field on the parent entity to correlate both. 
I.e: In order to define a bidirectional relationship between [Instructor]() and [InstructorDetail](), we will hace to create an Instructor property in our InstructorDetail and indicate the field used to map the relationship in the instructor table.
```
  @OneToOne(
            cascade={CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "instructorDetail")
    private Instructor instructor;
```

[One-to-One project]()

### One to many/Many to one
The following database schema  will be used as an example for One to Many relationship this example that can be create with the following [script](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/00-jpa-advanced-mappings-database-scripts/hb-03-one-to-many/create-db.sql):
![Schema](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/images/instructor-course-one-to-many.png)
The implementation have to meet this requirements:
- Relationship must be bi-directional
- Deleting a course shouldn't trigger the deletion of the instructor.
- Deleting an instructor should also delete all the related courses.
Steps:

1. Create the database schema using this section [script](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/03-spring-boot-hibernate-jpa-CRUD/00-jpa-advanced-mappings-database-scripts/hb-03-one-to-many/create-db.sql).

2. Create a new entity for the [Course](). We will have to add an Instructor private property to represent the many to one relationship (many courses can be taught by one Instructor):

```
@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;
```

3. After that we will update the [instructor]()  to represent the one to many relationship (one instructor can teach many courses)
```
@OneToMany(mappedBy="instructor",
    cascade={CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;
```
We will also add some methods to assign the courses to the Instructors and create the Bi-directional relationship.
```
public void add (Course newCourse){
        if(courses == null){
            courses = new ArrayList<>();
        }
        //Bi-directional relationship definition
        courses.add(newCourse);
        newCourse.setInstructor(this);
    }
```

[One-to-many/Many-to-one Project]()



### Many to Many

![Schema](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/images/ManyToMany.png)

As you can see in the schema, to define a many to many relationship (students can attend many courses, courses can be attended by many students) we will use a join table to represent the relationship betwen both entities. This table will define the relation between the entities through the id values.
To define this we will use the following annotations:
| Annotation          | Description                                                                                              |
|---------------------|----------------------------------------------------------------------------------------------------------|
| `@ManyToMany`       | Defines a many-to-many relationship between two entities.                                              |
| `@JoinColumn`       | Specifies the foreign key column used in a relationship.                                               |
| `@JoinTable`        | Configures the details of a many-to-many relationship and the join table.                              |

We will create an [Student]() entity and update the [Course]() entity to include a list of students. In this classes we will create auxiliary methods to add students to courses (Course entity) and courses to students (student entity)
In order to set the relationship between entites we will have to indicate how each of the entitities relate with the intermediate table. I.e: [Student entity]()
```
@ManyToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="course_student",
    joinColumns = @JoinColumn(name="student_id"),
    inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Course> courses;
```


[Many-to-Many project]()

## Eager loading vs Lazy loading
 - **Eager**:  When retrieving an entity from the database it retrieves the entity requested with all the related entities information.
 - **Lazy**: It retrieves the requested entity information. For any related entity information it will be retrieved when required on request. 

Eager loading can cause performance issues when there's too many relationed entities (ie: retrieve all the students for a all of the courses for a specific instructor). Because of that, lazy loading is recommended for most of the cases.
If lazy loading is being used, we need to make sure that the session is open . In order to do that we will have to make a new sql request to the database to retrieve the new data.

If a specific type of fetching is nmot provided in the relationship annotation we will it will apply the default fetching value. This default fetching value will depend on the type of relationship between entities:
| Relationship Type | Annotation(s) | Default Fetch Type | Default Loading Method |
|-------------------|---------------|-------------------|------------------------|
| `One-to-One`        | @OneToOne     | EAGER             | Load with parent       |
| `One-to-Many`       | @OneToMany    | LAZY              | Load when accessed     |
| `Many-to-One`       | @ManyToOne    | EAGER             | Load with parent       |
| `Many-to-Many`      | @ManyToMany   | LAZY              | Load when accessed     |

Additionally, eager loading can be used on specific functions without changing the default fetching type by using JOIN FETCH sql request to retrieve the objects information.

```
TypedQuery<Instructor> query = entityManager.createQuery( "SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.id = :instructorId", Instructor.class);

This request will retrieve the information of the courses related along with the instructor information. Retrieving the information through this function we will be using the eager loading methid for the instructor information. 
```




