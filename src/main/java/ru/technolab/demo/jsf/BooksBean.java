package ru.technolab.demo.jsf;

import java.util.List;

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
import ru.technolab.demo.dao.UsersRepository;

@Scope(value = "session")
@Component(value = "booksBean")
@ELBeanName(value = "booksBean")
@Join(path = "/books", to = "/books.xhtml")
public class BooksBean {
	private static final Logger log = LoggerFactory.getLogger(BooksBean.class);	// Аналогично аннотации Lombok @Slf4j
	
    @Autowired
    private BookRepository bookRepository;
    
    private Book selectedBook;
    
//    private List<Book> books;
//    
//    @Deferred
//    @RequestAction
//    @IgnorePostback
//    public void loadData() {
//        books = bookRepository.findAll();
//    }
    
//    public String save() {
//        productRepository.save(product);
//        product = new Product();
//        return "/product-list.xhtml?faces-redirect=true";
//    }
    
    public void newBook() { selectedBook = new Book(); }
 
    public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}