# Spring-Hibernate-for-Beginners
 ----

# Spring Boot - Spring Security

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
[Security configuration class]()

## Custom Login Form
Although SpringBoot provides a default login form for authentication, it's possible to define a custom login form.
Steps:

1. Define the requests that will be required to be authenticated (For testing purposes we will be configuring all the incoming requests to be authenticated)
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

[Security configuration class]()

5. Define the [custom login Form]()
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

### Configuring logout
Springboot provides the required process to perform the logout with no extra coding required. We will have to add a new form tag using the action logout and is mandatory that the method used is POST.

[Custom login form - Logout](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/08-spring-boot-spring-mvc-security/01-springboot-spring-mvc-security-default/src/main/resources/templates/login.html)
```
<!--Logout process call-->
<form th:action="@{/logout}" method="POST">

    <input type="submit" value="Logout"/>
</form>
```

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


