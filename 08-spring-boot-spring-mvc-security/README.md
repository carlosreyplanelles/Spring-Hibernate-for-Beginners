# Spring-Hibernate-for-Beginners
 ----

# Spring Boot - Spring Security
----

## Basic Configuration
### In memory authentication
1. Create a security configuration class 
2. Add users passwords and roles

```
Passwords are stored in Spring security using a special syntax to indicate the encryption used.

password syntax: {noop}password

Different encryption methods can be used for password encryption between the curly braces, but the most common are:
{noop} -> password is not encrypted and is stored as plain text. This is not recommended due to security vunerabilities.
{bcrypt} -> password is bcrypt encripted. Secure Most commonly used.
```
[Security configuration class - InMemoryUserDetailsManager Bean](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/java/com/luv2code/springboot/demoSecurity/security/DemoSecurityConfig.java)

### JDBC Authentication
In order to be able to use the jdbc authentication out of the box we will have to add some specific tables for the authetication: users and authorities. This tables will require specific fieldnames in order to allo springboot to get the required information:
![Standard Auth tables]()

After creating this tables in our database there's some changes that needs to be made on the code:  
- Add the pom dependencies:
[pom.xml](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/pom.xml)
```
<!-- JDBC dependencies-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<!---->
```
- Database connection information on properties file:
[application.properties](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/resources/application.properties)
```
#JDBC connection properties
spring.datasource.url= <database connection chain>
spring.datasource.username= <database username>
spring.datasource.password= <username password>

 # Log all JDBC SQL events
 # ONLY FOR DEV AND STAGING DON'T USE ON PRODUCTION ENVIROMENT(will expose user authentication information)
Logging.level.org.springfamework.jdbc.core=TRACE
```
- Create a new bean for jdbc authentication while removing all the Inmemory authentication.
[securityConfig](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/java/com/luv2code/springboot/demoSecurity/security/DemoSecurityConfig.java)
```
//JDBC Authentication
    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
```

As the encryption for the passwords is indicated between the curly braces (_{noop}password_) on the information stored in the database, the process for checking the password comes out of the box and it doesn't need any extra implementation on the code.

#### Custom authentication tables
It's possible to use custom authentication tables. In this case we will use the following schema for authentication:
![Custom Authentication info]()

To do that we will have to update the [Security Configuration](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/java/com/luv2code/springboot/demoSecurity/security/DemoSecurityConfig.java) to define the request to retrieve the user information(password and roles).
```
//Define the query to retrieve usernames
jdbcUserDetailsManager.setUsersByUsernameQuery(
    "SELECT user_id, pw, active FROM members WHERE user_id=?");

//Define the query to retrieve user roles
jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
    "SELECT user_id, role FROM roles WHERE user_id=?");
```


## Custom Login Form
Although SpringBoot provides a default login form for authentication, it's possible to define a custom login form.
Steps:

1. Define the requests that will be required to be authenticated (For testing purposes we will be configuring all the incoming requests to be authenticated)

[Security configuration class - SecurityFilterChain Bean](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/java/com/luv2code/springboot/demoSecurity/security/DemoSecurityConfig.java)

```
http.authorizeHttpRequests(configurer ->
                configurer
                        .anyRequest()
                        .authenticated());//All the requests have to be authenticated (be a logged in user)
```
2. Redirect route for the login form.
```
http.formLogin(form ->
                form
                        .loginPage("/showLogin") //Route to load the custom login form
```
3. Authentication route for the login information provided.
```
                        .loginProcessingUrl("/authenticateUser") //Route to process login information provided
```
4. allow everyone trying ot access to any protected resource accessing to the login page.
```
                        .permitAll());// Login form is accessible to every user trying to access to the site
```


5. Define the [custom login Form](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/resources/templates/login.html)
```
In order to use the in-built login process an input for username and an input for password have to be defined.
<input id="username" type="text" name="username">
<input id="password" type="text" name="password">

The fields have to be defined with this names in order for the in-built login process to work.
```
```
Thymeleaf (th) instructions support.
In order to be able to use the th tag in the HTML form this needs to be referenced in the HTML form. 
The XML Name Space (xmlns) has to be added in the html tag
<html lang="en"
      xmlns:th="http://www.thymeleaf.org">
```

```
Context path (@) for routes
What is context path? -> The context path of a web application defines the URL that end users will access the application from. A simple context path like myapp means the web app can be accessed from a URL like http://localhost:8080/myapp

The '@' symbol in the action represents the context path. This context path changes dynamically everytime we modify the context path in our app. This means that the links in our app will always work even if the context path changes.

```
6. Define the controller implementing the method to load the login form. The route for the mapping has to be consistent with the route mentioned in the configuration file for the login form.
[Login Controller class](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/java/com/luv2code/springboot/demoSecurity/controller/LoginController.java)

## Configuring logout

Springboot provides the required process to perform the logout with no extra coding required. We will have to indicate to the security configuration we are planning to perform this operation and add a new form tag using the action logout and is mandatory that the method used is POST.

1. Add Logout support to the [Security configuration](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/java/com/luv2code/springboot/demoSecurity/security/DemoSecurityConfig.java)

```
                .logout(logout->logout.permitAll()
```
2. Add the Logout Form
[Logout Form - homepage](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/resources/templates/home.html)
```
<!--Logout process call-->
<form th:action="@{/logout}" method="POST">

    <input type="submit" value="Logout"/>
</form>
```
When we use the out of the box logout implementation the user will be redirected to the login page and the param logout will be part of the url(Ex: http://localhost:8080/showLogin?logout). We can use this parameter to show a message when a user have been redirected after logging out.

[Login page - Param logout check](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/resources/templates/fancy-login.html)

```
<!-- Check for logout -->
                                            <div th:if="${param.logout}">

                                                <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                                    User logged out successfully.
                                                </div>
```

## Registration Form
If we want to allow a user to register in our page a registration form needs to be defined along with some changes in our security config.

For saving the users information we will use the following tables for authentication:
[Registration SQL tables with sample data]()
```
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `enabled` tinyint NOT NULL,  
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
```
1. Create [User]() and [Role]() class for the tables we have just created.
2. Stablish the DB communication implementing a [UserDAO]() and a [Role DAO]()
3. Add a [user service]() to manage the registration process through the DAOs created in the previous steps.
4. Create a new [registration form]() that will be used by the new user to introduce the user info.
4. Create the [registration controller]() to define the routes that will be used by the form to call the Service methods
5. Add the registration routes to our [security configuration]() allowing every user to access so this routes are accessible (This step is required in order to make make this the registration routes visible for every user).
```
.requestMatchers("/register/**").permitAll()
```
6. Create a DaoAuthentication provider in in our security configuration to handle the authentication process and a PasswordEncrypter bean. 
```
DaoAuthenticationProvider is an AuthenticationProvider implementation that uses a UserDetailsService and PasswordEncoder to authenticate a username and password.
```
```
PasswordEncrypter is used by the system to ecncrypt the new user information and to validate user requests on login.
```

[DaoAuthenticationProvider Doc](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/dao-authentication-provider.html)


## Custom error handling
### Custom error messages on failed login

If there's an error when trying to login the user will be redirected to the login page. Since a custom login page is being used, it won't show any error message on redirection but the empty login form. An error message has to be configured so it is returned along with the login form.
When login fails the user will be redirected to the login form URL with an error parameter
```
Example: https://www.luv2code.com/demo/login?error
```
In order to return a custom error message the form has to check if the "error" parameter is part of the URL. To get this result the form must be modifiead and if the error message exist then the error message must be shown.

[Custom login form - Error handling](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/resources/templates/login.html)
```
<!-- Check for login error-->
<div th:if="${param.error}">
    <p class="color_error">Wrong combination of username and password</p>
</div>
```

### Custom error pages
[Security configuration- custom forbidden error page](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/java/com/luv2code/springboot/demoSecurity/security/DemoSecurityConfig.java)
```
                ...
                //Custom forbidden access (403 error) page
                .exceptionHandling(configurer->
                        configurer.accessDeniedPage("/access-denied"))
                ...
```

## Roles
### Retrieve username and roles
To retrieve the current logged in user information, you have to use spring security html tags. In order to do that we will add a new name service inside the html tag:

```
xmlns:sec="http://www.thymeleaf.org/extras/spring-security"
```
After adding this username and roles can be accessed by using authetication tag:
```
User: <span sec:authentication="principal.username"></span>
Roles: <span sec:authentication="principal.authorities"></span>
```
[Homepage example](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/resources/templates/home.html)

### Role based Authentication
Users can be prevented of accessing pages or resources based on their roles. In order to do that we have to update the security configuration adding the rules to follow:
[Spring security configuration](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/java/com/luv2code/springboot/demoSecurity/security/DemoSecurityConfig.java)
```
 http.authorizeHttpRequests(configurer ->
                configurer
                        //ROLE BASED SPRING SECURITY CONFIGURATION
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")

The ** in /leaders/** represents every route that starts with '/leaders'  
```

### Filtering content based on permissions
Roles can be used to filter the content showed. In order to do that we will have to use spring-security extra tags from thymeleaf:
```
xmlns:sec="http://www.thymeleaf.org/extras/spring-security"
```
After adding this tag we can use the authorize tag to filter the content based on the role of the user:
```
...
<div sec:authorize="hasRole('MANAGER')">
...
<div sec:authorize="hasRole('ADMIN')">
...
```
This instruction will cause that all the content contained in the divs won't be shown to any user with the wrong permissions. It won't even generate the empty elements or the code for them.
[HomePage - Content filtering by Role](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/resources/templates/home.html)




