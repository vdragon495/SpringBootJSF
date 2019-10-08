create schema if not exists testdb;

DROP TABLE IF EXISTS testdb.USERS;

CREATE TABLE testdb.USERS (
  login VARCHAR(25) PRIMARY KEY,		-- login
  passw VARCHAR(25)
);

DROP TABLE IF EXISTS testdb.BOOK;
  
CREATE TABLE testdb.BOOK (
  isn INT AUTO_INCREMENT  PRIMARY KEY,	-- id
  author VARCHAR(100) NOT NULL,			-- Автор книги
  name VARCHAR(100) NOT NULL,			-- Название книги
  users_login VARCHAR(25) DEFAULT NULL,	-- Кем взята
  foreign key (users_login) references USERS(login)
);