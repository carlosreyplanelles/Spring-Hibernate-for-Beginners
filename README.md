# Spring-Hibernate-for-Beginners

https://www.udemy.com/course/spring-hibernate-tutorial/


<details>
<summary>Spring</summary>
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
____
</details>
<details>
<summary>Spring Boot</summary>

![image](https://focusedlabs.io/hubfs/FocusedLabs_November_2022/Images/9995591c43c050fbfc25beacd8db1cc3d6eb7b75-600x315.png)

## General
### Starters

  Starters are groups of dependencies used to reduce the size of the pom file and make it easier to manage.

  [Starters Official Doc](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.build-systems.starters)

  - **Actuator:** Actuator dependency provides of some endpoints to track the applications activity( endpoints, status of the app...)
      [Actuator Official Doc](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator)

  - **Security:** Allows to limit the access to specific endpoints and external resources.
  -  **Spring Boot Logging:** Logging systems configuration based on the pakage or to the whole app.
      [Loggin official doc](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)

## INDEX
 1. [Types of injection](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#injection_types)
    - [Constructor](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#constructor)
    - [Setter](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#setter)
    - [Field injection](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#field_injection)
    - [Qualifiers](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#qualifiers)
 2. [Lazy Initialization](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#lazy)
 3. [Bean Scopes](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#scope)
    - [Bean Lifecycle methods](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#lifecycle)
 4. [Bean configuration using java class](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/tree/main/02-spring-boot-spring-core#config_java_class)

<details>
<summary>Server properties configuration through properties file</summary>

  Spring boot server can be configured through the properties file of the project
  [Common properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties)
</details>

## Beans

<details>
<summary>Dependency injection</summary>
  
For dependency injection **@Autowired** is used to perform this process.
- If there's one constructor the Autowired annotation is not required. 
- If the the object injected have only one implementation the type is infered.

Process:
1. Define the **Interface**

    [Coach interface](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/02-spring-boot-spring-core/02-component-scanning/src/main/java/com/luv2code/springcoredemo/common/Coach.java)
      ```
      public interface Coach {

        public String getDailyWorkout();
      }
      ```
2. Define **interface implementation**

    [FootbalCoach implementation](src/main/java/com/luv2code/springcoredemo/common/FootballCoach.java)
      ```
      @Component
      public class FootballCoach implements Coach{
        @Override
        public String getDailyWorkout() {
        return "Run 20km!!!!";
        }
      }
      ```


</details>

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

| **spring.jpa.hibernate.ddl-auto value** | **Effect** |
|:---:|:---:|
| **none** | No database Schema initialization |
| **create** | Drops and creates the schema at the application startup. With this option, all your data will be gone on each startup. |
| **create-drop** | Creates schema at the startup and destroys the schema on context closure. Useful for unit tests. |
| **validate** | Only  checks if the Schema matches the Entities. If the schema doesn’t match,  then the application startup will fail. Makes no changes to the  database. |
| **update** | Updates the schema only if  necessary. For example, If a new field was added in an entity, then it  will simply alter the table for a new column without destroying the  data. |
</details>
</details>

<details>
<summary>REST</summary>

## Rest Services

Steps to implement rest service:

1. Set up Database Dev Environment
2. Create Spring Boot project using Spring Initializr
3. Implement DAO layer
  >1. Update db configs in application.properties
  [properties](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/resources/application.properties)
  >2. Create Employee entity
  [entity](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/entity/Employee.java)
  >3. Create DAO interface
  [DAO interface](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/dao/EmployeeDAO.java)
  >4. Create DAO implementation
  [DAO impl](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/dao/EmployeeDAOImpl.java)
  >5. Create REST controller to use DAO
  [Rest Controller](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/04-spring-boot-%20rest-CRUD/cruddemo/src/main/java/com/luv2code/springboot/cruddemo/rest/EmployeeRestController.java)
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

In order to share this exception handling through all the Controllers a class for exception handling has to be created outside the controllers, be annotated as *@ControllerAdvice* and move all the exception management code into this class. The methods in an @ControllerAdvice apply globally to all controllers.
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
<summary>Spring Security</summary>

[Security Reference Manual](https://docs.spring.io/spring-security/reference/)
## Authenication
In order to allow Spring boot to get the registration information from the database by default We will have to define the tables *users* and *authorities*:
```
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```
You can configure custom tables for user authentication. This will require to let the app know the specific query to retriever user and roles info.
[Role based Authentication](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/05-Spring-boot-rest-security/00-spring-boot-rest-security-employee-starter-code/src/main/java/com/luv2code/springboot/cruddemo/security/DemoSecurityConfig.java)
### Password encryption
Spring Security allow to store encrypted passwords using different algorithms (althought the recommended one is bcrypt).
The encryption algorithm used is stored in the database contained into brackets('{}'):
```
{noop}test123
{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q
```
**{noop}:** No encryption applied. The password is stored as plain text.
**{bcrypt}:** Bcrypt encryption is applied to the password. Bcrypt is a one way encripting. Wen introducing the user information will apply the salt in the password stored in the database to the password introduced by the user. The password in the database can't be decrypted.
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