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
public class UsersRepository {
	private static final Logger log = LoggerFactory.getLogger(UsersRepository.class);	// Аналогично аннотации Lombok @Slf4j
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	private void init() {
		log.info("Количество пользователей = "+findAll().size());
	}
	
	public List<User> findAll() {
        return jdbcTemplate.query(
                "select login, passw FROM testdb.USERS",
                (rs, rowNum) ->
                        new User(rs.getString("login"), rs.getString("passw"))
        );
	}
	
    public Optional<User> findById(String login) {
        return jdbcTemplate.queryForObject(
                "select login, passw FROM testdb.USERS WHERE login = ?",
                new Object[] {login},
                (rs, rowNum) ->
                        Optional.of(new User(rs.getString("login"), rs.getString("passw")))
        );
    }
    
    public int save(User user) {
        return jdbcTemplate.update(
                "insert into testdb.USERS (login, passw) values (?,?)",
                user.getLogin(), user.getPassw());
    }

    public int update(User user) {
        return jdbcTemplate.update(
                "update testdb.USERS set passw = ? where login = ?",
                user.getPassw(), user.getLogin());
    }

    public int deleteById(String login) {
        return jdbcTemplate.update(
                "delete testdb.USERS where login = ?",
                login);
    }
}
