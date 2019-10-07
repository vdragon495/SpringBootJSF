package ru.technolab.demo.dao;

public class Book {
	private Integer isn;
	private String author;
	private String name;
	private String usersLogin;
	
	public Book(Integer isn, String author, String name, String usersLogin) {
		super();
		this.isn = isn;
		this.author = author;
		this.name = name;
		this.usersLogin = usersLogin;
	}

	public Integer getIsn() {
		return isn;
	}

	public void setIsn(Integer isn) {
		this.isn = isn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsersLogin() {
		return usersLogin;
	}

	public void setUsersLogin(String usersLogin) {
		this.usersLogin = usersLogin;
	}
}
