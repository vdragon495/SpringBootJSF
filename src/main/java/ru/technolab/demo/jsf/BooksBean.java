package ru.technolab.demo.jsf;

import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.technolab.demo.dao.Book;
import ru.technolab.demo.dao.BookRepository;

@Scope(value = "session")
@Component(value = "booksBean")
@ELBeanName(value = "booksBean")
@Join(path = "/books", to = "/books.xhtml")
public class BooksBean extends GenericBean {
	private static final Logger log = LoggerFactory.getLogger(BooksBean.class);	// Аналогично аннотации Lombok @Slf4j
	
    @Autowired
    private BookRepository bookRepository;
    
    private Book selectedBook;
    
    private List<Book> books;

    @PostConstruct
    public void init() {
    	books = bookRepository.findAll();
    }
    
//    @Deferred
//    @RequestAction
//    @IgnorePostback
//    public void loadData() {
//        books = bookRepository.findAll();
//    }
    
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