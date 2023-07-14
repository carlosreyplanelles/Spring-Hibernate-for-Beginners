  # Spring-Hibernate-for-Beginners

https://www.udemy.com/course/spring-hibernate-tutorial/
____

## INDEX
 1. [Types of injection](#injection_types)
    - [Constructor](#constructor)
    - [Setter](#setter)
    - [Field injection](#field_injection)
    - [Qualifiers](#qualifiers)
 2. [Lazy Initialization](#lazy)
 3. [Bean Scopes](#scope)
    - [Bean Lifecycle methods](#lifecycle)
 4. [Bean configuration using java class](#config_java_class)

  ### <a id="injection_types">Types of Injection</a>
  #### <a id="constructor">Constructor</a>
  - Used for required dependencies
  - The method recommended by the spring.io development team.
    Create the **Controller**. Use the @aAutowired annotation to inject the dependency
      [Controller implementation (constructor injection)](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/02-component-scanning/src/main/java/com/luv2code/springcoredemo/rest/demoController.java)

      ```
      public class demoController {
          private Coach demoCoach;

          @Autowired
          public demoController(Coach coach){
            demoCoach = coach;
          }
          ...
      ```
    #### <a id="setter">Setter</a>
      - Optional dependencies
      - Reasonable default logic when the dependency is not provided.

      Create the setter method in the **Controller** and annotate it with the **@Autowired**

      [Controller implementation (Setter injection)](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/03-setter-injection/src/main/java/com/luv2code/springcoredemo/rest/demoController.java)
      ```
      public class demoController {

        private Coach demoCoach;

        @Autowired
        public void setDemoCoach(Coach coach){
            demoCoach = coach;
        }
        ...
      ```

    #### <a id="field_injection">Field Injection</a>   
      - Not recommended
      - Not unit testing friendly.

      Set the **@Autowired** annotation to the field

      [Controller implementation (Field injection)](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/04-field-injection/src/main/java/com/luv2code/springcoredemo/rest/demoController.java)
      ```
      @RestController
      public class demoController {

        @Autowired
        private Coach demoCoach;
      ```
    ### <a id="qualifiers">Qualifiers</a>

    They are used to indicate the specific implementation to be used when there are more than one for the interface being used. 
    [Controller implementation (Qualifiers)](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/05-qualifiers/src/main/java/com/luv2code/springcoredemo/rest/DemoController.java)
    ```
        public class DemoController {

        private Coach demoCoach;

        @Autowired
        public DemoController(@Qualifier("baseballCoach") Coach coach){
        demoCoach = coach;
        }
    ```

    The ambiguity on the implementation requested can also be soved using the **@Primary** annotation in one of them.
    - **@Primary** can only be used for one of the interface implementations.
    - If one implementation is defined as **@Primary** there's no need to define a qualifier as it will solve the ambiguity by returning the annotated class.
    -  **@Qualifier has priority over @Primary annotation.**

____


## <a id="lazy">Lazy Initialization</a>

  - With lazy initialization beans are only created when they are injected or explicitly called.
  - By default this is disabled, althought can be enabled on properties configuration.

  [Application properties](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/07-Lazy/src/main/resources/application.properties)
  ```
  spring.main.lazy-initialization = true
  ```
  Also a class can be defined as lazy by using **@Lazy** annotation in the class.

  [TennisCoach implementation](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/07-Lazy/src/main/java/com/luv2code/springcoredemo/common/TennisCoach.java)
  ```
  @Component
  @Lazy
  public class TennisCoach implements Coach{
    ...
  ```

  Advantages
    - Only create the objects when they are used
    - If there are many components it reduces the startup time.
  
  Disadvantages
    - Web related components (like @RestController) are not created until they are called.
    - Makes more difficult to identify configuration issues.
    - You can run out of memory for all the beans.

## <a id="scope">Bean Scopes</a>

  **@Scope:** specifies the configuration to be used for the bean (Which is the bean context)

| **Scope** | **Use** |
|:---:|:---:|
| **singleton** | Scopes a single bean definition to a single object instance per Spring IoC container. |
| **prototype** | Drops and creates the schema at the application startup. With this option, all your data will be gone on each startup. |
| **request** | Scopes a single bean definition to the lifecycle of a single HTTP request; that is each and every HTTP request will have its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware   Spring ApplicationContext.|
| **session** | Scopes a single bean definition to the lifecycle of a HTTP Session. Only valid in the context of a web-aware Spring ApplicationContext.|
| **global session** | Scopes a single bean definition to the lifecycle of a global HTTP Session. Typically only valid when used in a portlet context. Only valid in the context of a web-aware Spring ApplicationContext.|

  [Code example](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/08-bean-scopes/src/main/java/com/luv2code/springcoredemo/common/BaseballCoach.java)

### <a id="lifecycle">Bean Lifecycle methods</a>

- **@PostConstruct:** Process performed after constructing the object
- **@PreDestroy:** Process performed before the object is destroyed

[Code example](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/09-bean-lifecycle/src/main/java/com/luv2code/springcoredemo/common/BaseballCoach.java)

____


## <a id="config_java_class">Bean configuration using java class</a>

Instead of using **@Component** annotation to declare a Bean, Configuration java classes can be used to define the beans manually. In order to achieve this a class needs to be created annotated with the **@Configuration** annotation.
The main reason to use configuration classes to define beans are injecting third parties which code can't be accessed and they don't include **@Component** annotation or any annotation to indicate that a bean must be created.

[Config class example](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/10-java-config-bean/src/main/java/com/luv2code/springcoredemo/config/SportConfig.java)