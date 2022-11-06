# Spring Boot Test

This aim of the repository is to practice writing unit-tests, integration test, acceptance test for REST API service with spring boot

## Setup 

Install mongodb and run it

Download or clone the repo 

Make sure you have necessary plugins to import the project if you are using IDEs. 

For VSCode the Java Extension Pack and Spring Boot Extension Pack are needed to run
the server and tests from the IDE environment

For those who prefer terminal: 
```bash
  
   // go to the project directory 
  
   $ cd path/to/springboot-test 

   // to run the tests

   $ ./mvnw tests   

   // to run the server 

   $ ./mvnw spring-boot:run
 ```  


## Pending 

Check for exceptions in unit-tests, Lombok annotations


## Future Work

This project can evolve into a handy spring-boot skeleton by setting up and implementing : 

               - dockerfile / deployment files on cloud (aws, gcp)
               - enable https connections
               - docs for API and source code
               - static code analysis tools 
               - authentication / authorization  
               - error handling and validations


Adding above capabilities is one time cost and can be useful 



## References 

[Java-techie-github](https://github.com/Java-Techie-jt/spring-boot-mockito)


[Kric-foster-github](https://github.com/kriscfoster/spring-boot-testing-pyramid/)

 
[Java-techie-youtube](https://www.youtube.com/watch?v=kXhYu939_5s): Write tests using JUnit and Mockito
 

[Kric-foster-youtube](https://www.youtube.com/watch?v=aEW8ZH6wj2o): Spring Boot Testing Rest Controller with Unit, Integration and Acceptance tests


[Defog-tech-youtube](https://www.youtube.com/watch?v=Ekr4jxOIf4c): Spring Boot Testing basics


 
[how-to-do-in-java](https://howtodoinjava.com/spring-boot2/testing/springboottest-annotation/): Spring boot annotations


[Spring-framework-guru](https://springframework.guru/blog/): Bean Validation and Exception Handling 


[reflectoring.io](https://reflectoring.io/spring-boot-exception-handling/): Exception handling


[Mongodb-install](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/)
