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

## Key Files

*pom.xml* includes nothing special except the main thing - version of spring-boot-maven-plugin must be like here differ from the version of spring-boot-starter-parent. Otherwise it won't works!!!
```xml
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<version>1.4.2.RELEASE</version>			<!--  версия обязательно такая, иначе при попытке войти на сайт: /books.xhtml Not Found in ExternalContext as a Resource -->
			</plugin>
```

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.9.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>ru.technolab</groupId>
	<artifactId>TechnolabTest</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>TechnolabTest</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
		<jsf.version>2.2.11</jsf.version>
	</properties>

	<dependencies>
		<dependency> 
			<groupId>org.springframework.boot</groupId> 
			<artifactId>spring-boot-starter-security</artifactId> 
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>	<!-- +JdbcTemplate. Чтобы проинициализировался DataSource необходимо starter-jdbc либо starter-data-jpa -->
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>

		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-api</artifactId>
			<version>${jsf.version}</version>
		</dependency>
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-impl</artifactId>
			<version>${jsf.version}</version>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
		</dependency>
		<dependency>
			<groupId>org.primefaces</groupId>
			<artifactId>primefaces</artifactId>
			<version>6.0</version>
		</dependency>

		<dependency>	<!-- DB tool -->
			<groupId>org.flywaydb</groupId>
			<artifactId>flyway-core</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<finalName>${project.artifactId}</finalName>		<!-- так результат сборки просто /target/TechnolabTest.jar без версии -->
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<version>1.4.2.RELEASE</version>			<!--  версия обязательно такая, иначе при попытке войти на сайт: /books.xhtml Not Found in ExternalContext as a Resource -->
			</plugin>
		</plugins>
		<resources>
			<resource>										<!-- тут указываем что надо подменить в папке вставки вида @xxx@ (для не 
					Spring boot - ${xxx}) значениями. Например ${project.artifactId} подменится 
					на TechnolabTest -->
				<directory>src/main/resources</directory>
				<filtering>true</filtering>
			</resource>
		</resources>
	</build>

	<repositories>
		<repository>
			<url>http://repository.primefaces.org/</url>
			<id>PrimeFaces-maven-lib</id>
			<layout>default</layout>
			<name>Repository for library PrimeFaces-maven-lib</name>
		</repository>
	</repositories>
</project>
```

### src/main/resources/application.properties

```properties
server.port=8081

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password= 

spring.devtools.add-properties=false

flyway.enabled=true
spring.flyway.locations=classpath:db/migration
flyway.schemas=testdb

# root logging
logging.level.ROOT=INFO
logging.file=@user.home@/app/logs/technolab.log

# setup from maven - ${project.version} -> @project.version@ because Spring boot!
project.version=@project.version@
project.artifactId=@project.artifactId@
```
