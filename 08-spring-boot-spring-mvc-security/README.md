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
[Login Controller class]()