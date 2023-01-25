# Spring-Hibernate-for-Beginners

https://www.udemy.com/course/spring-hibernate-tutorial/

In order to use spring framework it has to be downloaded from:
https://repo.spring.io/artifactory/release/org/springframework/spring/5.3.9/

After it it has to be added to the build path.

## Anotations (@)
### General
In order to enable component scan this line have to be added to xml configuration file:

```
<context:component-scan base-package="com.luv2code.springdemo"/>
```

- **@Component:** Used to automatically create a bean of the class whicch has this annotation. By default, if we don't pass a value to the annotation the bean name will be the same than the class but first char in lower case.

```
@Component
public class TennisCoach implements Coach
```

We can pass an optional parameter with the bean name.

```
@Component("basketCoachBean")
public class BasketCoach implements Coach
```

### Depency Injection
- **@Autowired:** Used to inject dependencies automatically based on class or implemented interface. The service to be injected needs to have a bean defined.
  ####Injection types

  - **Constructor:**

    1. Define dependency interface and class

    ```
    public interface FortuneService {

        public String getFortune();
        ...
    }
    ```

    ```
    @Component
    public class HappyFortuneService implements FortuneService {

        @Override
        public String getFortune() {
            return "Today is your lucky day!";
        }
        ...
    }
    ```

    2. Create a constructor in the class
    3. Configure dependency injection with @Autowired

    ```
    @Autowired
    private TennisCoach(FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }
    ...
    ```

  - **Setter:**
    1. Create setter method
    2. Configure dependency injection with @Autowired annotation
    ```
    @Autowired
    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }
    ...
    ```
  - **Field:**

    ```
    public class BasketCoach implements Coach {

        @Autowired
        FortuneService fortuneService;
        ...
    }
    ```

    If there is more than one class that implements the wired interface, there's an special annotation that can be used.
    **@Qualifier** allows to point thi the specific implementation to wire.
    _Example:_

    - FortuneService interface

      - HappyFortuneService implements FortuneService
      - BadFortuneService implements FortuneService

      ```
      public class BasketCoach implements Coach {

        @Autowired
        @Qualifier("happyFortuneService")
        FortuneService fortuneService;
      ...
      ```

      @Qualifier annotation can also be used in constructor injection using it in the parameters:

      ```
      @Autowired
      private TennisCoach(@Qualifier("happyFortuneService") FortuneService theFortuneService) {
        fortuneService = theFortuneService;
        ...
      }
      ```

      In case of the setter method both can be used:

      ```
        ...
        @Autowired
      public void setFortuneService(@Qualifier("randomFortuneService") FortuneService theFortuneService) {
      	System.out.println(">> TennisCoach: inside setFortuneService() method");
      	this.fortuneService = theFortuneService;
      }
        ...
      ```

      ```
        ...
        @Autowired
        @Qualifier("randomFortuneService")
        public void setFortuneService(FortuneService theFortuneService) {
      	    System.out.println(">> TennisCoach: inside setFortuneService() method");
      	    this.fortuneService = theFortuneService;
        }
        ...
      ```

- **@Value(propertyName):** Retrieves a property from a propertyFile:
  _Properties file (src/sport.properties)_
  ```
  foo.email=myeasycoach@luv2code.com
  foo.team=Silly Java Coders
  ```
  _Properties file load in context_
  ```
  <context:property-placeholder location="classpath:sport.properties"/>
  ```
  _Properties injection_
  ```
  @Value("${foo.email}")
  private String email;

  @Value("${foo.team}")
  private String team;
  ```

  ## Bean configurations

  **@Scope:** Ised to specify the configuration to be used for the bean
    -  **singleton:** Scopes a single bean definition to a single object instance per Spring IoC container.
    - **prototype:** Scopes a single bean definition to any number of object instances.
    - **request:** Scopes a single bean definition to the lifecycle of a single HTTP request; that is each and every HTTP request will have its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware   Spring ApplicationContext.
    - **session:** Scopes a single bean definition to the lifecycle of a HTTP Session. Only valid in the context of a web-aware Spring ApplicationContext.
    - **global session:** Scopes a single bean definition to the lifecycle of a global HTTP Session. Typically only valid when used in a portlet context. Only valid in the context of a web-aware Spring ApplicationContext.
    [Reference](https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch04s04.html)
  **@PostConstruct:** Process performed after constructing the object
  **@PreDestroy:** Process performed before the object is destroyed
  The function passed to this annotations can have any name and return any type (although void is the most common) but should be no-args function.
