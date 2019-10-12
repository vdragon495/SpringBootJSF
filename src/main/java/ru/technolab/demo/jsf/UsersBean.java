package ru.technolab.demo.jsf;

import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.technolab.demo.dao.User;
import ru.technolab.demo.dao.UsersRepository;

@Scope(value = "session")
@Component(value = "usersBean")
@ELBeanName(value = "usersBean")
@Join(path = "/users", to = "/users.xhtml")
public class UsersBean extends GenericBean {
	private static final Logger log = LoggerFactory.getLogger(UsersBean.class);	// Аналогично аннотации Lombok @Slf4j
	
    @Autowired
    private UsersRepository userRepository;
    
    private User selectedUser;
    
    private List<User> users;

    @PostConstruct
    public void init() {
    	users = userRepository.findAll();
    }
  
    public void save() {
    	try {
    		addSavingStatusMessage(userRepository.save(selectedUser)==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения пользователя: ", e);
    		addSavingStatusMessage(false);
    	}
    }

    public void update() {
    	try {
    		addSavingStatusMessage(userRepository.update(selectedUser)==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения пользователя: ", e);
    		addSavingStatusMessage(false);
    	}
    }

    public void delete() {
    	try {
    		addSavingStatusMessage(userRepository.deleteById(selectedUser.getLogin())==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка удаления пользователя: ", e);
    		addSavingStatusMessage(false);
    	}
    }
    
    public void newUser() { selectedUser = new User(); init(); }
 
    public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<User> getUsers() {
        return users;
    }
}