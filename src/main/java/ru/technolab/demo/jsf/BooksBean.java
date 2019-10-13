package ru.technolab.demo.jsf;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ru.technolab.demo.dao.Book;
import ru.technolab.demo.dao.BookRepository;

@Scope(value = "session")
@Component(value = "booksBean")
public class BooksBean extends GenericBean {
	private static final Logger log = LoggerFactory.getLogger(BooksBean.class);	// Аналогично аннотации Lombok @Slf4j
	
    @Autowired
    private BookRepository bookRepository;
    
    private Book selectedBook;
    
    private List<Book> books;
    
    private String login;

    @PostConstruct
    public void init() {
    	books = bookRepository.findAll();
    	try {
    		login = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    		log.info("Логин пользователя = "+login);
    	} catch(Exception e) {
    		log.warn("Ошибка получения данных пользователя", e);
    	}
    }
    
    public String getLogin() {
		return login;
	}

	public void save() {
    	try {
    		addSavingStatusMessage(bookRepository.save(selectedBook)==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения книги: ", e);
    		addSavingStatusMessage(false);
    	}
    }

    public void update() {
    	try {
    		addSavingStatusMessage(bookRepository.update(selectedBook)==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения книги: ", e);
    		addSavingStatusMessage(false);
    	}
    }

    public void delete() {
    	try {
    		addSavingStatusMessage(bookRepository.deleteById(selectedBook.getIsn())==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка удаления книги: ", e);
    		addSavingStatusMessage(false);
    	}
    }
    
    public void newBook() { selectedBook = new Book(); }

    public void getBook(Book book) {
    	try {
    		book.setUsersLogin(login);
    		bookRepository.update(book);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения книги: ", e);
    		showMsg("Error", "Не удалось взять книгу");
    	}
    }
    
    public void returnBook(Book book) {
    	try {
    		book.setUsersLogin(null);
    		bookRepository.update(book);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения книги: ", e);
    		showMsg("Error", "Не удалось вернуть книгу");
    	}
    }
    
    public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	public List<Book> getBooks() {
        return books;
    }
}