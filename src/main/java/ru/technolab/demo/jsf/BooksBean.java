package ru.technolab.demo.jsf;

import java.util.ConcurrentModificationException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ru.technolab.demo.dao.Book;
import ru.technolab.demo.dao.BookRepository;

/** Обслуживает страницу /books.xhtml */
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
    
    /** @return	Логин авторизованного пользователя */
    public String getLogin() {
		return login;
	}

    /** Сохранение книги по кнопке Сохранить из диалога Добавить книгу... */
	public void save() {
    	try {
    		addSavingStatusMessage(bookRepository.save(selectedBook)==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения книги: ", e);
    		addSavingStatusMessage(false);
    	}
    }

	/** Сохранение книги по кнопке Сохранить из диалога редактирования книги */
    public void update() {
    	try {
    		addSavingStatusMessage(bookRepository.update(selectedBook)==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения книги: ", e);
    		addSavingStatusMessage(false);
    	}
    }

    /** Удаление книги по кнопке Удалить */
    public void delete() {
    	try {
    		addSavingStatusMessage(bookRepository.deleteById(selectedBook.getIsn())==1);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка удаления книги: ", e);
    		addSavingStatusMessage(false);
    	}
    }
    
    /** Вызывается при добавлении книги */
    public void newBook() { selectedBook = new Book(); }

    /** Вызывается при клике по Взять книгу */
    public void getBook(Book book) {
    	try {    		
    		book = bookRepository.findById(book.getIsn()).get();
    		if(!StringUtils.isEmpty(book.getUsersLogin())) throw new ConcurrentModificationException();
    		book.setUsersLogin(login);
    		bookRepository.update(book);
    		init();
    	} catch(Exception e) {
    		log.warn("Ошибка сохранения книги: ", e);
    		showMsg("Error", "Не удалось взять книгу");
    	}
    }
    
    /** Вызывается при клике по Вернуть книгу */
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