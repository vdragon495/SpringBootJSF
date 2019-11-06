# SpringBootJSF
Simple Spring Boot starter 2.1.9.RELEASE for JSF 2.2 (Mojarra) and PrimeFaces 6.0. 

Moreover, project contains integration with Spring Security, uses JdbcTemplate, DBMS=H2, initially database fills by the Flyway tool, project build automation of maven.

To build and run the application you need maven and jre 8+ installed. I used Apache Maven 3.5.4 and jdk 12 on my PC.

It builds with command *$ mvn clean package* from root project folder */TechnolabTest*. The result of building is /TechnolabTest/target/TechnolabTest.jar. It could be run using command *$ java -jar target/TechnolabTest.jar*, and then it would be accessible in any browser by the link *http://localhost:8081/*. 

It requires authentication. First time use login 'vvv' and password '111'. After you authenticate you can create additional users and reauthenticate using them.

This application implements simple functionality of public books library. The language of application is Russian. There are two database tables: users and book. After authentication with default user vvv you can see list of users and list of books on separate pages - facelets (list of books by default), switching between them using buttons on the header. Moreover, you can add, edit or remove any user or book, and you can sort that lists by clicking on the headers. There is a technical task attached to the project. It contains all details of functionality, but it is on Russian language. It is in /docs folder
