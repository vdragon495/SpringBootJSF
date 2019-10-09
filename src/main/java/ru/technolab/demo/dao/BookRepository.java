package ru.technolab.demo.dao;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
	private static final Logger log = LoggerFactory.getLogger(BookRepository.class);	// Аналогично аннотации Lombok @Slf4j
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	private void init() {
		log.info("Количество книг = "+findAll().size());
	}

    public List<Book> findAll() {
        return jdbcTemplate.query(
                "select * from testdb.BOOK",
                (rs, rowNum) ->
                        new Book(
                                rs.getInt("isn"),
                                rs.getString("author"),
                                rs.getString("name"),
                                rs.getString("users_login")
                        )
        );
    }

    public Optional<Book> findById(Integer isn) {
        return jdbcTemplate.queryForObject(
                "select * from testdb.BOOK where isn = ?",
                new Object[] {isn},
                (rs, rowNum) ->
                        Optional.of(new Book(
                                rs.getInt("isn"),
                                rs.getString("author"),
                                rs.getString("name"),
                                rs.getString("users_login")
                        ))
        );
    }
    
    public int save(Book book) {
        return jdbcTemplate.update(
                "insert into testdb.BOOK (isn, author, name, users_login) values (?,?,?,?)",
                book.getIsn(), book.getAuthor(), book.getName(), book.getUsersLogin());
    }

    public int update(Book book) {
        return jdbcTemplate.update(
                "update testdb.BOOK set author = ?, name = ?, users_login = ? where isn = ?",
                book.getAuthor(), book.getName(), book.getUsersLogin(), book.getIsn());
    }

    public int deleteById(Integer isn) {
        return jdbcTemplate.update(
                "delete books where isn = ?",
                isn);
    }
}
