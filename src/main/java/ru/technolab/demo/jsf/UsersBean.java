package ru.technolab.demo.jsf;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.technolab.demo.dao.User;
import ru.technolab.demo.dao.UsersRepository;

/** Обслуживает страницу /users.xhtml */
@Scope(value = "session")
@Component(value = "usersBean")
public class UsersBean extends GenericBean<User> {
	private static final Logger log = LoggerFactory.getLogger(UsersBean.class);	// Аналогично аннотации Lombok @Slf4j
	
    @Autowired
    private UsersRepository userRepository;
    
    @PostConstruct
    public void init() {
    	setModel(userRepository.findAll());
    }
  
    public void save() {
    	try {
    		addSavingStatusMessage(userRepository.save(getSelected())==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения пользователя: ", e);
    		addSavingStatusMessage(false);
    	}
    }

    public void update() {
    	try {
    		addSavingStatusMessage(userRepository.update(getSelected())==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения пользователя: ", e);
    		addSavingStatusMessage(false);
    	}
    }

    public void delete() {
    	try {
    		addSavingStatusMessage(userRepository.deleteById(getSelected().getLogin())==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка удаления пользователя: ", e);
    		addSavingStatusMessage(false);
    	}
    }
    
    public void newUser() { setSelected(new User()); }
}