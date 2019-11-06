# SpringBootJSF
Simple Spring Boot starter 2.1.9.RELEASE for JSF 2.2 and PrimeFaces 6.0. 

Moreover, project contains integration with Spring Security, uses JdbcTemplate, DBMS=H2, Flyway, build automation of maven.

It builds with command *$ mvn clean package* from root project folder *TechnolabTest*. The result of building is TechnolabTest/target/TechnolabTest.jar. It could be run using command *$ java -jar target/TechnolabTest.jar*, and then it would be accessible in any browser by the link *http://localhost:8081/*. 

It requires authentication. First time use login 'vvv' and password '111'. After you authenticate you can create additional users and reauthenticate using them.
