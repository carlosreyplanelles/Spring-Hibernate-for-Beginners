# Spring Boot - Thymeleaf

Thymeleaf is a templating framework used for java applications. This framework can be used for any type of application based on java.
[Thymeleaf docs](https://www.thymeleaf.org/documentation.html)

In order to return an html file, a controller have to be created and return the name of the html file without the extension. All the information will be added to the model by using the addAttribute method indicating the name and the value for this property in the model.
[Controller](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/07-Spring-boot-spring-mvc/01-thymeleafdemo-helloworld/src/main/java/com/luv2code/springboot/thymeleafdemo/controller/DemoController.java)

## Resources

By default, the system look for the templates in the resources folder of the project.

## Accessing java objects information

In order to access java objects information from the template we will have to will use the syntax ${\[Model property name\]}
[HTML template](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/07-Spring-boot-spring-mvc/01-thymeleafdemo-helloworld/src/main/resources/templates/helloWorld.html)