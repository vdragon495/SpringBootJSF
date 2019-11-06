# SpringBootJSF
Simple Spring Boot starter 2.1.9.RELEASE for JSF 2.2 (Mojarra) and PrimeFaces 6.0. 

Moreover, project contains integration with Spring Security, uses JdbcTemplate, DBMS=H2, initially database fills by the Flyway tool, project build automation of maven.

## Run Example Application locally
To build and run the application you need maven and jre 8+ installed. I used Apache Maven 3.5.4 and jdk 12 on my PC. To download source code you can use command line or something else:
1- Clone this project. 
```Shell
git clone https://github.com/vdragon495/SpringBootJSF.git
```

2- Build from root project folder */TechnolabTest*. The result of building is /TechnolabTest/target/TechnolabTest.jar
```Shell
mvn clean package
```

3- Run from root project folder */TechnolabTest*
```Shell
java -jar target/TechnolabTest.jar
```

4- Access starter page in any browser at **http://localhost:8081/**

It requires authentication. First time use login 'vvv' and password '111'. After you authenticate you can create additional users and reauthenticate using them.

## Application description
This application implements simple functionality of public books library. The language of application is Russian. There are two database tables: users and book. After authentication with default user vvv you can see list of users and list of books on separate pages - facelets (list of books by default), switching between them using buttons on the header. Moreover, you can add, edit or remove any user or book, and you can sort that lists by clicking on the headers. On books page there is a column 'Кем взята' in table of books - in English 'Taken by'. It contains login of user who takes the book, or one of the links 'Взять/Вернуть' (Take/Return in English). If you click 'Take' link it transform into 'Return' as if you really get that book. Then if you reauthenticate with another user you can see your previous login name at that cell of column. There is a technical task attached to the project. It contains all details of functionality, but it is in Russian language. It is in /docs folder
