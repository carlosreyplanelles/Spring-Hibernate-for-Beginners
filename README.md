# Spring-Hibernate-for-Beginners

https://www.udemy.com/course/spring-hibernate-tutorial/

## Anotations (@)

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
