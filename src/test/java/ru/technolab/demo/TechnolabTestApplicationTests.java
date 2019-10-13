package ru.technolab.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.technolab.demo.dao.BookRepository;
import ru.technolab.demo.dao.UsersRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TechnolabTestApplicationTests {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void testFlyway() {
		Assert.assertEquals(1, usersRepository.findAll().size());
		Assert.assertEquals(0, bookRepository.findAll().size());
	}
}
