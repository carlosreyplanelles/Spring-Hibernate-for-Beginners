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
  #### Injection types

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

# Bean configurations

**@Scope:** specifies the configuration to be used for the bean
  -  **singleton:** Scopes a single bean definition to a single object instance per Spring IoC container.
  - **prototype:** Scopes a single bean definition to any number of object instances.
  - **request:** Scopes a single bean definition to the lifecycle of a single HTTP request; that is each and every HTTP request will have its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware   Spring ApplicationContext.
  - **session:** Scopes a single bean definition to the lifecycle of a HTTP Session. Only valid in the context of a web-aware Spring ApplicationContext.
  - **global session:** Scopes a single bean definition to the lifecycle of a global HTTP Session. Typically only valid when used in a portlet context. Only valid in the context of a web-aware Spring ApplicationContext.
  [Scopes documentation](https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch04s04.html)
**@PostConstruct:** Process performed after constructing the object
**@PreDestroy:** Process performed before the object is destroyed
The function passed to this annotations can have any name and return any type (although void is the most common) but should be no-args function.


# Configuration using Java Code (no XML)

  ## Annotations(@)
  - **@Configuration:** Used to mark that the class is used to define a configuration for the app.
  - **@ComponentScan("[package]"):** Used to indicate to the app which context will be scanned for the annotations. Additionally, the package which will be scanned can be indicated as a parameter referencing the compiled object(.class)
  - **@Bean**: Use to define beans inside a configuration file.

  ## Spring MVC

  ### Initial Config

  1 - Configure the application so it gets all the components and build the route to the views files.


*MVC-config.xml*
  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans
    	http://www.springframework.org/schema/beans/spring-beans.xsd
    	http://www.springframework.org/schema/context
    	http://www.springframework.org/schema/context/spring-context.xsd
    	http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

	<!-- Step 3: Add support for component scanning -->
	<context:component-scan base-package="com.luv2code.springdemo" />

	<!-- Step 4: Add support for conversion, formatting and validation support -->
	<mvc:annotation-driven/>

	<!-- Step 5: Define Spring MVC view resolver -->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
	</bean>

</beans>

  ```



2 - Configure the dispatcher servlet.

  *web.xml*
  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
	id="WebApp_ID" version="3.1">

	<display-name>spring-mvc-demo</display-name>

	<absolute-ordering />

	<!-- Spring MVC Configs -->

	<!-- Step 1: Configure Spring MVC Dispatcher Servlet -->
	<servlet>
		<servlet-name>dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring-mvc-demo-servlet.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<!-- Step 2: Set up URL mapping for Spring MVC Dispatcher Servlet -->
	<servlet-mapping>
		<servlet-name>dispatcher</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
	
</web-app>
  ```

This files have to be stored on the WEB-INF under Content directory.



Given the route configured in our MVC-config.xml fil example, The views are being retrieved from the view directory under WEB-INF
```
<property name="prefix" value="/WEB-INF/view/" />
```

and the extensions of the views file is jsp:

```
<property name="suffix" value=".jsp" />
```

### Views and Controllers
Between this prefix and suffix the name of the file will be added. We will have to implement a controller that returns the name of the file and assign it to  a mapping using the **@RequestMapping** annotation on the corresponding method:
```
@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showPage() {
		return "main-menu";
	}

}
```

this will return the information contained on the file */WEB-INF/view/main-menu.jsp*

### Adding parameters to the request

1- create a form and return his values:
```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Spring MVC - demo - Form demo</title>
    </head>

    <body>
        <h2>Hello Form</h2>
        <form action="processFormShout" method="GET">
            <input type="text" name="studentName" />
            <input type="submit">
        </form>
    </body>

    </html>
```
Use the attribute name inside the input file to define the parameter *name* that will be added to the URL.

2- Accesss parameters and show them in the interface

```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>

    <body>
        <h2>Hello World!</h2>

        <div>
            Student name: ${param.studentName}
        </div>
    </body>

    </html>
```

We will use ${param.\[param-name\]} to access to the parameters in the URL.

### Adding parameters to the model

1- Create a method that recieves the request and the model on the call and adds the attribute to the model

*src/com/luv2code/springdemo/mvc/HelloWorldController.java*

```
@RequestMapping("/processFormShout")
	public String processFormShout(HttpServletRequest req, Model model) {
		
		String name = req.getParameter("studentName");
		
		String msg = "YO! " + name.toUpperCase(); 
		
		model.addAttribute("msg", msg);
		
		return "helloWorld";
	}
```

2- Access to the attribute in the interface.

*/webContent/WEB-INF/view/helloWorld.jsp*

```
<div>
  The message: ${msg}
</div>
```
With this request we are accessing to the object msg from the model.

### Forms
When using jsp pages there are some specific elements that can be used for the form fields.

To enable the compiler to understand this special form fields the library have to be added to the html file:

```
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
```
After adding this library this form components can be used by adding the prefix *form:*:

```
<form:input path="firstName"/>
```
#### inputs

*/webContent/WEB-INF/view/student-form.jsp*

```
form:form action="confirmation" modelAttribute="student">
		<div>
		First Name: <form:input path="firstName"/>
		</div>
    ...
```

**modelAttribute:** represent the object that we will assign values when submitting this form
**path:** indicates which field form the modelAttrubite object have to be used to store the information provided in the input.

After submitting the form information annotation **@ModelAttribute** can be used to access to it (the parameter passed to the annotation has to match with the name used in the **modelAttribute**):

*src/com/luv2code/springdemo/mvc/StudentController.java*

```
public String processForm(@ModelAttribute("student") Student student)
```
#### Checkboxes and Radio Buttons

For checkboes and radiobuttions we will use annontations forms:checkbox and form:radiobutton respetively.

*radioButton:*
``` 
<div>
			<form:radiobuttons path="favoriteLanguage" items="${student.favoriteLanguageOptions}"  />
	</div>
```

The path value will reresent the field of the modelAttribute defined in the form which will store the information.
The plural from this components can be used in the label (i.e.:form:radiobuttons) to indicate the elements that are in this group of the form through a stored map or properties file.

##### Class map

1. **Create the Map** in the Object class file and populate in the no-Args constructor:
    
    *src/spring.luv2code.springdemo.mvc/Student.java:*
    ```
    private LinkedHashMap<String, String> favoriteLanguageOptions;
    public Student() {
		
		favoriteLanguageOptions = new LinkedHashMap<>();
		favoriteLanguageOptions.put("Java", "Java");
		favoriteLanguageOptions.put("C#", "C#");
		favoriteLanguageOptions.put("Python", "Python");
		...
    ```
2. **Retrieve it** in the form:
    
    *WEB-INF/view/student-form.jsp:*
    ```
    <div>
			<form:radiobuttons path="favoriteLanguage" items="${student.favoriteLanguageOptions}"  />
		</div>
    ```
##### Properties file

1.  **Create a properties file.**
    
    *WEB-INF/countries.properties:*
    ```
    BR=Brazil 
    FR=France 
    CO=Colombia 
    IN=India
    ```

2. **Update spring config file** and create ban reverencing the properties file.
    
    *WEB-INF/spring-servlet.xml*

    ```
        <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans" 
            xmlns:context="http://www.springframework.org/schema/context" 
            xmlns:mvc="http://www.springframework.org/schema/mvc" 
            xmlns:util="http://www.springframework.org/schema/util" 
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
            xsi:schemaLocation="
                http://www.springframework.org/schema/beans     
                http://www.springframework.org/schema/beans/spring-beans.xsd     
                http://www.springframework.org/schema/context     
                http://www.springframework.org/schema/context/spring-context.xsd     
                http://www.springframework.org/schema/mvc         
                http://www.springframework.org/schema/mvc/spring-mvc.xsd 
                http://www.springframework.org/schema/util     
                http://www.springframework.org/schema/util/spring-util.xsd">

                ...

                <util:properties  id="countries" location="classpath:../countries.properties" />
                ...
    ```
3. **Inject the bean** and **Add the attribute to the model**
  
  *src/spring.luv2code.springdemo.mvc/StudentController.java:*

    ```
        @Value("#{counties}") 
        private Map<String, String> countries;

        public String showForm(Model model) {
          ...
          model.addAttribute("countries", countries); 
          ...
    ```
4. **Access to the injected properties file**

*src/spring.luv2code.springdemo.mvc/Student-form.java:*
    ```
    ...
    <form:select path="country"> 
      <form:options items="${countries}" />
    </form:select>
    ...
    ```

#### Validations

For configuring validations hibernate validator libraries must be added. 
First, the validator library have to be downloaded from [hiernate.org](https://hibernate.org/validator)

[Validations basic guide](https://www.baeldung.com/javax-validation)
[Validators doc](https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html)

1. Create a model and add the validations that has to be applied.

*src/spring.luv2code.springdemo.mvc/Customer.java:*

```
package com.luv2code.springdemo.mvc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Customer {
	
	private String firstName;
	
  //lastName field is required Validations.
	@NotNull(message="required field")
	@Size(min=1, message="required field")
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
```

2. Create the form to add customer info

```
<form:form action="validate" modelAttribute="customer" >
		<div>
			First name: <form:input path="firstName"/>
		</div>
		<div>
			Last name: <form:input path="lastName"/>
			<form:errors path="lastName" class="error"/>
		</div>
		<input type="submit" value="Submit"/>
	</form:form>
```
form:errors is used to retrieve any error message relate to the customer.

3. Create the controller to handle the validation:

*src/spring.luv2code.springdemo.mvc/CustomerController.java:*

```
package com.luv2code.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

  //Trim all the trailing and leading spaces
  @InitBinder
	public void trimSpaces(WebDataBinder dataBinder) {
    //stringTrimEditor(true) => if all the elements are spaces returns a null object.
		StringTrimmerEditor ste = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, ste);
	}
	
	@RequestMapping("/form")
	public String getForm(Model model) {
		Customer c = new Customer();
		model.addAttribute("customer", c);
		return "customer-form";
	}
	
	
	@RequestMapping("/validate")
	public String validateForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "customer-form";
		} else {
			return "customer-confirmation";
		}
	}

}
```
Annotation **@Valid** in the validateForm method signature triggers the validation for the form. In order to confirm that the customer information in the model is valid, method hasErrors() for bindingResult will contain all the validation errors. if there's any error on the bindingResult it will return to the form when submitting it showing the current errors. If there's no error will redirect to the success page.

Annotation **@InitBinder** is used to indicate the preprocessing that will be applied prior to validation.

Annotation **@Pattern** is used touse a regular expression to verify the structure of an string based on a regular expression(regexp)

##### Custom Validation Methods

1. Define the new annotation implementation:

*src/com/luv2code/springdemo/mvc/validation/CourseValidation*

```
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

	public String value() default "LUV";
	public String message() default "Course code must start with LUV";
	
	//define the groups of errors that are related to this Validation annotation
	public Class<?>[] groups() default {};
	
	//define payloads
	public Class<? extends Payload>[] payload() default {};
}
```
- **@Constraint:** indicates the class that will handle this annotation.
- **@Target:** Indicates which levels this annotation can be applied.
- **@Retention:** How the annotation have to be cached and handled by the JVM
- **@interface:** Is used to declare a new annotation type

2. Define the handler class implementation for this annotation.

*src/com/luv2code/springdemo/mvc/validation/CourseCodeConstraintValidator.java*

```
public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String>{
	
	
	private String coursePrefix;
	@Override
	public void initialize(CourseCode courseCode) {
		// TODO Auto-generated method stub
		coursePrefix = courseCode.value();
	}

	@Override
	public boolean isValid(String userCourse, ConstraintValidatorContext arg1) {
		boolean result = true;
		
		if (userCourse != null) {
			result = userCourse.startsWith(coursePrefix);
		}
		return result;
	}
  ```
Method **isValid** will be override in order to define the condition which make the element under this annotation to be valid.

3. Use the new annotation. the name will match with the @interface created.

### Custom Error messages


*src/resources/message.properties*

```
TypeMismatch.customer.freePasses = Invalid number
```
The identifier to be used for the error messages can be found in the bindingResults object errors List.

*mvc servlet config file (spring-mvc-demo-servlet.xml)*

```
<!-- Load custom messages -->
	<bean id="messageSource" 
		class="org.springframework.context.support.ResourceBundleMessageSource">
		<property name="basenames" value= "resources/messages"/>	
	</bean>

```

## Hibernate

### Initial Configuration

Requirements:
- Use Java 8
- Install [MySql](https://dev.mysql.com/downloads/windows/installer/8.0.html)
- Download the [Hibernate library](https://sourceforge.net/projects/hibernate/files/hibernate-orm/5.6.5.Final/hibernate-release-5.6.5.Final.zip/download)
- Download the [Connector/J](https://dev.mysql.com/downloads/connector/j/) jar file
____

1. Copy all the required libs into the project lib directory (lib/required) from the hibernate library and the Connector/J .jar file.
2. Add the libs to the project classpath.

### Configuring JDBC connection

*src/hibernate.cfg.xml*

```
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>

    <session-factory>

        <!-- JDBC Database connection settings -->
        <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&amp;serverTimezone=UTC</property>
        <property name="connection.username">hbstudent</property>
        <property name="connection.password">hbstudent</property>

        <!-- JDBC connection pool settings ... using built-in test pool -->
        <property name="connection.pool_size">1</property>

        <!-- Select our SQL dialect -->
        <property name="dialect">org.hibernate.dialect.MySQLDialect</property>

        <!-- Echo the SQL to stdout -->
        <property name="show_sql">true</property>

		<!-- Set the current session context -->
		<property name="current_session_context_class">thread</property>
 
    </session-factory>

</hibernate-configuration>
```




*src/com/luv2code/hibernate/demo/entity/Student.java*

```
@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;

	public Student() {}
	
	public Student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
  /*GETTERS AND SETTERS*/
  @Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

```

**@Entity** Indicates this class represents a table in the database. If the class name and the table name are the same it it will connect both models automatically.
**@Table** Used to define the name of the table in the database to be related.Optional
**@Column** Defines the name of the column in the database that is stored in the following object.


### CRUD

  - [Create](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/e5f000b823eda20d3de212f2cd834363e0bf8a4a/hibernate_tutorial/src/com/luv2code/hibernate/demo/createEmployeeDemo.java)
  - [Read](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/e5f000b823eda20d3de212f2cd834363e0bf8a4a/hibernate_tutorial/src/com/luv2code/hibernate/demo/readEmployeeDemo.java)
  - [Update](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/e5f000b823eda20d3de212f2cd834363e0bf8a4a/hibernate_tutorial/src/com/luv2code/hibernate/demo/updateEmployeeDemo.java)
  - [Delete](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/e5f000b823eda20d3de212f2cd834363e0bf8a4a/hibernate_tutorial/src/com/luv2code/hibernate/demo/deleteEmployeeDemo.java)

*NOTE: IF CONFIGURATION FILE NAMEIS "hibernate.cfg.xml" ThHERE'S NO NEED TO SPECIFY IT*
The SessionFactory is a thread safe object and used by all the threads of an application. A Session is used to get a physical connection with a database.

![image](https://focusedlabs.io/hubfs/FocusedLabs_November_2022/Images/9995591c43c050fbfc25beacd8db1cc3d6eb7b75-600x315.png)

## Starters
Starters are groups of dependencies used to reduce the size of the pom file and make it easier to manage.

[Starters Official Doc](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.build-systems.starters)

  - **Actuator:** Actuator dependency provides of some endpoints to track the applications activity( endpoints, status of the app...)
      [Actuator Official Doc](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator)

  - **Security:** Allows to limit the access to specific endpoints and external resources.
  -  **Spring Boot Logging:** Logging systems configuration based on the pakage or to the whole app.
      [Loggin official doc](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)


## Server Configuration
Spring boot server can be configured through the properties file of the project
[Common properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties)


## Dependency injection
  
  For dependency injection **@Autowired** is used to perform this process.
  - If there's one constructor the Autowired annotation is not required. 
  - If the the object injected have only one implementation the type is infered.

Process:

  1. Define the **Interface**

      [src/main/java/com/luv2code/springcoredemo/common/Coach.java](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/02-component-scanning/src/main/java/com/luv2code/springcoredemo/common/Coach.java)

      ```
      public interface Coach {

        public String getDailyWorkout();
      }
      ```
    2. Define **interface implementation**
      [src/main/java/com/luv2code/springcoredemo/common/FootballCoach.java](src/main/java/com/luv2code/springcoredemo/common/FootballCoach.java)

      ```
      @Component
      public class FootballCoach implements Coach{
        @Override
        public String getDailyWorkout() {
        return "Run 20km!!!!";
        }
      }
      ```

### Types of injection
#### Constructor
  - Used for required dependencies
  - The method recommended by the spring.io development team.
    
    Create the **Controller**. Use the @aAutowired annotation to inject the dependency
    [src/main/java/com/luv2code/springcoredemo/rest/demoController.java](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/02-component-scanning/src/main/java/com/luv2code/springcoredemo/rest/demoController.java)

    ```
    public class demoController {
        private Coach demoCoach;

        @Autowired
        public demoController(Coach coach){
            demoCoach = coach;
        }
        ...
    ```
#### Setter
  - Optional dependencies
  - Reasonable default logic when the dependency is not provided.

  Create the setter method in the **Controller** and annotate it with the **@Autowired**

  [src/main/java/com/luv2code/springcoredemo/rest/demoController.java](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/03-setter-injection/src/main/java/com/luv2code/springcoredemo/rest/demoController.java)
  ```
  public class demoController {

    private Coach demoCoach;

    @Autowired
    public void setDemoCoach(Coach coach){
        demoCoach = coach;
    }
    ...
    ```
#### Field Injection   
  - Not recommended
  - Not unit testing friendly.
  



