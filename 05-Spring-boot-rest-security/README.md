# Spring-Hibernate-for-Beginners
 ----

# Spring Security - backend Authentication configuration
----

[Security Reference Manual](https://docs.spring.io/spring-security/reference/)

## Authenication

In order to allow Spring boot to get the registration information from the database by default We will have to define the tables _users_ and _authorities_:

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

Custom tables can be configured for user authentication. This will require to let the app know the specific query to retriever user and roles info.
[Role based Authentication](https://github.com/carlosreyplanelles/Spring-Hibernate-for-Beginners/blob/main/05-Spring-boot-rest-security/00-spring-boot-rest-security-employee-starter-code/src/main/java/com/luv2code/springboot/cruddemo/security/DemoSecurityConfig.java)

```
To configure custom authentication jdbcUserDetailsManager functions to let the app know the tables used:

//CONFIGURE CUSTOM TABLES FOR AUTHENTICATION
        //Query to retrieve user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members where user_id=?");

        //Query to retrieve roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");
```

### Password encryption

Spring Security allow to store encrypted passwords using different algorithms (althought the recommended one is bcrypt).
The encryption algorithm used is stored in the database contained into brackets('{}'):

```
{noop}test123
{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q
```

- **{noop}:** No encryption applied. The password is stored as plain text.
- **{bcrypt}:** Bcrypt encryption is applied to the password. Bcrypt is a one way encripting. Wen introducing the user information will apply the salt in the password stored in the database to the password introduced by the user. The password in the database can't be decrypted.
